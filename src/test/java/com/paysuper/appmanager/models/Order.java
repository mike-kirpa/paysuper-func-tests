package com.paysuper.appmanager.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order {
    private String today;
    private String pattern = "MMM dd, yyyy";
    private String UUID;
    private String OrderCurrency;
    private String OrderAmount;
    private String ProjectId;
    private String TotalAmountFromPayForm;


    public Order(){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("en", "US"));
        today = simpleDateFormat.format(new Date());
    }

    public String getToday() {
        return today;
    }

    public String getTotalAmountFromPayForm() {
        return TotalAmountFromPayForm;
    }

    public void setTotalAmountFromPayForm(String totalAmountFromPayForm) {
        TotalAmountFromPayForm = totalAmountFromPayForm;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        OrderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return OrderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        OrderCurrency = orderCurrency;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
