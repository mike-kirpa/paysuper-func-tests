package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.pages.AbstractPage;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class DashboardVerifyEmailPage extends AbstractPage {
    public DashboardVerifyEmailPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardVerifyEmailPage.Text");
    }

    public DashboardMainPage VerifyEmail(String user_login_for_email, String autotest_email_pass, Email email) throws InterruptedException {
        MailParser mailParser = new MailParser(user_login_for_email, autotest_email_pass, email);
        mailParser.parseVerifyEmail();
        driver.get(email.getVerifyHref());
        return new DashboardMainPage(driver);
    }
}
