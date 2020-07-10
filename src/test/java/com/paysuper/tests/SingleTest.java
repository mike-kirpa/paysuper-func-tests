package com.paysuper.tests;

import com.paysuper.appmanager.helpers.MailParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

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
