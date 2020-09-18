package com.paysuper.tests;



import com.google.common.base.Verify;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test(enabled = false)
    public void Test(){
    }


    @Test(enabled = true,
            description="success simple payment https://protocolone.tpondemand.com/entity/193560-web-payform-uspeshnaya-pokupka-simp",
            groups = {"tst", "stg", "prod", "pay"})
    public void SimpleOrderSuccessPayTest() throws InterruptedException {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setOrderCurrency(app.getProperties.value("OrderCurrency"));
        order.setOrderAmount(app.getProperties.value("OrderAmount"));
        order.setProjectId(app.getProperties.value("ProjectId"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createSimpleOrder(
                app.getProperties.value("ApiUrlCheckout"),
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        Assert.assertEquals(app.driver.findElement(Locators.get("PayForm.OrderSummaryValue")).getText(), "€" + order.getOrderAmount());
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmountFromPayForm(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(app.getProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
        MailParser mailParser = new MailParser(app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        Thread.sleep(4000);
        mailParser.parsePurchaseCheck();
        Assert.assertEquals(email.getTransactionID(), order.getUUID());
        Assert.assertEquals(email.getTotal(), order.getTotalAmountFromPayForm());
        Assert.assertEquals(email.getTransactionDate(), order.getToday());
        Assert.assertEquals(email.getMerchantName(), app.getProperties.value("MerchantName"));
        if(order.getCountryFromPayForm().equals("UA") | order.getCountryFromPayForm().equals("RU")){
            Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNameMalta"));
        }
        else
            Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));
    }
    }

