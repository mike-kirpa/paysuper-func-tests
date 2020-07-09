package com.paysuper.tests;


import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.pages.PayFormPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import com.paysuper.appmanager.pages.analytics.AnalyticsLogin;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.mail.MessagingException;
import java.io.IOException;

import static com.paysuper.appmanager.helpers.MailParser.getMail;


public class SingleTest extends TestBase {

    @Test()
    public void test() throws Exception {
        String HTMLSTring = (String) MailParser.getMail();
        org.jsoup.nodes.Document html = Jsoup.parse(HTMLSTring);
        Element link = html.selectFirst("a:contains(Аккаунт Google)");
        String relHref = link.attr("href"); // == "/"
        System.out.println(relHref);

    }
}
