package com.paysuper.tests;

import com.github.javafaker.Faker;
import com.paysuper.appmanager.ApplicationManager;
import org.testng.annotations.*;

public class TestBase {
    public Faker faker = new Faker();

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeClass(alwaysRun = true)
    @org.testng.annotations.Parameters(value = { "config", "environment", "zone", "localrun"})
    public void setUp(String config_file, String environment, String zone, boolean localrun) throws Exception {
        app.init(config_file, environment, zone, localrun);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
