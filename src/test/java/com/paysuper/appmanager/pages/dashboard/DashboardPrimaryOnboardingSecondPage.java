package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardPrimaryOnboardingSecondPage extends AbstractPage {
    public DashboardPrimaryOnboardingSecondPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingSecondPage.StepCounterText");
    }

    public void selectCheckbox(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingSecondPage.Checkboxes"), false);
    }

    public DashboardPrimaryOnboardingThirdPage successSecondPagePrimaryOnboarding(){
        selectCheckbox();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingThirdPage(driver);
    }
}
