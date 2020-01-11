package com.paysuper.tests.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OrderPaymentTest extends TestBase {

    @Test
    public void test_01() throws Exception {
        app.driver.get("https://www.google.com/ncr");
        WebElement element = app.driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 01");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("BrowserStack Test 01 - Google Search", app.driver.getTitle());
    }
}
