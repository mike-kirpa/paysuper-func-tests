package com.paysuper.tests;

import com.paysuper.appmanager.pages.PayFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test(enabled = true, description="success token payment https://protocolone.tpondemand.com/entity/193349-web-payform-uspeshnaya-pokupka-s-pomoshyu")
    public void TokenOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createTokenOrder(
                app.getProperties.value("ApiUrl"));
        System.out.println(payment_form_url);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver, app.getProperties.value("DefaultLanguage"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
//        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }
}
