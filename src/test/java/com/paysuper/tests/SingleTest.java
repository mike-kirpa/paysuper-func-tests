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
    @Test(groups = { "tst", "stg", "onboarding"})
    public void AllStepsOnboardingTest(){
        app.driver.get("https://" + System.getenv("HELLO_WORLD"));
    }
}

