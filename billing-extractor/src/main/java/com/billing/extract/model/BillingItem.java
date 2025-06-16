package com.billing.extract.model;

public class BillingItem {
    private String description;
    private double value;

    public BillingItem() {}

    public BillingItem(String description, double value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
