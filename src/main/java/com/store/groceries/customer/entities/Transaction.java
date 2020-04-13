package com.store.groceries.customer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Transaction {

  @JsonFormat(pattern = "MM/dd/yyyy")
  private LocalDate date;
  private int amount;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
