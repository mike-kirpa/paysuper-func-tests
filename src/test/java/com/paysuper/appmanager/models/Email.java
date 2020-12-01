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
    private String TransactionDate;
    private String Total;
    private String TransactionID;
    private String MerchantName;
    private String WebCheckUrl;
    private String Subject;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String toString() {
        return "Email{" +
                "VerifyHref='" + VerifyHref + '\'' +
                ", EmailRecipient='" + EmailRecipient + '\'' +
                ", TransactionDate='" + TransactionDate + '\'' +
                ", Total='" + Total + '\'' +
                ", TransactionID='" + TransactionID + '\'' +
                ", MerchantName='" + MerchantName + '\'' +
                ", WebCheckUrl='" + WebCheckUrl + '\'' +
                ", PaymentPartner='" + PaymentPartner + '\'' +
                '}';
    }

    public String getWebCheckUrl() {
        return WebCheckUrl;
    }

    public void setWebCheckUrl(String webCheckUrl) {
        WebCheckUrl = webCheckUrl;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getPaymentPartner() {
        return PaymentPartner;
    }

    public void setPaymentPartner(String paymentPartner) {
        PaymentPartner = paymentPartner;
    }

    private String PaymentPartner;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

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
