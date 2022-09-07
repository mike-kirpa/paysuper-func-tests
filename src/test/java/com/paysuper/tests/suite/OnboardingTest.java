package com.paysuper.tests.suite;

import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.pages.dashboard.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OnboardingTest extends TestBase {

    @Test(groups = { "tst", "stg", "tst-crypto", "onboarding"})
    public void FiatOnboardingTest() throws Exception {
        Email email = new Email();
        String unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String generated_user_email = "autotest.protocolone+" + unixTime + "@gmail.com";
        String generated_user_pass = "Q" + unixTime;
        email.setEmailRecipient(generated_user_email);
        MailParser mailParser = new MailParser(GetProperties.value("user_login_for_email"),
                GetProperties.value("Email_pass"),
                email);

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
        DashboardMainPage dashboardMainPage = dashboardVerifyEmailPage.VerifyEmail(mailParser);

        //1 step - Account Info
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
        //2 step -Contacts
        dashboardGeneralOnboardingPage.clickOn3rdStepContactsButton();
        dashboardGeneralOnboardingPage.enterTextInNameRepresentativeField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInPositionRepresentativeField(faker.job().position());
        dashboardGeneralOnboardingPage.enterTextInEmailRepresentativeField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneRepresentativeField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.enterTextInNameTechnicalField(faker.name().firstName().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInEmailTechnicalField(generated_user_email);
        dashboardGeneralOnboardingPage.enterTextInPhoneTechnicalField(faker.regexify("[0-9]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitContactsButton();
        //3th step - Banking Info
        dashboardGeneralOnboardingPage.clickOn4rdStepBankingInfoButton();
        dashboardGeneralOnboardingPage.enterTextInSwiftField("COBADEFF");
        dashboardGeneralOnboardingPage.selectAccountCurrency();
        dashboardGeneralOnboardingPage.enterTextInIbanField("DE89370400440532013000");
        dashboardGeneralOnboardingPage.enterTextInBankNameField(faker.funnyName().name().replaceAll("'",""));
        dashboardGeneralOnboardingPage.enterTextInBankAddressField(faker.address().fullAddress().replaceAll("'",""));
        dashboardGeneralOnboardingPage.clickOnSubmitBankingInfoButton();
        //4th step - Payment Methods
        dashboardGeneralOnboardingPage.clickOn5rdStepPaymentMethodsButton();
        dashboardGeneralOnboardingPage.selectTheMainSalesRegion();
        dashboardGeneralOnboardingPage.clickOnSubmitApplicationButton();
        Assert.assertTrue(mailParser.isEmailExist(GetProperties.value("EmailRequestSubject")));

        //5th step - Company Documents
        dashboardGeneralOnboardingPage.clickOn6thStepCompanyDocumentsButton();
        dashboardGeneralOnboardingPage.sendFilePath();
        dashboardGeneralOnboardingPage.typeFileTextInField(faker.regexify("[a-z]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitDocumentsButton();

        mailParser.parseKYCEmail();
        app.driver.get(email.getUrl());

        //Projects
        ProjectPage projectPage = dashboardMainPage.clickOnProjectLink();
        Assert.assertTrue(projectPage.isProjectExist(GetProperties.value("DefaultProject")), "Default project not created");
        projectPage.clickOnHomeLogo();

//        Assert.assertTrue(DashboardGeneralOnboardingPage.isIncompletetStepNotPresense(), "There is incomplete onboarding step");
        Assert.assertEquals(dashboardGeneralOnboardingPage.getCurrentNameOfTheStep().substring(1).replaceFirst(".$",""),
                GetProperties.value("6Step"),
                "Current name of step not equal:" + GetProperties.value("6Step"));
    }

    @Test(groups = { "tst", "stg", "tst-crypto", "onboarding", "crypto"})
    public void CryptoOnboardingTest() throws Exception {
        Email email = new Email();
        String unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String generated_user_email = "autotest.protocolone+" + unixTime + "@gmail.com";
        String generated_user_pass = "Q" + unixTime;
        email.setEmailRecipient(generated_user_email);
        MailParser mailParser = new MailParser(GetProperties.value("user_login_for_email"),
                GetProperties.value("Email_pass"),
                email);

        app.driver.get(GetProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.clickOnSignInButton();
        DashboardRegistrationPage dashboardRegistrationPage = dashboardLoginPage.clickOnSignUpButton();
        DashboardPrimaryOnboardingFirstPage dashboardPrimaryOnboardingFirstPage
                = dashboardRegistrationPage.successRegistration(generated_user_email, generated_user_pass);
        DashboardPrimaryOnboardingSecondPage dashboardPrimaryOnboardingSecondPage
                = dashboardPrimaryOnboardingFirstPage.successFirstStepPrimaryOnboarding(faker.name().firstName().replaceAll("'",""), faker.name().lastName().replaceAll("'",""));
        DashboardPrimaryOnboardingThirdPage dashboardPrimaryOnboardingThirdPage
                = dashboardPrimaryOnboardingSecondPage.successDAOSecondPagePrimaryOnboarding();
        DashboardVerifyEmailPage dashboardVerifyEmailPage
                = dashboardPrimaryOnboardingThirdPage.successDAOThirdPagePrimaryOnboarding(faker.funnyName().name().replaceAll("'",""), faker.company().url());
        DashboardMainPage dashboardMainPage = dashboardVerifyEmailPage.VerifyEmail(mailParser);
        //1 step - Account Info
        dashboardMainPage.clickOnStepCounterButton();
        DashboardGeneralOnboardingPage dashboardGeneralOnboardingPage = new DashboardGeneralOnboardingPage(app.driver);
        //5th step - Company Documents
        dashboardGeneralOnboardingPage.clickOn6thStepCompanyDocumentsButton();
        dashboardGeneralOnboardingPage.sendFilePath();
        dashboardGeneralOnboardingPage.typeFileTextInField(faker.regexify("[a-z]{10}"));
        dashboardGeneralOnboardingPage.clickOnSubmitDocumentsButton();

        mailParser.parseKYCEmail();
        app.driver.get(email.getUrl());

        //Projects
        ProjectPage projectPage = dashboardMainPage.clickOnProjectLink();
        Assert.assertTrue(projectPage.isProjectExist(GetProperties.value("DefaultProject")), "Default project not created");
        projectPage.clickOnHomeLogo();

//        Assert.assertTrue(DashboardGeneralOnboardingPage.isIncompletetStepNotPresense(), "There is incomplete onboarding step");
        Assert.assertEquals(dashboardGeneralOnboardingPage.getCurrentNameOfTheStep().substring(1).replaceFirst(".$",""),
                GetProperties.value("6Step"),
                "Current name of step not equal:" + GetProperties.value("6Step"));
    }
}
