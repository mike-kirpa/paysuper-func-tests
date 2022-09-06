package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardPrimaryOnboardingSecondPage extends AbstractPage {
    public DashboardPrimaryOnboardingSecondPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingThirdPage.StepCounterText");
    }

    public DashboardPrimaryOnboardingThirdPage successSecondPagePrimaryOnboarding(){
        clickOnNextButton();
        return new DashboardPrimaryOnboardingThirdPage(driver);
    }
}
