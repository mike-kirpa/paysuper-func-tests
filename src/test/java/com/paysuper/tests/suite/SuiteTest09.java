package com.paysuper.tests.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class SuiteTest09 extends TestBase {

    @Test
    public void test_09() throws Exception {
        app.driver.get("https://www.google.com/ncr");
        Thread.sleep(3000);
        WebElement element = app.driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 09");
        Thread.sleep(3000);
        element.submit();
        Thread.sleep(3000);

        Assert.assertEquals("BrowserStack Test 09 - Google Search", app.driver.getTitle());
    }
}
