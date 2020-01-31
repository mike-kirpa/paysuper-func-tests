package com.paysuper.appmanager.helpers;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

public class MailParser {

    public MailParser() {}

    //
    // inspired by :
    // http://www.mikedesjardins.net/content/2008/03/using-javamail-to-read-and-extract/
    //

    public String getLink(String mail, String pass) throws MessagingException, IOException {
        Folder folder = null;
        Store store = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com",mail, pass);
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
            //Message messages[] = folder.getMessages();
            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());

            SearchTerm sender = new FromTerm(new InternetAddress("thepostmanteam@getpostman.com"));
            Message[] messages = folder.search(sender);
            for (int i=0; i < messages.length; ++i) {
                System.out.println("MESSAGE #" + (i + 1) + ":");
                Message msg = messages[i];
        /*
          if we don''t want to fetch messages already processed
          if (!msg.isSet(Flags.Flag.SEEN)) {
             String from = "unknown";
             ...
          }
        */
                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getReplyTo()[0].toString();
                }
                else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }
                String subject = msg.getSubject();
                Object content = msg.getContent();

                System.out.println("Received from " + (msg.getFrom()[0]));
                ///   System.out.println("Received from "+((InternetAddress)((Address)(msg.getFrom()[0]))).getAddress());
                System.out.println("Subject ... " + subject +" " + from);

                System.out.println("Body ... " + content +" " + from);
                // you may want to replace the spaces with "_"
                // the TEMP directory is used to store the files
                // msg.setFlag(Flags.Flag.SEEN,true);
                // to delete the message
                // msg.setFlag(Flags.Flag.DELETED, true);



                try {
                    Object contents = msg.getContent();
                    if (content instanceof Multipart) {
                        StringBuffer messageContent = new StringBuffer();
                        Multipart multipart = (Multipart) contents;
                        for (int j = 0; j < multipart.getCount(); j++) {
                            Part part = multipart.getBodyPart(i);
                            if (part.isMimeType("text/plain")) {
                                messageContent.append(part.getContent().toString());
                            }
                        }  System.out.println(messageContent.toString());
                        return messageContent.toString();

                    }
                    return content.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        finally {
            if (folder != null) { folder.close(true); }
            if (store != null) { store.close(); }
        }

        return "";
    }
}