package com.awards.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JoinColumn(name = "cost")
    private Float cost;
    @JoinColumn
    private Date date;

    public Transaction() {
    }

    public Transaction(Long id, Customer customer, Float cost, Date date) {
        this.id = id;
        this.customer = customer;
        this.cost = cost;
        this.date = date;
    }

    public Transaction(Customer customer, Float cost, Date date) {
        this.customer = customer;
        this.cost = cost;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
