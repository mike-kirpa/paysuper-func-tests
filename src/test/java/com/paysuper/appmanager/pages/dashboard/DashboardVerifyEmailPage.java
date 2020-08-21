package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardVerifyEmailPage extends AbstractPage {
    public DashboardVerifyEmailPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardVerifyEmailPage.Text");
    }

    public DashboardMainPage VerifyEmail(String user_login_for_email, String autotest_email_pass, String generated_user_email) throws InterruptedException {
        Thread.sleep(3000);
        String HTMLSTring = (String) MailParser.getMail(
                user_login_for_email,
                autotest_email_pass,
                generated_user_email);
        driver.get(HTMLSTring);
        return new DashboardMainPage(driver);
    }

}
