package com.paysuper.appmanager.helpers;

import org.apache.commons.mail.util.MimeMessageParser;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.IOException;


public class MailParser {
    public MailParser() {
    }
    public static Object getMail() throws IOException {
        Folder folder = null;
        Store store = null;
        Object content = null;
        Properties properties = new Properties();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        FileReader fileReader = new FileReader(
                MailParser.class.getClassLoader().getResource("tst.properties").getFile()
        );
        properties.load(fileReader);
        System.out.println(properties.getProperty("email"));
        try {
            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", "autotest.protocolone@gmail.com", System.getenv("autotest_email_pass"));
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
            SearchTerm sender = new FromTerm(new InternetAddress("googlecommunityteam-noreply@google.com"));
            SearchTerm subject = new SubjectTerm(properties.getProperty("subjectTerm"));
            //Message[] messages = folder.search(new AndTerm(sender, subject));
            Message[] messages = folder.search(sender);

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
        return content;
    }

    public static String readHtmlContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getHtmlContent();
    }
}