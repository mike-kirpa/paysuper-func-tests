package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardPrimaryOnboardingThirdPage extends AbstractPage {
    public DashboardPrimaryOnboardingThirdPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingThirdPage.StepCounterText");
    }
    public void enterCompanyName(String CompanyName){
        driver.findElement(Locators.get("DashboardPrimaryOnboardingThridPage.CompanyNameField")).sendKeys(CompanyName);
    }

    public void enterWebsiteName(String WebsiteName){
        driver.findElement(Locators.get("DashboardPrimaryOnboardingThridPage.WebsiteField")).sendKeys(WebsiteName);
    }

    public void selectAnnualIncome(){
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingThridPage.AnnualIncomeField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false);
    }

    public void selectNumberOfEmployees(){
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingThridPage.NumberOfEmployeesField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false);
    }

    public void clickOnTypeOfBusiness(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.TypeOfBusinessRadiobutton"), false);
    }

    public void clickOnBusinessModel(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.BusinessModelCheckboxes"), false);
    }

    public void clickOnSupportedPlatforms(){
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.SupportedPlatformsCheckboxes"), false);
    }

    public void clickOnDoneButton(){
        driver.findElement(Locators.get("DashboardPrimaryOnboardingThridPage.DoneButton")).click();
    }



    public DashboardVerifyEmailPage successThirdPagePrimaryOnboarding(String CompanyName, String WebsiteName) throws InterruptedException {
        enterCompanyName(CompanyName);
        enterWebsiteName(WebsiteName);
        selectAnnualIncome();
        selectNumberOfEmployees();
        clickOnTypeOfBusiness();
        clickOnBusinessModel();
        clickOnSupportedPlatforms();
        clickOnDoneButton();
        return new DashboardVerifyEmailPage(driver);
    }
}
