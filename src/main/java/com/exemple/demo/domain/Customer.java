package com.exemple.demo.domain;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id;
    private String name;
    private String telephone;
    private int sex; // 1: male, 0:female
    private String description;
    private String agent_id_num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgent_id_num() {
        return agent_id_num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAgent_id_num(String agent_id_num) {
        this.agent_id_num = agent_id_num;
    }
}
