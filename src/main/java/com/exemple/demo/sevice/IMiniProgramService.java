package com.exemple.demo.sevice;

import com.exemple.demo.domain.Agent;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.domain.Customer;

public interface IMiniProgramService {

    int register(Agent agent) throws Exception;

    Agent isAgentExist(Agent agent) throws Exception;

    int addCustomer(Customer customer);

    int addCreditCard(CreditCard creditCard);
}
