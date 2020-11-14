package com.paysuper.tests.suite;

import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.pages.dashboard.*;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OnboardingTest extends TestBase {

    @Test(enabled = true, groups = {"tst", "stg", "onboarding"})
    public void test() throws Exception {
        Email email = new Email();
        String unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String generated_user_email = "autotest.protocolone+" + unixTime + "@gmail.com";
        String generated_user_pass = "Q" + unixTime;
        email.setEmailRecipient(generated_user_email);
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.clickOnSignInButton();
        DashboardRegistrationPage dashboardRegistrationPage = dashboardLoginPage.clickOnSignUpButton();
        DashboardPrimaryOnboardingFirstPage dashboardPrimaryOnboardingFirstPage
                = dashboardRegistrationPage.successRegistration(generated_user_email, generated_user_pass);
        DashboardPrimaryOnboardingSecondPage dashboardPrimaryOnboardingSecondPage
                = dashboardPrimaryOnboardingFirstPage.successFirstStepPrimaryOnboarding(faker.name().firstName().replaceAll("'",""), faker.name().lastName().replaceAll("'",""));
        DashboardPrimaryOnboardingThirdPage dashboardPrimaryOnboardingThirdPage
                = dashboardPrimaryOnboardingSecondPage.successSecondPagePrimaryOnboarding();
        DashboardVerifyEmailPage dashboardVerifyEmailPage = dashboardPrimaryOnboardingThirdPage.successThirdPagePrimaryOnboarding(faker.funnyName().name().replaceAll("'",""), faker.company().url());
//        Thread.sleep(5000);
        DashboardMainPage dashboardMainPage = dashboardVerifyEmailPage.VerifyEmail(
                app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        DashboardGeneralOnboardingPage dashboardGeneralOnboardingPage = dashboardMainPage.clickOnActivateLiveModeButton();
        dashboardGeneralOnboardingPage.enterTextInLegalnameField(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInWebsiteFiled(faker.company().url());
        dashboardGeneralOnboardingPage.enterTextInOperatingName(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInRegistrationNumberField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInVatNumberField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInCountryField("Czech Republic");
        dashboardGeneralOnboardingPage.enterTextInCityField("Prague");
        dashboardGeneralOnboardingPage.enterTextInZipCodeField("12501");
        dashboardGeneralOnboardingPage.enterTextInAddress1Field(faker.address().fullAddress().replaceAll("'",""));
        dashboardGeneralOnboardingPage.clickOnAccountSubmitButton();
        dashboardGeneralOnboardingPage.clickOn3rdStepContactsButton();
        dashboardGeneralOnboardingPage.enterTextInNameRepresentativeField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInPositionRepresentativeField(faker.job().position());
        dashboardGeneralOnboardingPage.enterTextInEmailRepresentativeField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneRepresentativeField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInNameTechnicalField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInEmailTechnicalField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneTechnicalField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitContactsButton();
        dashboardGeneralOnboardingPage.clickOn4rdStepBankingInfoButton();
        dashboardGeneralOnboardingPage.enterTextInSwiftField("COBADEFF");
        dashboardGeneralOnboardingPage.selectAccountCurrency();
        dashboardGeneralOnboardingPage.enterTextInIbanField("DE89370400440532013000");
        dashboardGeneralOnboardingPage.enterTextInBankNameField(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInBankAddressField(faker.address().fullAddress().replaceAll("'",""));
        dashboardGeneralOnboardingPage.clickOnSubmitBankingInfoButton();
        dashboardGeneralOnboardingPage.clickOn5rdStepPaymentMethodsButton();
        dashboardGeneralOnboardingPage.selectTheMainSalesRegion();
        dashboardGeneralOnboardingPage.selectRiskLevel();
        dashboardGeneralOnboardingPage.clickOnSubmitApplicationButton();
    }
}
