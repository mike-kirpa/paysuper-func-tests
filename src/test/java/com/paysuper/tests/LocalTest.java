package com.paysuper.tests;

import com.paysuper.appmanager.pages.dashboard.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocalTest extends TestBase {

    @Test
    public void test2() throws Exception {
        app.driver.get("http://bs-local.com:45691/check");

        Assert.assertTrue(app.driver.getPageSource().contains("Up and running"));
    }
}
