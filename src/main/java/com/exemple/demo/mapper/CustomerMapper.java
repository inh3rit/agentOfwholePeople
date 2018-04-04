package com.exemple.demo.mapper;

import com.exemple.demo.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper {

    @Insert("insert into customer(name, sex, description, agent_id_num, telephone) values(#{name}, #{sex}, " +
            "#{description}, #{agent_id_num}, #{telephone})")
    int insertCustomer(Customer customer);

    @Select("select * from customer where agent_id_num = #{agentIdNum}")
    List<Customer> getCustomers(String agentIdNum);

    @Select("select count(id) from customer where agent_id_num=#{agentIdNum}")
    Integer customerCount(@Param("agentIdNum")String agentIdNum);

    @Select("select * from customer limit #{offset}, #{pageSize}")
    List<Customer> customerList(@Param("agentIdNum")String agentIdNum, @Param("offset") Integer offset, @Param
            ("pageNo")
            Integer
            pageSize);
}
