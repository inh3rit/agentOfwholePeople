package com.exemple.demo.controller;

import com.exemple.demo.domain.Agent;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.domain.Customer;
import com.exemple.demo.sevice.IMiniProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序接口
 */
@RestController
@RequestMapping("mini")
public class MiniProgramController {

    private final static Logger log = LoggerFactory.getLogger(MiniProgramController.class);
    @Autowired
    IMiniProgramService miniProgramService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestBody Agent agent) {
        try {
            int count = miniProgramService.register(agent);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException)
                return "身份证号码已存在";
            else
                return "未知错误";
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Agent agent) {
        try {
            Agent _agent = miniProgramService.isAgentExist(agent);
            if (_agent == null)
                return "用户不存在";
            else if (!agent.getPasswd().equals(_agent.getPasswd()))
                return "密码不正确";

        } catch (Exception e) {
            e.printStackTrace();
            return "未知错误";
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addCustomer(@RequestBody Customer customer) {
        try {
            int count = miniProgramService.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException)
                return "您已添加此客户";
            else
                return "未知错误";
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "addCreditCard", method = RequestMethod.POST)
    public String addCreditCard(@RequestBody CreditCard creditCard) {
        try {
            int count = miniProgramService.addCreditCard(creditCard);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException)
                return "您已添加此银行卡";
            else
                return "未知错误";
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "getCustomers", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> getCustomers(String agentIdNum) {
        List<Customer> customers = miniProgramService.getCustomers(agentIdNum);
        return customers;
    }

    @RequestMapping(value = "getCreditCards", method = RequestMethod.GET)
    public @ResponseBody
    List<CreditCard> getCreditCards(String agentIdNum) {
        List<CreditCard> creditCards = miniProgramService.getCreditCards(agentIdNum);
        return creditCards;
    }

}

