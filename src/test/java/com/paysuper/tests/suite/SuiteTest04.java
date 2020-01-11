package com.paysuper.tests.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class SuiteTest04 extends TestBase {

    @Test
    public void test_04() throws Exception {
        app.driver.get("https://www.google.com/ncr");
        WebElement element = app.driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 04");
        element.submit();
        Thread.sleep(7000);

        Assert.assertEquals("BrowserStack Test 04 - Google Search", app.driver.getTitle());
    }
}
