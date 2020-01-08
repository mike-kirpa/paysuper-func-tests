package com.paysuper;

import org.testng.annotations.Test;
import pages.DashboardLoginPage;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
        driver.get("https://paysupermgmt.tst.protocol.one/");
        DashboardLoginPage DashboardLoginPage = new DashboardLoginPage(driver);
 /*       WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());
*/
    }
}
