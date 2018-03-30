package com.exemple.demo.domain;

import java.io.Serializable;
import java.util.List;

public class MIniUser implements Serializable {

    private int id;
    private String name;
    private String passwd;
    private String telephone;
    private String idNum;
    private List<CreditCard> cardList;

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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
}