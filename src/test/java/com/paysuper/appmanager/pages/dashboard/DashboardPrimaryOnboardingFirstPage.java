package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class DashboardPrimaryOnboardingFirstPage extends AbstractPage {
    public DashboardPrimaryOnboardingFirstPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingFirstPage.StepCounterText");
    }

    public void enterFisrtName(String FisrtName){
        driver.findElement(Locators.get("DashboardPrimaryOnboardingFirstPage.FirstNameField")).sendKeys(FisrtName);
    }

    public void enterLastName(String LastName){
        driver.findElement(Locators.get("DashboardPrimaryOnboardingFirstPage.LastNameField")).sendKeys(LastName);
    }

    private void clickOnNextButton(){
        driver.findElement(Locators.get("DashboardPrimaryOnboarding.NextButton")).click();
    }

    public void selectPosition(){
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingFirstPage.PositionField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"));
    }

    public DashboardPrimaryOnboardingSecondPage successFirstStepPrimaryOnboarding(String FisrtName, String LastName) throws InterruptedException {
        enterFisrtName(FisrtName);
        enterLastName(LastName);
        selectPosition();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingSecondPage(driver);
    }
}
