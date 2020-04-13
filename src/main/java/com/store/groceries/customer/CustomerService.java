package com.store.groceries.customer;

import com.store.groceries.customer.entities.Customer;
import com.store.groceries.customer.entities.Reward;
import com.store.groceries.customer.entities.Transaction;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
class CustomerService {

  List<Reward> getCustomersRewards(List<Customer> customers) {
    return customers.stream().map(this::getRewards).collect(Collectors.toList());
  }

  private Reward getRewards(Customer customer) {
    Reward reward = new Reward(customer.getCustomerId());
    List<Transaction> transactions = customer.getTransactions();
    Map<String, Integer> monthRewards = new HashMap<>();

    if (transactions != null && !transactions.isEmpty()) {
      for (Transaction transaction : transactions) {
        String month = retrieveMonth(transaction.getDate());
        if (StringUtils.isNotBlank(month)) {
          // calculate rewards amount
          int rewardAmount = rewardCalculator(transaction.getAmount());

          // add month rewards. If a month already exists, then add amount to existing amount
          Integer value = monthRewards.get(month);
          Integer monthlyReward = value != null ? (value + rewardAmount) : rewardAmount;
          monthRewards.put(month, monthlyReward);

          // calculate total rewards (sum of all month's rewards)
          reward.addTotalRewards(rewardAmount);
        }
      }

      reward.setMonthRewards(monthRewards);
    }
    return reward;
  }

  // A customer receives 2 points for every dollar spent over $100 in each transaction,
  // plus 1 point for every dollar spent over $50 in each transaction
  //(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
  int rewardCalculator(int amountSpent) {
    int rewardsAmount = 0;

    if (amountSpent > 50) {
      if (amountSpent <= 100) {
        rewardsAmount = rewardsAmount + (amountSpent - 50);
      } else {
        rewardsAmount = (amountSpent - 100) * 2 + 50;
      }
    }

    return rewardsAmount;
  }

  /**
   * Check if a transaction is within 3 months period from today's date. If transaction is above 3
   * months period then it gets ignored
   */
  private String retrieveMonth(LocalDate transactionDate) {
    LocalDate threeMonthsAgo = LocalDate.now().plusMonths(-3); // 3 months back transaction

    // is within 3 months period
    if (transactionDate.isAfter(threeMonthsAgo)) {
      return transactionDate.getMonth().name();
    } else {
      return null;
    }
  }
}
