package com.paysuper.appmanager.helpers;

import org.jsoup.Jsoup;

import java.io.*;
import java.util.*;
import javax.activation.UnsupportedDataTypeException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.IOException;
import org.jsoup.nodes.Element;

public class MailParser {
    public MailParser() {
    }
    public static Object getMail() throws MessagingException, IOException {
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
          System.out.println(properties.getProperty("email"));
            store.connect("imap.gmail.com", properties.getProperty("email"), properties.getProperty("password"));
            folder = store.getFolder("Inbox");
            /* Others GMail folders :
             * [Gmail]/All Mail   This folder contains all of your Gmail messages.
             * [Gmail]/Drafts     Your drafts.
             * [Gmail]/Sent Mail  Messages you sent to other people.
             * [Gmail]/Spam       Messages marked as spam.
             * [Gmail]/Starred    Starred messages.
             * [Gmail]/Trash      Messages deleted from Gmail.
             */
            folder.open(Folder.READ_WRITE);
            SearchTerm sender = new FromTerm(new InternetAddress(properties.getProperty("internetAddress")));
            SearchTerm subject = new SubjectTerm(properties.getProperty("subjectTerm"));
            Message[] messages = folder.search(new AndTerm(sender, subject));
            //  Message[] messages = folder.search(sender);


            for (int i = 0; i < messages.length; ++i) {
                System.out.println("MESSAGE #" + (i + 1) + ":");
                Message msg = messages[i];
                content = msg.getContent();
/*
          if we don''t want to fetch messages already processed
          if (!msg.isSet(Flags.Flag.SEEN)) {
             String from = "unknown";
             ...
          }
        */

            }
        } finally {
            if (folder != null) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }

        return content;
    }

    public static String getContent(Object content) throws MessagingException, IOException {

        String body = null;
        //   Object content = msg.getContent();
        if (content instanceof MimeMultipart) {
            MimeMultipart multipart = (MimeMultipart) content;
            if (multipart.getCount() > 0) {
                BodyPart part = multipart.getBodyPart(0);
                content = part.getContent();
                body = content.toString();
            }
        } else if (content instanceof String) {
            body = (String) content;
        } else
            throw new UnsupportedDataTypeException();


        return body;
    }

//    public static void main(String args[]) throws Exception {
//
//        //  ReceiveMailImap.getContent(getMail());
//        String HTMLSTring = MailParser.getContent(getMail());
//        org.jsoup.nodes.Document html = Jsoup.parse(HTMLSTring);
//        Element link = html.select("a").first();
//        String relHref = link.attr("href"); // == "/"
//        System.out.println(relHref);
//    }
}