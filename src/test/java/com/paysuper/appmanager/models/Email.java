package com.paysuper.appmanager.models;

/**
  * Класс для хранения данных из email
  *
  * @version 1.01 2020-09-16
  * @author Mikhail Kyrpa
  */

public class Email {
    private String VerifyHref;
    private String EmailRecipient;


    public String getVerifyHref() {
        return VerifyHref;
    }

    public void setVerifyHref(String verifyHref) {
        VerifyHref = verifyHref;
    }


    public String getEmailRecipient() {
        return EmailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        EmailRecipient = emailRecipient;
    }

}
