package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardMainPage extends AbstractPage {
    public DashboardMainPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardMainPage.FeedbackLink");
    }

    public DashboardTransactionsPage clickOnTransactionSearchLink(){
        driver.findElement(Locators.get("DashboardMainPage.TransactionSearchLink")).click();
        return new DashboardTransactionsPage(driver);
    }

    public DashboardGeneralOnboardingPage clickOnActivateLiveModeButton(){
        driver.findElement(Locators.get("DashboardMainPage.ActivateLiveModeButton")).click();
        return new DashboardGeneralOnboardingPage(driver);
    }

    public void clickOnStepCounterButton(){
        waitForElementLoad(Locators.get("DashboardMainPage.StepCounterButton"));
        driver.findElement(Locators.get("DashboardMainPage.StepCounterButton")).click();
    }

    public void clickOnProfileIcon(){
        driver.findElement(Locators.get("DashboardMainPage.ProfileIcon")).click();
    }

    public DashboardLoginPage clickOnLogoutInProfileMenu(){
            driver.findElement(Locators.get("DashboardMainPage.LogoutInProfileIcon")).click();
            return new DashboardLoginPage(driver);
    }

    public ProjectPage clickOnProjectLink(){
        waitForClickAbleElement(Locators.get("DashboardMainPage.ProjectLink"));
        driver.findElement(Locators.get("DashboardMainPage.ProjectLink")).click();
        return new ProjectPage(driver);
    }
}
