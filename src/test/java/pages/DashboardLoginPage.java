package pages;

import helpers.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardLoginPage extends AbstractPage {
//    private By buttonSignIn = By.xpath("//span[contains(text(),'Sign in or Sign up')]/..");
    String buttonText;

    public DashboardLoginPage(WebDriver driver) {
        super(driver);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(Locators.get("DashboardLoginPage.SignInButton")));
    }
}
