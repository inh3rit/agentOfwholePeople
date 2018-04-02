package com.exemple.demo.domain;

import java.io.Serializable;
import java.util.List;

public class Agent implements Serializable {

    private int id;
    private String name;
    private String passwd;
    private String telephone;
    private String id_num;
    private int sex; // 1:male, 0: female
    private List<CreditCard> cardList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public List<CreditCard> getCardList() {
        return cardList;
    }

    public void setCardList(List<CreditCard> cardList) {
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }
}