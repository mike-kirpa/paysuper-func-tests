package com.paysuper.tests;

import com.paysuper.appmanager.pages.PayFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test()
    public void ProductOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ProjectId"),
                app.getProperties.value("Product"),
                app.getProperties.value("ApiUrlCheckout")).jsonPath().get("payment_form_url");
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver);
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }
}
