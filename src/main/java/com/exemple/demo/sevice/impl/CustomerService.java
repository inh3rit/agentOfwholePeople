package com.exemple.demo.sevice.impl;

import com.exemple.demo.Const.SqlParam;
import com.exemple.demo.domain.Customer;
import com.exemple.demo.mapper.CustomerMapper;
import com.exemple.demo.sevice.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int customerCount(String agentIdNum) {
        return customerMapper.customerCount(agentIdNum);
    }

    @Override
    public List<Customer> customerList(String agentIdNum, Integer pageNo) {
        return customerMapper.customerList(agentIdNum, pageNo * SqlParam.PageSize, SqlParam.PageSize);
    }
}
