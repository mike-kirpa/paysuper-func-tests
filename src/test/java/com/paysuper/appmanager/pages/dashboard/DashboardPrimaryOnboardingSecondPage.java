package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPrimaryOnboardingSecondPage extends AbstractPage {
    public DashboardPrimaryOnboardingSecondPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingThirdPage.StepCounterText");
    }

    public void clickDAOType(){
        By by = Locators.get("DashboardPrimaryOnboardingThirdPage.DAOradio");
        WebElement element = driver.findElement(by);
        element.click();
    }

    public DashboardPrimaryOnboardingThirdPage successSecondPagePrimaryOnboarding(){
        clickOnNextButton();
        return new DashboardPrimaryOnboardingThirdPage(driver);
    }

    public DashboardPrimaryOnboardingThirdPage successDAOSecondPagePrimaryOnboarding(){
        clickDAOType();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingThirdPage(driver);
    }
}
