package com.paysuper.tests;



import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardMainPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import com.paysuper.appmanager.pages.dashboard.OrderPage;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {
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
        org.testng.Assert.assertEquals(app.driver.findElement(Locators.get("PayForm.OrderSummaryValue")).getText(), "€" + order.getOrderAmount());
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(app.getProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        org.testng.Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        org.testng.Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        org.testng.Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
        MailParser mailParser = new MailParser(app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        mailParser.parsePurchaseCheck();
        org.testng.Assert.assertEquals(email.getTransactionID(), order.getUUID());
        org.testng.Assert.assertEquals(email.getTotal(), order.getTotalAmount());
        org.testng.Assert.assertEquals(email.getTransactionDate(), order.getToday());
        org.testng.Assert.assertEquals(email.getMerchantName(), app.getProperties.value("MerchantName"));
        if(order.getCountry().equals("UA") | order.getCountry().equals("RU")){
            org.testng.Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNameMalta"));
        }
        else
            org.testng.Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));
    }

    @Test(enabled = true,
            description="success product payment https://protocolone.tpondemand.com/entity/193318-web-payform-uspeshnaya-pokupka-product",
            groups = {"tst", "stg", "prod", "pay"})
    public void ProductOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setProduct(app.getProperties.value("Product"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ApiUrlCheckout"),
                "product",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        org.testng.Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        org.testng.Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductPayText"));
        org.testng.Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(enabled = true,
            description="success token payment https://protocolone.tpondemand.com/entity/193349-web-payform-uspeshnaya-pokupka-s-pomoshyu",
            groups = {"tst", "stg", "prod", "pay"})
    public void TokenOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setSecret(app.getProperties.value("Secret"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createTokenOrder(
                app.getProperties.value("ApiUrl"),
                order,
                email);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.clickPayButton();
        org.testng.Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        org.testng.Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        org.testng.Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(enabled = true, groups = {"tst", "stg", "prod", "pay"})
    public void ProductKeyOrderSuccessPayTest()  {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setProduct(app.getProperties.value("ProductKey"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ApiUrlCheckout"),
                "key",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        org.testng.Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        org.testng.Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductKeyPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }
    }

