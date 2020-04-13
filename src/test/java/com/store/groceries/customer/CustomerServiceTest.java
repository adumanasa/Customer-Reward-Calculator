package com.store.groceries.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.store.groceries.customer.entities.Customer;
import com.store.groceries.customer.entities.Reward;
import com.store.groceries.customer.entities.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

  @InjectMocks
  private CustomerService customerService;

  @Test
  public void testGetCustomerRewards(){
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(createTransaction(125, 1));
    transactions.add(createTransaction(60, 0));
    transactions.add(createTransaction(100, 2));
    transactions.add(createTransaction(90, 1));

    Customer customer = new Customer();
    customer.setCustomerId("1234");
    customer.setTransactions(transactions);

    List<Transaction> transactions1 = new ArrayList<>();
    transactions1.add(createTransaction(110, 1));
    transactions1.add(createTransaction(60, 6)); // this should be excluded
    transactions1.add(createTransaction(30, 2));
    transactions1.add(createTransaction(90, 1));

    Customer customer1 = new Customer();
    customer1.setCustomerId("3762");
    customer1.setTransactions(transactions1);

    List<Reward> rewards = customerService.getCustomersRewards(new ArrayList<Customer>() {{
      add(customer);
      add(customer1);
    }});

    assertNotNull(rewards);
    assertEquals(2, rewards.size());
    assertEquals("1234", rewards.get(0).getCustomerId());
    assertEquals(3, rewards.get(0).getMonthRewards().size());
    assertEquals(200, rewards.get(0).getTotalRewards());

    assertEquals("3762", rewards.get(1).getCustomerId());
    assertEquals(2, rewards.get(1).getMonthRewards().size());
    assertEquals(110, rewards.get(1).getTotalRewards());
  }

  @Test
  public void testGetCustomerRewardsNoTransactions() {
    Customer customer = new Customer();
    customer.setCustomerId("123");

    List<Reward> rewards = customerService.getCustomersRewards(new ArrayList<Customer>() {{
      add(customer);
    }});

    assertNotNull(rewards);
    assertEquals(1, rewards.size());
    assertEquals("123", rewards.get(0).getCustomerId());
    assertEquals(0, rewards.get(0).getTotalRewards());
    assertNull(rewards.get(0).getMonthRewards());
  }

  @Test
  public void testRewardCalculator() {
    int rewards = customerService.rewardCalculator(140);
    assertEquals(130, rewards);

    rewards = customerService.rewardCalculator(30);
    assertEquals(0, rewards);

    rewards = customerService.rewardCalculator(90);
    assertEquals(40, rewards);

    rewards = customerService.rewardCalculator(230);
    assertEquals(310, rewards);
  }

  private LocalDate getDays(int months) {
    LocalDate localDate = LocalDate.now();
    return localDate.minusMonths(months);
  }

  private Transaction createTransaction(int amount, int month) {
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDate(getDays(month));

    return transaction;
  }
}
