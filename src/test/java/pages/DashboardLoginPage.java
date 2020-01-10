package pages;

import helpers.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardLoginPage extends AbstractPage {

    public DashboardLoginPage(WebDriver driver) {
        super(driver);
        waitForPageLoad("DashboardLoginPage.SignInButton");
    }
}
