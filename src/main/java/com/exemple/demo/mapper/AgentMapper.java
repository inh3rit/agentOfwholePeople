package com.exemple.demo.mapper;

import com.exemple.demo.domain.Agent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AgentMapper {

    @Insert("insert into agent(name, passwd, telephone, id_num, sex) values(#{name}, #{passwd}, " +
            "#{telephone}, #{id_num}, #{sex})")
    int insertAgent(Agent agent) throws Exception;

    @Select("select * from agent where id_num=#{id_num}")
    Agent isAgentExist(Agent agent) throws Exception;

    @Select("select count(id) from agent")
    int agentCount();

    @Select("select * from agent limit #{offset}, #{pageSize}")
    List<Agent> agentList(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
