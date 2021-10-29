package com.paysuper.appmanager.models;

import java.util.Objects;

public class Webhook {

    private String type;
    private String event;
    private String live;
    private String object_id;
    private String object_object;
    private String object_status;
    private Boolean object_canceled;
    private String object_cancellation;
    private Boolean object_refunded;
    private String object_email;
    private String object_amount;
    private String object_currency;
    private String object_user_email;
    private String object_user_address_country;
    private String object_tax_type;
    private String object_tax_rate;
    private String object_tax_amount;
    private String object_tax_currency;
    final private String object_method_title = "Bank card";
    final private String object_method_external_id = "BANKCARD";
    final private String object_method_payment_system_id = "5be2d0b4b0b30d0007383ce5";
    final private String object_method_payment_type = "BANKCARD";
    final private Boolean object_method_payment_saved = false;

    public Webhook(String type){
        if(type.equals("order.processed")){
            event = "payment.success";
            object_object = "order";
            object_status = "processed";
            object_canceled = false;
            object_cancellation = null;
            object_refunded = false;
        }
        else if(type.equals("payment.cancel")){
            event = "payment.cancel";
            object_object = "order";
            object_status = "rejected";
            object_canceled = false;
            object_cancellation = "[code:06, reason:3-D Secure availability is unknown]";
            object_refunded = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Webhook webhook = (Webhook) o;
        return Objects.equals(type, webhook.type) && Objects.equals(event, webhook.event) && Objects.equals(live, webhook.live) && Objects.equals(object_id, webhook.object_id) && Objects.equals(object_object, webhook.object_object) && Objects.equals(object_status, webhook.object_status) && Objects.equals(object_canceled, webhook.object_canceled) && Objects.equals(object_cancellation, webhook.object_cancellation) && Objects.equals(object_refunded, webhook.object_refunded) && Objects.equals(object_email, webhook.object_email) && Objects.equals(object_amount, webhook.object_amount) && Objects.equals(object_currency, webhook.object_currency) && Objects.equals(object_user_email, webhook.object_user_email) && Objects.equals(object_user_address_country, webhook.object_user_address_country) && Objects.equals(object_tax_type, webhook.object_tax_type) && Objects.equals(object_tax_rate, webhook.object_tax_rate) && Objects.equals(object_tax_amount, webhook.object_tax_amount) && Objects.equals(object_tax_currency, webhook.object_tax_currency) && Objects.equals(object_method_title, webhook.object_method_title) && Objects.equals(object_method_external_id, webhook.object_method_external_id) && Objects.equals(object_method_payment_system_id, webhook.object_method_payment_system_id) && Objects.equals(object_method_payment_type, webhook.object_method_payment_type) && Objects.equals(object_method_payment_saved, webhook.object_method_payment_saved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, event, live, object_id, object_object, object_status, object_canceled, object_cancellation, object_refunded, object_email, object_amount, object_currency, object_user_email, object_user_address_country, object_tax_type, object_tax_rate, object_tax_amount, object_tax_currency, object_method_title, object_method_external_id, object_method_payment_system_id, object_method_payment_type, object_method_payment_saved);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getObject_id() {
        return object_id;
    }

    @Override
    public String toString() {
        return "Webhook{" +
                "type='" + type + '\'' +
                ", event='" + event + '\'' +
                ", live='" + live + '\'' +
                ", object_id='" + object_id + '\'' +
                ", object_object='" + object_object + '\'' +
                ", object_status='" + object_status + '\'' +
                ", object_canceled=" + object_canceled +
                ", object_cancellation='" + object_cancellation + '\'' +
                ", object_refunded=" + object_refunded +
                ", object_email='" + object_email + '\'' +
                ", object_amount='" + object_amount + '\'' +
                ", object_currency='" + object_currency + '\'' +
                ", object_user_email='" + object_user_email + '\'' +
                ", object_user_address_country='" + object_user_address_country + '\'' +
                ", object_tax_type='" + object_tax_type + '\'' +
                ", object_tax_rate='" + object_tax_rate + '\'' +
                ", object_tax_amount='" + object_tax_amount + '\'' +
                ", object_tax_currency='" + object_tax_currency + '\'' +
                ", object_method_title='" + object_method_title + '\'' +
                ", object_method_external_id='" + object_method_external_id + '\'' +
                ", object_method_payment_system_id='" + object_method_payment_system_id + '\'' +
                ", object_method_payment_type='" + object_method_payment_type + '\'' +
                ", object_method_payment_saved=" + object_method_payment_saved +
                '}';
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getObject_object() {
        return object_object;
    }

    public void setObject_object(String object_object) {
        this.object_object = object_object;
    }

    public String getObject_status() {
        return object_status;
    }

    public void setObject_status(String object_status) {
        this.object_status = object_status;
    }

    public Boolean getObject_canceled() {
        return object_canceled;
    }

    public void setObject_canceled(Boolean object_canceled) {
        this.object_canceled = object_canceled;
    }

    public String getObject_cancellation() {
        return object_cancellation;
    }

    public void setObject_cancellation(String object_cancellation) {
        this.object_cancellation = object_cancellation;
    }

    public Boolean getObject_refunded() {
        return object_refunded;
    }

    public void setObject_refunded(Boolean object_refunded) {
        this.object_refunded = object_refunded;
    }

    public String getObject_email() {
        return object_email;
    }

    public void setObject_email(String object_email) {
        this.object_email = object_email;
    }

    public String getObject_amount() {
        return object_amount;
    }

    public void setObject_amount(String object_amount) {
        this.object_amount = object_amount;
    }

    public String getObject_currency() {
        return object_currency;
    }

    public void setObject_currency(String object_currency) {
        this.object_currency = object_currency;
    }

    public String getObject_user_email() {
        return object_user_email;
    }

    public void setObject_user_email(String object_user_email) {
        this.object_user_email = object_user_email;
    }

    public String getObject_user_address_country() {
        return object_user_address_country;
    }

    public void setObject_user_address_country(String object_user_address_country) {
        this.object_user_address_country = object_user_address_country;
    }

    public String getObject_tax_type() {
        return object_tax_type;
    }

    public void setObject_tax_type(String object_tax_type) {
        this.object_tax_type = object_tax_type;
    }

    public String getObject_tax_rate() {
        return object_tax_rate;
    }

    public void setObject_tax_rate(String object_tax_rate) {
        this.object_tax_rate = object_tax_rate;
    }

    public String getObject_tax_amount() {
        return object_tax_amount;
    }

    public void setObject_tax_amount(String object_tax_amount) {
        this.object_tax_amount = object_tax_amount;
    }

    public String getObject_tax_currency() {
        return object_tax_currency;
    }

    public void setObject_tax_currency(String object_tax_currency) {
        this.object_tax_currency = object_tax_currency;
    }

    public String getObject_method_title() {
        return object_method_title;
    }

    public String getObject_method_external_id() {
        return object_method_external_id;
    }

    public String getObject_method_payment_system_id() {
        return object_method_payment_system_id;
    }

    public String getObject_method_payment_type() {
        return object_method_payment_type;
    }

    public Boolean getObject_method_payment_saved() {
        return object_method_payment_saved;
    }
}
