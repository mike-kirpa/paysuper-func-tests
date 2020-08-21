package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPrimaryOnboardingSecondPage extends AbstractPage {
    public DashboardPrimaryOnboardingSecondPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingSecondPage.StepCounterText");
    }

    public void selectCheckbox(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingSecondPage.Checkboxes"));
    }

    private void clickOnNextButton(){
        driver.findElement(Locators.get("DashboardPrimaryOnboarding.NextButton")).click();
    }

    public DashboardPrimaryOnboardingThirdPage successSecondPagePrimaryOnboarding(){
        selectCheckbox();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingThirdPage(driver);
    }
}
