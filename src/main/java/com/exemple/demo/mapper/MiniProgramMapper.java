package com.exemple.demo.mapper;

import com.exemple.demo.domain.Agent;
import com.exemple.demo.domain.CreditCard;
import com.exemple.demo.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MiniProgramMapper {

    @Insert("insert into agent(name, passwd, telephone, id_num, sex) values(#{name}, #{passwd}, " +
            "#{telephone}, #{id_num}, #{sex})")
    int insertAgent(Agent agent) throws Exception;

    @Select("select * from agent where id_num=#{id_num}")
    Agent isAgentExist(Agent agent) throws Exception;

    @Insert("insert into customer(name, sex, description, agent_id_num, telephone) values(#{name}, #{sex}, " +
            "#{description}, #{agent_id_num}, #{telephone})")
    int insertCustomer(Customer customer);

    @Insert("insert into credit_card(card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) " +
            "values(#{card_num}, #{bank_name}, #{sub_bank_name}, #{city}, #{is_default}, #{agent_id_num})")
    int insertCreditCard(CreditCard credit_ard);

    @Select("select * from customer where agent_id_num = #{agentIdNum}")
    List<Customer> getCustomers(String agentIdNum);

    @Select("select * from credit_card where agent_id_num = #{agentIdNum}")
    List<CreditCard> getCreditCards(String agentIdNum);
}
