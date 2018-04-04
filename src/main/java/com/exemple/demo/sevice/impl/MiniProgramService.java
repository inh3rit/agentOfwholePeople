package com.exemple.demo.sevice.impl;

import com.exemple.demo.domain.Agent;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.domain.Customer;
import com.exemple.demo.mapper.AgentMapper;
import com.exemple.demo.mapper.CreditCardMapper;
import com.exemple.demo.mapper.CustomerMapper;
import com.exemple.demo.sevice.IMiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MiniProgramService implements IMiniProgramService {

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Override
    public int register(Agent agent) throws Exception {
        return agentMapper.insertAgent(agent);
    }

    @Override
    public Agent isAgentExist(Agent agent) throws Exception {
        return agentMapper.isAgentExist(agent);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    @Transactional
    public int addCreditCard(CreditCard creditCard) {
        if (creditCard.getIs_default() == 1)
            creditCardMapper.updateIsDefault();
        return creditCardMapper.insertCreditCard(creditCard);
    }

    @Override
    public List<Customer> getCustomers(String agentIdNum) {
        return customerMapper.getCustomers(agentIdNum);
    }

    @Override
    public List<CreditCard> getCreditCards(String agentIdNum) {
        return creditCardMapper.getCreditCards(agentIdNum);
    }
}
