package com.billing.extract.model;

import java.util.ArrayList;
import java.util.List;

public class BillingInfo {
    private double total;
    private List<BillingItem> items = new ArrayList<>();

    public BillingInfo() {}

    public BillingInfo(double total, List<BillingItem> items) {
        this.total = total;
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BillingItem> getItems() {
        return items;
    }

    public void setItems(List<BillingItem> items) {
        this.items = items;
    }
}
