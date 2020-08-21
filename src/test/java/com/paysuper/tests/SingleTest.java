package com.paysuper.tests;


import com.paysuper.appmanager.pages.dashboard.*;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test()
    public void test() throws Exception {


        String unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String generated_user_email = "autotest.protocolone+" + unixTime + "@gmail.com";
        String generated_user_pass = "Q" + unixTime;
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.clickOnSignInButton();
        DashboardRegistrationPage dashboardRegistrationPage = dashboardLoginPage.clickOnSignUpButton();
        DashboardPrimaryOnboardingFirstPage dashboardPrimaryOnboardingFirstPage
                = dashboardRegistrationPage.successRegistration(generated_user_email, generated_user_pass);
        DashboardPrimaryOnboardingSecondPage dashboardPrimaryOnboardingSecondPage
                = dashboardPrimaryOnboardingFirstPage.successFirstStepPrimaryOnboarding("FirstName", "LastName");
        DashboardPrimaryOnboardingThirdPage dashboardPrimaryOnboardingThirdPage
                = dashboardPrimaryOnboardingSecondPage.successSecondPagePrimaryOnboarding();
        DashboardVerifyEmailPage dashboardVerifyEmailPage = dashboardPrimaryOnboardingThirdPage.successThirdPagePrimaryOnboarding("CompanyName", "https://super.com");
        DashboardMainPage dashboardMainPage = dashboardVerifyEmailPage.VerifyEmail(
                app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                generated_user_email);
        DashboardGeneralOnboardingPage dashboardGeneralOnboardingPage = dashboardMainPage.clickOnActivateLiveModeButton();
        dashboardGeneralOnboardingPage.enterTextInLegalnameField("Legal name");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInWebsiteFiled("https://super.com");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInOperatingName("Operating name");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInRegistrationNumberField("0123456789");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInCountryField("Czech Republic");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInCityField("Prague");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInZipCodeField("12501");
        Thread.sleep(3000);
        dashboardGeneralOnboardingPage.enterTextInAddress1Field("Address 1");
    }
    }

