package com.awards.controller;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateTransactionRequest {
    private Date date;
    @NotNull(message = "User is cannot be null")
    private Long customerId;
    @NotNull(message = "Transaction cost value cannot be null")
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
