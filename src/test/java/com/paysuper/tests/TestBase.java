package com.paysuper.tests;

import com.github.javafaker.Faker;
import com.paysuper.appmanager.ApplicationManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.*;

public class TestBase {
    public Faker faker = new Faker();
    public final ApplicationManager app = new ApplicationManager();

    @BeforeClass(alwaysRun = true)
    @org.testng.annotations.Parameters(value = { "config", "environment", "zone", "localrun"})
    public void setUp(String config_file, String environment, String zone, boolean localrun) throws Exception {
        app.init(config_file, environment, zone, localrun);
    }

    @AfterMethod(alwaysRun = true)
    public void clear(){
        ((JavascriptExecutor) app.driver).executeScript("window.localStorage.clear();");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
