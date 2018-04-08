package com.exemple.demo.sevice;

import com.exemple.demo.domain.Customer;

import java.util.List;

public interface ICustomerService {

    int customerCount(String agentIdNum);

    List<Customer> customerList(String agentIdNum);
}
