package com.paysuper.tests.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class SuiteTest10 extends TestBase {

    @Test
    public void test_10() throws Exception {
        app.driver.get("https://www.google.com/ncr");
        WebElement element = app.driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 10");
        element.submit();
        Thread.sleep(10000);

        Assert.assertEquals("BrowserStack Test 10 - Google Search", app.driver.getTitle());
    }
}
