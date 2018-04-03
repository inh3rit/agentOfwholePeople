package com.exemple.demo.sevice;

import com.exemple.demo.domain.Agent;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.domain.Customer;
import com.exemple.demo.mapper.MiniProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiniProgramService implements IMiniProgramService {

    @Autowired
    private MiniProgramMapper miniProgramMapper;

    @Override
    public int register(Agent agent) throws Exception {
        return miniProgramMapper.insertAgent(agent);
    }

    @Override
    public Agent isAgentExist(Agent agent) throws Exception {
        return miniProgramMapper.isAgentExist(agent);
    }

    @Override
    public int addCustomer(Customer customer) {
        return miniProgramMapper.insertCustomer(customer);
    }

    @Override
    public int addCreditCard(CreditCard creditCard) {
        return miniProgramMapper.insertCreditCard(creditCard);
    }

    @Override
    public List<Customer> getCustomers(String agentIdNum) {
        return miniProgramMapper.getCustomers(agentIdNum);
    }

    @Override
    public List<CreditCard> getCreditCards(String agentIdNum) {
        return miniProgramMapper.getCreditCards(agentIdNum);
    }
}
