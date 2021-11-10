package com.awards.model;

public class PointsCustomerReport {
    private Customer customer;
    private Integer lastMonthPoints;
    private Integer oneMonthsAgoPoints;
    private Integer twoMonthsAgoPoints;
    private Integer totalPoints;

    public PointsCustomerReport(Customer customer, Integer points) {
        this.customer = customer;
        this.totalPoints = points;
    }

    public PointsCustomerReport(Customer customer, Integer lastMonthPoints,
                                Integer oneMonthsAgoPoints, Integer twoMonthsAgoPoints, Integer totalPoints) {
        this.customer = customer;
        this.lastMonthPoints = lastMonthPoints;
        this.oneMonthsAgoPoints = oneMonthsAgoPoints;
        this.twoMonthsAgoPoints = twoMonthsAgoPoints;
        this.totalPoints = totalPoints;
    }

    public Integer getLastMonthPoints() {
        return lastMonthPoints;
    }

    public void setLastMonthPoints(Integer lastMonthPoints) {
        this.lastMonthPoints = lastMonthPoints;
    }

    public Integer getOneMonthsAgoPoints() {
        return oneMonthsAgoPoints;
    }

    public void setOneMonthsAgoPoints(Integer oneMonthsAgoPoints) {
        this.oneMonthsAgoPoints = oneMonthsAgoPoints;
    }

    public Integer getTwoMonthsAgoPoints() {
        return twoMonthsAgoPoints;
    }

    public void setTwoMonthsAgoPoints(Integer twoMonthsAgoPoints) {
        this.twoMonthsAgoPoints = twoMonthsAgoPoints;
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
