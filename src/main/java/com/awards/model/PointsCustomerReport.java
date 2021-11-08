package com.awards.model;

public class PointsCustomerReport {
    private Customer customer;
    private Integer totalPoints;

    public PointsCustomerReport(Customer customer, Integer points) {
        this.customer = customer;
        this.totalPoints = points;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getPoints() {
        return totalPoints;
    }

    public void setPoints(Integer points) {
        this.totalPoints = points;
    }
}
