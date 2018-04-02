package com.exemple.demo.domain;

import java.io.Serializable;

public class CreditCard implements Serializable {

    private int id;
    private String card_num;
    private String bank_name;
    private String sub_bank_name;
    private String city;
    private int is_default; // 0: false, 1: true
    private String agent_id_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getSub_bank_name() {
        return sub_bank_name;
    }

    public void setSub_bank_name(String sub_bank_name) {
        this.sub_bank_name = sub_bank_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getAgent_id_num() {
        return agent_id_num;
    }

    public void setAgent_id_num(String agent_id_num) {
        this.agent_id_num = agent_id_num;
    }
}
