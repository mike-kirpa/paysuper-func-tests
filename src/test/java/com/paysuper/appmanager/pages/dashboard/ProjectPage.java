package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public boolean isProjectExist(String projectName){
        return isElementPresent(Locators.get(String.format("DashboardProjectPage.ProjectNameInListText"), projectName));
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

    public void clickOnProjectInList(){
        waitForClickAbleElement(Locators.get("DashboardProjectPage.ProjectLinkInList"));
        List<WebElement> elements = driver.findElements(Locators.get("DashboardProjectPage.ProjectLinkInList"));
        elements.get(0).click();
    }

    public void clickOnVirtualCurrencyLink(){
        clickOnElement(Locators.get("DashboardProjectPage.VirtualCurrencyLink"));
    }

    public void clickOnVirtualItemsLink(){
        clickOnElement(Locators.get("DashboardProjectPage.VirtualItemsLink"));
    }

    public void clickOnGameKeysLink(){
        clickOnElement(Locators.get("DashboardProjectPage.GameKeysLink"));
    }
}
