package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardMainPage extends AbstractPage {
    public DashboardMainPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardMainPage.WelcomeText");
    }
}
