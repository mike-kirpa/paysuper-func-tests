package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class _DashboardPrimaryOnboardingSecondPage extends AbstractPage {
    public _DashboardPrimaryOnboardingSecondPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingSecondPage.StepCounterText");
    }

    public void selectCheckbox(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingSecondPage.Checkboxes"), false);
    }

    public DashboardPrimaryOnboardingSecondPage successSecondPagePrimaryOnboarding(){
        selectCheckbox();
        clickOnNextButton();
        return new DashboardPrimaryOnboardingSecondPage(driver);
    }
}
