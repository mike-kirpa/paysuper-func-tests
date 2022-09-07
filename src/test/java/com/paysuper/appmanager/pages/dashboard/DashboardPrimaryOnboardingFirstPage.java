package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPrimaryOnboardingFirstPage extends AbstractPage {
    public DashboardPrimaryOnboardingFirstPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        waitForElementLoad("DashboardPrimaryOnboardingFirstPage.StepCounterText");
    }

    public void enterFisrtName(String FisrtName){
        waitForClickAbleElement(Locators.get("DashboardPrimaryOnboardingFirstPage.FirstNameField"));
        driver.findElement(Locators.get("DashboardPrimaryOnboardingFirstPage.FirstNameField")).sendKeys(FisrtName);
    }

    public void enterLastName(String LastName){
        waitForClickAbleElement(Locators.get("DashboardPrimaryOnboardingFirstPage.LastNameField"));
        driver.findElement(Locators.get("DashboardPrimaryOnboardingFirstPage.LastNameField")).sendKeys(LastName);
    }

    public void selectPosition(){
        waitForClickAbleElement(Locators.get("DashboardPrimaryOnboardingFirstPage.PositionField"));
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingFirstPage.PositionField"), By.className("option"), false, 0);
    }

    public DashboardPrimaryOnboardingSecondPage successFirstStepPrimaryOnboarding(String FisrtName, String LastName) throws InterruptedException {
        enterFisrtName(FisrtName);
        enterLastName(LastName);
        selectPosition();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingSecondPage(driver);
    }
}
