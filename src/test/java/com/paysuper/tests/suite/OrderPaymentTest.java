package com.paysuper.tests.suite;

import com.paysuper.appmanager.pages.PayFormPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OrderPaymentTest extends TestBase {

    @Test(enabled = true, description="success simple payment https://protocolone.tpondemand.com/entity/193560-web-payform-uspeshnaya-pokupka-simp")
    public void SimpleOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createSimpleOrder(
                app.getProperties.value("ProjectId"),
                Integer.parseInt(app.getProperties.value("Amount")),
                app.getProperties.value("Currency"),
                app.getProperties.value("ApiUrlCheckout"));
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver, app.getProperties.value("DefaultLanguage"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }

    @Test(enabled = true, description="success product payment https://protocolone.tpondemand.com/entity/193318-web-payform-uspeshnaya-pokupka-product")
    public void ProductOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ProjectId"),
                app.getProperties.value("Product"),
                app.getProperties.value("ApiUrlCheckout"));
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver, app.getProperties.value("DefaultLanguage"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }

    @Test(enabled = true, description="success token payment https://protocolone.tpondemand.com/entity/193349-web-payform-uspeshnaya-pokupka-s-pomoshyu")
    public void TokenOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createTokenOrder(
                app.getProperties.value("ApiUrl"),
                app.getProperties.value("ProjectId"),
                app.getProperties.value("Secret"));
        System.out.println(payment_form_url);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver, app.getProperties.value("DefaultLanguage"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }
}
