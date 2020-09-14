package com.paysuper.tests;



import com.paysuper.appmanager.pages.dashboard.*;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {



    @Test(enabled = true, groups = {"tst", "stg", "prod"})
    public void test() throws Exception {
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

