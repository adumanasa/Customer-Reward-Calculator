package com.store.groceries.customer;

import com.store.groceries.customer.entities.Customer;
import com.store.groceries.customer.entities.Reward;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/customer")
@RestController
class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/rewards")
  public List<Reward> getRewards(@RequestBody List<Customer> customers) {
    if (customers != null && !customers.isEmpty()) {
      return customerService.getCustomersRewards(customers);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer info not provided");
    }
  }
}
