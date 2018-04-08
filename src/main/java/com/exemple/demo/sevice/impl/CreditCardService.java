package com.exemple.demo.sevice.impl;

import com.exemple.demo.Const.SqlParam;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.mapper.CreditCardMapper;
import com.exemple.demo.sevice.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Override
    public int creditCardCount(String agentIdNum) {
        return creditCardMapper.creditCardCount(agentIdNum);
    }

    @Override
    public List<CreditCard> creditCardList(String agentIdNum) {
        return creditCardMapper.creditCardList(agentIdNum);
    }
}
