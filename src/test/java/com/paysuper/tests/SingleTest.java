package com.paysuper.tests;

import org.testng.annotations.Test;
import com.paysuper.appmanager.pages.DemoSdkPage;

public class SingleTest extends TestBase {

    @Test
    public void test() throws Exception {
        app.http.post();
//        app.driver.get(app.getProperties.value("DemoSdkUrl"));
//        DemoSdkPage demoSdkPage = new DemoSdkPage(app.driver);
 //       demoSdkPage.createSimpleOrder(app.getProperties.value("projectid"), app.getProperties.value("amount"), app.getProperties.value("currency"));

//        Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());

    }
}
