package com.paysuper.tests;

import com.paysuper.appmanager.pages.analytics.AnalyticsLogin;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test(enabled = true, description="success simple payment https://protocolone.tpondemand.com/entity/193560-web-payform-uspeshnaya-pokupka-simp")
    public void SimpleOrderSuccessPayTest() {
        String payment_form_url = app.restAPI.createSimpleOrder(
                app.getProperties.value("ProjectId"),
                Integer.parseInt(app.getProperties.value("Amount")),
                app.getProperties.value("Currency"),
                app.getProperties.value("ApiUrlCheckout"));
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(app.getProperties.value("ValidEmail"));
        payFormPage.clickPayButton();
        org.testng.Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        org.testng.Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), app.getProperties.value("ValidEmail"));
    }
}
