package com.paysuper.appmanager.helpers;

import com.paysuper.appmanager.models.Email;
import org.apache.commons.mail.util.MimeMessageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.*;
import java.io.IOException;


public class MailParser {

    private String user_login_for_email;
    private String user_pass_for_email;
    private String PaySuperSender = "paysuper-team@pay.super.com";
    private String EmailSubject;
    private Email email;
    private String EmailRecipient;


    public MailParser(String user_login_for_email, String user_pass_for_email, Email email) {
    this.user_login_for_email = user_login_for_email;
    this.user_pass_for_email = user_pass_for_email;
    this.email = email;
    EmailRecipient = email.getEmailRecipient();
    }

    public Document getMail(String EmailSubject){
        this.EmailSubject = EmailSubject;
        Folder folder = null;
        Store store = null;
        Object content = null;
        org.jsoup.nodes.Document html = null;
        int GetMailCount = 3;

        Properties properties = new Properties();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");


        try {
            do {

                Session session = Session.getDefaultInstance(props, null);
                // session.setDebug(true);
                store = session.getStore("imaps");
                store.connect("imap.gmail.com", user_login_for_email, user_pass_for_email);
                folder = store.getFolder("Inbox");
                /* Others GMail folders :
                 * [Gmail]/All Mail   This folder contains all of your Gmail messages.
                 * [Gmail]/Drafts     Your drafts.
                 * [Gmail]/Sent Mail  Messages you sent to other people.
                 * [Gmail]/Spam       Messages marked as spam.
                 * [Gmail]/Starred    Starred messages.
                 * [Gmail]/Trash      Messages deleted from Gmail.
                 */
                folder.open(Folder.READ_ONLY);
                SearchTerm sender = new FromTerm(new InternetAddress(PaySuperSender));
                SearchTerm subject = new SubjectTerm(EmailSubject);
                SearchTerm recipient = new RecipientTerm(Message.RecipientType.TO, new InternetAddress(EmailRecipient));
                Message[] messages = folder.search(new AndTerm(recipient, subject));
                //Message[] messages = folder.search(recipient);
                for (int i = 0; i < messages.length; ++i) {
                    MimeMessage msg = (MimeMessage) messages[i];
                    content = readHtmlContent(msg);
                }
                GetMailCount--;
                Thread.sleep(5000);
            }while (content == null && GetMailCount > 0 );
            html = Jsoup.parse((String) content);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public String readHtmlContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getHtmlContent();
    }

    public void parseVerifyEmail(){
        org.jsoup.nodes.Document html = getMail(email.getSubject());
        Element link = html.selectFirst("a:contains(Verify My Email)");
        email.setVerifyHref(link.attr("href"));
    }

    public void parsePurchaseCheck(){
        org.jsoup.nodes.Document html = getMail(email.getSubject());
        //Get "Total" from email and set in Email
        email.setTotal(html.selectFirst("td:nth-child(2)>b").text());
        //Get "Transaction Date" from email and set in Email
        email.setTransactionDate(html.selectFirst("tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td:nth-child(2)").text());
        //Get "Transaction ID" from email and set in Email
        email.setTransactionID(html.selectFirst("tr:nth-child(4) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2)").text());
        //Get "Merchant Name" from email and set in Email
        email.setMerchantName(html.selectFirst("td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2)").text());
        //Get "Payment Partner" from email and set in Email
        email.setPaymentPartner(html.selectFirst("td:nth-child(2) > table > tbody > tr:nth-child(3) > td:nth-child(2)").text());
        //Get "WebCheckUrl" from email and set in Email
        email.setWebCheckUrl(html.selectFirst("a:contains(link)").attr("href"));
    }

    public void parseRefundedCheck(){
        org.jsoup.nodes.Document html = getMail(email.getSubject());
        //Get "Total" from email and set in Email
        email.setTotal(html.selectFirst("td:nth-child(2)>b").text());
        //Get "Transaction Date" from email and set in Email
        email.setTransactionDate(html.selectFirst("tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td:nth-child(2)").text());
        //Get "Transaction ID" from email and set in Email
        email.setTransactionID(html.selectFirst("tr:nth-child(4) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2)").text());
        //Get "Merchant Name" from email and set in Email
        email.setMerchantName(html.selectFirst("td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2)").text());
        //Get "Payment Partner" from email and set in Email
        email.setPaymentPartner(html.selectFirst("td:nth-child(2) > table > tbody > tr:nth-child(3) > td:nth-child(2)").text());
        //Get "WebCheckUrl" from email and set in Email
        email.setWebCheckUrl(html.selectFirst("a:contains(link)").attr("href"));
    }


}