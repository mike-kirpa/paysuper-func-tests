package com.paysuper.tests.suite;

import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.helpers.MockApi;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.models.Webhook;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardMainPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import com.paysuper.appmanager.pages.dashboard.OrderPage;
import com.paysuper.appmanager.pages.payform.PayFormPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OrderPaymentTest extends TestBase {

    @Test(description="success simple payment",
            groups = {"tst", "stg", "prod", "pay", "simple-pay"})
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
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(GetProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"), "Incorrect title of payform after paid:");
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessSimplePayText"), "Incorrect message in payform after paid:");
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient(), "Incorrect customer email in payform after paid:");
        MailParser mailParser = new MailParser(GetProperties.value("user_login_for_email"),
                GetProperties.value("Email_pass"),
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

    @Test(description="success product payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void ProductOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(GetProperties.value("ProjectId"));
        order.setProduct(GetProperties.value("Product"));
        email.setSubject(GetProperties.value("EmailPurchaseCheckSubject"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                GetProperties.value("ApiUrlCheckout"),
                "product",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="success token payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void TokenOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(GetProperties.value("ProjectId"));
        order.setSecret(GetProperties.value("Secret"));
        email.setSubject(GetProperties.value("EmailPurchaseCheckSubject"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createTokenOrder(
                GetProperties.value("ApiUrl"),
                order,
                email);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(groups = {"tst", "stg", "prod", "pay"})
    public void ProductKeyOrderSuccessPayTest()  {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(GetProperties.value("ProjectId"));
        order.setProduct(GetProperties.value("ProductKey"));
        email.setSubject(GetProperties.value("EmailPurchaseCheckSubject"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                GetProperties.value("ApiUrlCheckout"),
                "key",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessProductKeyPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="Dashboard Refund From Order List",
            groups = {"refund"})
    public void RefundFromOrderListTest() throws InterruptedException {
        Email email = new Email();
        Order order = new Order();

        email.setSubject(GetProperties.value("EmailRefundSubject"));

        app.driver.get(GetProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(GetProperties.value("ValidEmail"), GetProperties.value("Password"));
        DashboardTransactionsPage dashboardTransactionsPage = dashboardMainPage.clickOnTransactionSearchLink();
        dashboardTransactionsPage.openFilteredOrderList("processed");
        String lastOrderUrl = dashboardTransactionsPage.clickRefundOnLastTransaction(order);
        dashboardTransactionsPage.clickOnConfrimRefundButton();
        Thread.sleep(1000);
        app.driver.navigate().refresh();
        Thread.sleep(1000);
        dashboardLoginPage.waitForElementLoad("DashboardTransactionsPage.HeaderText");
        Assert.assertFalse(dashboardLoginPage.isElementPresent(By.xpath("//a[@href='" + lastOrderUrl + "']/*/div[contains(@class, 'refund')]")));

        OrderPage orderPage = dashboardTransactionsPage.openOrderPageByLink(GetProperties.value("DashboardUrl") + lastOrderUrl);
        email.setEmailRecipient(orderPage.getEmail());
        order.setGrossRevenue(orderPage.getTotalChargeSum());
        order.setCountry(orderPage.getBillingAddress());
        order.setUUID(orderPage.getOrderUid());


        dashboardTransactionsPage = dashboardMainPage.clickOnTransactionSearchLink();
        dashboardTransactionsPage.openFilteredOrderList("refunded");
        Thread.sleep(3000);
        OrderPage refundOrderPage = dashboardTransactionsPage.clickOnOrderBytransactionId(order.getTransactionID());
        refundOrderPage.getRefundOrderUid(order);

        MailParser mailParser = new MailParser(GetProperties.value("user_login_for_email"),
                GetProperties.value("Email_pass"),
                email);
        mailParser.parseRefundedCheck();
        org.testng.Assert.assertEquals(email.getTransactionID(), order.getUUID());
        org.testng.Assert.assertEquals(email.getTotal(), order.getGrossRevenue());
        org.testng.Assert.assertEquals(email.getTransactionDate(), order.getToday());
        org.testng.Assert.assertEquals(email.getMerchantName(), GetProperties.value("MerchantName"));
        org.testng.Assert.assertEquals(email.getPaymentPartner(), GetProperties.value("OperCompanyNAmeCyprus"));

    }

    @Test(description="Decline 3DS simple payment",
            groups = {"tst", "prod", "pay"})
    public void SimpleOrder3DSDeclineTest() throws InterruptedException {
        Webhook actualWebhook = new Webhook("null");
        Webhook expectedWebhook = new Webhook("payment.cancel");
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
        payFormPage.inputBankCardNumber(GetProperties.value("No3DSSPaymentDECLINED"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputCity(GetProperties.value("CustomerCity"));
        payFormPage.inputAddress(GetProperties.value("SomeAddress"));
        payFormPage.inputPhone(GetProperties.value("Phone"));
        payFormPage.inputWorkPhone(GetProperties.value("Phone"));
        payFormPage.inputHomePhone(GetProperties.value("Phone"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(GetProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("UnSuccessPayTitle"));
        //Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("UnSuccessPayText"));

        //Webhook assert
        expectedWebhook.setObject_id(order.getUUID());
        mockApi.checkAndCleatEvent(actualWebhook);
        Assert.assertEquals(actualWebhook, expectedWebhook, "Actual webhook's data not equal:");
    }
    @Test(description="paylink payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void PayLinkSuccessPayTest() {
        Email email = new Email();
        String unixTime;
        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        app.driver.get(GetProperties.value("PayLinkURL") + GetProperties.value("PayLinkId"));
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="shortpaylink payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void ShortPayLinkSuccessPayTest(){
        Email email = new Email();
        String unixTime;
        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        app.driver.get(GetProperties.value("ShortPayLinkURL"));
        PayFormPage payFormPage =new PayFormPage(app.driver,
                GetProperties.value("DefaultLanguage"),
                GetProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(GetProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(GetProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(GetProperties.value("ValidCVV"));
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="success payment with a saved card",
            groups = {"tst", "stg", "prod", "pay", "pay-with-saved-card"})
    public void SimpleOrderSavedCardSuccessPayTest() throws InterruptedException {

        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setOrderCurrency(GetProperties.value("OrderCurrency"));
        order.setOrderAmount(GetProperties.value("OrderAmount"));
        order.setProjectId(GetProperties.value("ProjectId"));
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
        payFormPage.inputZipCode(GetProperties.value("ZipCode"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(GetProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickRememberMeCheckbox();
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"), "Incorrect title of payform after paid:");

        payment_form_url = app.restAPI.createSimpleOrder(GetProperties.value("ApiUrlCheckout"), order);
        for(int i = 0; i < 5; i++){
            app.driver.get(payment_form_url);
            payFormPage =new PayFormPage(app.driver, GetProperties.value("DefaultLanguage"), GetProperties.value("DefautCountry"));
            if (payFormPage.isElementPresent("PayForm.SavedBankCardField")){
                break;
            }
            Thread.sleep(500);
        }
        Assert.assertEquals(payFormPage.getSavedBankCardNumber(), GetProperties.value("ValidNo3DSBankCardMasked"), "Incorrect saved card number in payform:");
        //payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), GetProperties.value("EnSuccessPayTitle"), "Incorrect title of payform after paid:");
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), GetProperties.value("EnSuccessSimplePayText"), "Incorrect message in payform after paid:");
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient(), "Incorrect customer email in payform after paid:");

        payment_form_url = app.restAPI.createSimpleOrder(GetProperties.value("ApiUrlCheckout"), order);
        app.driver.get(payment_form_url);
        payFormPage =new PayFormPage(app.driver, GetProperties.value("DefaultLanguage"), GetProperties.value("DefautCountry"));
        payFormPage.clickOnDeleteCardButton();
        Assert.assertTrue(payFormPage.isElementNotPresent("PayForm.SavedBankCardField"), "Saved card number is present");
    }
}
