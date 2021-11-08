package com.awards.model;

public class PointsCustomerReport {
    private Customer customer;
    private Integer points;

    public PointsCustomerReport(Customer customer, Integer points) {
        this.customer = customer;
        this.points = points;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
