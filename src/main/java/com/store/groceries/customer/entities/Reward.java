package com.store.groceries.customer.entities;

import java.util.Map;

public class Reward {

  private String customerId;
  private int totalRewards;
  private Map<String, Integer> monthRewards;

  public Reward(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public int getTotalRewards() {
    return totalRewards;
  }

  public void setTotalRewards(int totalRewards) {
    this.totalRewards = totalRewards;
  }

  public Map<String, Integer> getMonthRewards() {
    return monthRewards;
  }

  public void setMonthRewards(Map<String, Integer> monthRewards) {
    this.monthRewards = monthRewards;
  }

  public void addTotalRewards(int reward) {
    this.totalRewards = this.totalRewards + reward;
  }
}
