package com.paysuper.tests;

import com.paysuper.appmanager.pages.PayFormPage;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test()
    public void test() throws Exception {
        String payment_form_url = app.restAPI.createOrder(
                app.getProperties.value("projectid"),
                Integer.valueOf(app.getProperties.value("amount")),
                app.getProperties.value("currency")).jsonPath().get("payment_form_url");
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver);
    }
}
