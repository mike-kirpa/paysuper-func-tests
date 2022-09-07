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

    public DashboardMainPage VerifyEmail(MailParser mailParser) throws InterruptedException {
        mailParser.parseVerifyEmail();
        driver.get(mailParser.getEmail().getVerifyHref());
        return new DashboardMainPage(driver);
    }
}
