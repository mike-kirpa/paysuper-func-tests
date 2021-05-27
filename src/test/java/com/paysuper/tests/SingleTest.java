package com.paysuper.tests;


import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.helpers.MockApi;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.models.Webhook;
import com.paysuper.appmanager.pages.dashboard.*;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleTest extends TestBase {
    @Test(description="success simple payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void SimpleOrderSuccessPayTest() throws InterruptedException {
        Webhook actualWebhook = new Webhook("null");
        Webhook expectedWebhook = new Webhook("order.processed");
        MockApi mockApi = new MockApi();
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;


        order.setOrderCurrency(GetProperties.value("OrderCurrency"));
        order.setOrderAmount(GetProperties.value("OrderAmount"));
        order.setProjectId(GetProperties.value("ProjectWebhooksId"));
        email.setSubject(GetProperties.value("EmailPurchaseCheckSubject"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createSimpleOrder(
                GetProperties.value("ApiUrlCheckout"),
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
//        Assert.assertEquals(app.driver.findElement(Locators.get("PayForm.OrderSummaryValue")).getText(), "€" + order.getOrderAmount());
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(GetProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
        MailParser mailParser = new MailParser(GetProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        mailParser.parsePurchaseCheck();
        Assert.assertEquals(email.getTransactionID(), order.getUUID());
        Assert.assertEquals(email.getTotal(), order.getTotalAmount());
        Assert.assertEquals(email.getTransactionDate(), order.getToday());
        Assert.assertEquals(email.getMerchantName(), GetProperties.value("MerchantName"));
        Assert.assertEquals(email.getPaymentPartner(), GetProperties.value("OperCompanyNAmeCyprus"));

        //Webhook assert

        expectedWebhook.setObject_id(order.getUUID());
        mockApi.checkAndCleatEvent(actualWebhook);
        Assert.assertEquals(expectedWebhook, actualWebhook, "Actual webhook's data not equal:");
    }
}

