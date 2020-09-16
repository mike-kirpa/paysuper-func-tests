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
    EmailRecipient = this.email.getEmailRecipient();
    }

    public Document getMail(String EmailSubject){
        this.EmailSubject = EmailSubject;
        Folder folder = null;
        Store store = null;
        Object content = null;

        Properties properties = new Properties();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        try {
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
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        org.jsoup.nodes.Document html = Jsoup.parse((String) content);
        return html;
    }

    public String readHtmlContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getHtmlContent();
    }

    public void parseVerifyEmail(){
        String EmailSubject = "PaySuper E-mail Verification";
        org.jsoup.nodes.Document html = getMail(EmailSubject);
        Element link = html.selectFirst("a:contains(Verify My Email)");
        email.setVerifyHref(link.attr("href"));
    }
}