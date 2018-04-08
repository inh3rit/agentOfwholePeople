package com.exemple.demo.sevice;

import com.exemple.demo.domain.CreditCard;

import java.util.List;

public interface ICreditCardService {

    int creditCardCount(String agentIdNum);

    List<CreditCard> creditCardList(String agentIdNum);
}
