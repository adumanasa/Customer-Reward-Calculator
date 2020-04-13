package com.store.groceries.customer.entities;

import java.util.List;

public class Customer {

  private String customerId;

  private List<Transaction> transactions;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

}
