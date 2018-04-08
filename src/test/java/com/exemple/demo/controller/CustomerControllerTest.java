package com.exemple.demo.controller;

import com.exemple.demo.BaseTest;
import com.exemple.demo.sevice.ICustomerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerControllerTest extends BaseTest {

    @Autowired
    private ICustomerService customerService;

    @Test
    public void userList() {
        customerService.customerList("111111111111111");
    }
}