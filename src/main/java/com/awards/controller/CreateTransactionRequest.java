package com.awards.controller;

import java.util.Date;

public class CreateTransactionRequest {
    private Date date;
    private Long customerId;
    private Float costValue;

    public CreateTransactionRequest(Date date, Long customerId, Float costValue) {
        this.date = date;
        this.customerId = customerId;
        this.costValue = costValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Float getCostValue() {
        return costValue;
    }

    public void setCostValue(Float costValue) {
        this.costValue = costValue;
    }
}
