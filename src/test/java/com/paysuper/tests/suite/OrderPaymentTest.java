package com.paysuper.tests.suite;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.payform.PayFormPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OrderPaymentTest extends TestBase {

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
        order.setTotalAmount(payFormPage.getTotalAmount());
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
        Assert.assertEquals(email.getTotal(), order.getTotalAmount());
        Assert.assertEquals(email.getTransactionDate(), order.getToday());
        Assert.assertEquals(email.getMerchantName(), app.getProperties.value("MerchantName"));
        if(order.getCountry().equals("UA") | order.getCountry().equals("RU")){
            Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNameMalta"));
        }
        else
            Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));
    }

    @Test(enabled = true,
            description="success product payment https://protocolone.tpondemand.com/entity/193318-web-payform-uspeshnaya-pokupka-product",
            groups = {"tst", "stg", "prod", "pay"})
    public void ProductOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ProjectId"),
                app.getProperties.value("Product"),
                app.getProperties.value("ApiUrlCheckout"),
                "product");
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }

    @Test(enabled = true,
            description="success token payment https://protocolone.tpondemand.com/entity/193349-web-payform-uspeshnaya-pokupka-s-pomoshyu",
            groups = {"tst", "stg", "prod", "pay"})
    public void TokenOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createTokenOrder(
                app.getProperties.value("ApiUrl"),
                app.getProperties.value("ProjectId"),
                app.getProperties.value("Secret"),
                app.getProperties.value("ValidEmail"));
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }

    @Test(enabled = true, groups = {"tst", "pay"})
    public void ProductKeyOrderSuccessPayTest()  {
        String payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ProjectId"),
                app.getProperties.value("ProductKey"),
                app.getProperties.value("ApiUrlCheckout"),
                "key");
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductKeyPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }
}
