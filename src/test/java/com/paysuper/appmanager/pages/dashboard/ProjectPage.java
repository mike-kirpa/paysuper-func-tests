package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class ProjectPage extends AbstractPage {

    public ProjectPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardProjectPage.CreateProjectButton");
    }

    public void clickOnCreateProjectButton(){
        driver.findElement(Locators.get("DashboardProjectPage.CreateProjectButton")).click();
        waitForElementLoad(Locators.get("DashboardProjectPage.CreateProjectFormText"));
    }

    public void typeProjectName(String projectName){
        driver.findElement(Locators.get("DashboardProjectPage.FormProjectNameField")).sendKeys(projectName);
    }

    public void clickOnCreateButton(String projectName){
        driver.findElement(Locators.get("DashboardProjectPage.FormCreateButton")).click();
        waitForElementLoad(Locators.get(String.format("DashboardProjectPage.ProjectNameText"), projectName));
    }


    public void createNewProject(String projectName){
        clickOnCreateProjectButton();
        typeProjectName(projectName);
        clickOnCreateButton(projectName);
    }

    public DashboardMainPage clickOnHomeLogo(){
        driver.findElement(Locators.get("DashboardMainPage.HomeLogo")).click();
        return new DashboardMainPage(driver);
    }
}
