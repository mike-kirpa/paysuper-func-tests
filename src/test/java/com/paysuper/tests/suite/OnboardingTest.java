package com.paysuper.tests.suite;

import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.pages.dashboard.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OnboardingTest extends TestBase {

    @Test(groups = { "tst", "stg", "onboarding"})
    public void AllStepsOnboardingTest() throws Exception {
        Email email = new Email();
        String unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String generated_user_email = "autotest.protocolone+" + unixTime + "@gmail.com";
        String generated_user_pass = "Q" + unixTime;
        email.setEmailRecipient(generated_user_email);
        email.setSubject(GetProperties.value("EmailVerificationSubject"));
        app.driver.get(GetProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.clickOnSignInButton();
        DashboardRegistrationPage dashboardRegistrationPage = dashboardLoginPage.clickOnSignUpButton();
        DashboardPrimaryOnboardingFirstPage dashboardPrimaryOnboardingFirstPage
                = dashboardRegistrationPage.successRegistration(generated_user_email, generated_user_pass);
        DashboardPrimaryOnboardingSecondPage dashboardPrimaryOnboardingSecondPage
                = dashboardPrimaryOnboardingFirstPage.successFirstStepPrimaryOnboarding(faker.name().firstName().replaceAll("'",""), faker.name().lastName().replaceAll("'",""));
        DashboardPrimaryOnboardingThirdPage dashboardPrimaryOnboardingThirdPage
                = dashboardPrimaryOnboardingSecondPage.successSecondPagePrimaryOnboarding();
        DashboardVerifyEmailPage dashboardVerifyEmailPage
                = dashboardPrimaryOnboardingThirdPage.successThirdPagePrimaryOnboarding(faker.funnyName().name().replaceAll("'",""), faker.company().url());
        DashboardMainPage dashboardMainPage = dashboardVerifyEmailPage.VerifyEmail(
                GetProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        //1 step - Projects
        dashboardMainPage.clickOnStepCounterButton();
        ProjectPage projectPage = new ProjectPage(app.driver);
        projectPage.createNewProject(unixTime);
        //2 step - Account Info
        dashboardMainPage = projectPage.clickOnHomeLogo();
        dashboardMainPage.clickOnStepCounterButton();
        DashboardGeneralOnboardingPage dashboardGeneralOnboardingPage = new DashboardGeneralOnboardingPage(app.driver);
        dashboardGeneralOnboardingPage.enterTextInLegalnameField(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInWebsiteFiled(faker.company().url());
        dashboardGeneralOnboardingPage.enterTextInOperatingName(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInRegistrationNumberField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInVatField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInCountryField("Czech Republic");
        dashboardGeneralOnboardingPage.enterTextInCityField("Prague");
        dashboardGeneralOnboardingPage.enterTextInZipCodeField("12501");
        dashboardGeneralOnboardingPage.enterTextInAddress1Field(faker.address().fullAddress().replaceAll("'",""));
        dashboardGeneralOnboardingPage.clickOnAccountSubmitButton();
        //3 step -Contacts
        dashboardGeneralOnboardingPage.clickOn3rdStepContactsButton();
        dashboardGeneralOnboardingPage.enterTextInNameRepresentativeField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInPositionRepresentativeField(faker.job().position());
        dashboardGeneralOnboardingPage.enterTextInEmailRepresentativeField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneRepresentativeField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInNameTechnicalField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInEmailTechnicalField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneTechnicalField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitContactsButton();
        //4th step - Banking Info
        dashboardGeneralOnboardingPage.clickOn4rdStepBankingInfoButton();
        dashboardGeneralOnboardingPage.enterTextInSwiftField("COBADEFF");
        dashboardGeneralOnboardingPage.selectAccountCurrency();
        dashboardGeneralOnboardingPage.enterTextInIbanField("DE89370400440532013000");
        dashboardGeneralOnboardingPage.enterTextInBankNameField(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInBankAddressField(faker.address().fullAddress().replaceAll("'","").substring(0, 60));
        dashboardGeneralOnboardingPage.clickOnSubmitBankingInfoButton();
        //5th step - Payment Methods
        dashboardGeneralOnboardingPage.clickOn5rdStepPaymentMethodsButton();
        dashboardGeneralOnboardingPage.selectTheMainSalesRegion();
        dashboardGeneralOnboardingPage.clickOnSubmitApplicationButton();
        //6th step - Company Documents
        dashboardGeneralOnboardingPage.clickOn6thStepCompanyDocumentsButton();
        dashboardGeneralOnboardingPage.sendFilePath();
        dashboardGeneralOnboardingPage.typeFileTextInField(faker.regexify("[a-z]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitDocumentsButton();
//        Assert.assertTrue(DashboardGeneralOnboardingPage.isIncompletetStepNotPresense(), "There is incomplete onboarding step");
        Assert.assertEquals(dashboardGeneralOnboardingPage.getCurrentNameOfTheStep().substring(1).replaceFirst(".$",""),
                GetProperties.value("7Step"),
                "Current name of step not equal:" + GetProperties.value("7Step"));
    }
}
