package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardPrimaryOnboardingThirdPage extends AbstractPage {
    public DashboardPrimaryOnboardingThirdPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingThirdPage.StepCounterText");
    }

    public DashboardPrimaryOnboardingFourthPage successFouthPagePrimaryOnboarding(){
        clickOnNextButton();
        return new DashboardPrimaryOnboardingFourthPage(driver);
    }
}
