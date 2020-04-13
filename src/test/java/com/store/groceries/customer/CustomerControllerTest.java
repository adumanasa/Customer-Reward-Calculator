package com.store.groceries.customer;

import com.store.groceries.customer.entities.Customer;
import com.store.groceries.customer.entities.Reward;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

  private static final String customerId = "123";

  @InjectMocks
  private CustomerController customerController;

  @Mock
  private CustomerService customerService;

  @Test
  public void testGetRewards(){
    Reward reward = new Reward(customerId);

    Customer customer = new Customer();
    customer.setCustomerId(customerId);

    Mockito.when(customerService.getCustomersRewards(ArgumentMatchers.anyList()))
        .thenReturn(new ArrayList<Reward>() {{
          add(reward);
        }});

    List<Reward> result = customerController.getRewards(new ArrayList<Customer>() {{
      add(customer);
    }});

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
    Assert.assertEquals(customerId, result.get(0).getCustomerId());
  }

  @Test
  public void testGetRewardsBlankCustomerId() {
    Reward reward = new Reward(customerId);

    Mockito.when(customerService.getCustomersRewards(ArgumentMatchers.anyList()))
        .thenReturn(new ArrayList<Reward>() {{
          add(reward);
        }});

    try {
      customerController.getRewards(new ArrayList<Customer>() {{
        add(new Customer());
      }});
    } catch (Exception e) {
      Assert.assertEquals("Customer info not provided", e.getMessage());
    }
  }
}
