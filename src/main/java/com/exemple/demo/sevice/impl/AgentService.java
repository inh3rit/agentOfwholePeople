package com.exemple.demo.sevice.impl;

import com.exemple.demo.Const.SqlParam;
import com.exemple.demo.domain.Agent;
import com.exemple.demo.mapper.AgentMapper;
import com.exemple.demo.sevice.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService implements IAgentService {

    @Autowired
    private AgentMapper agentMapper;

    @Override
    public int agentCount() {
        return agentMapper.agentCount();
    }

    @Override
    public List<Agent> agentList(Integer pageNo) {
        return agentMapper.agentList(pageNo * SqlParam.PageSize, SqlParam.PageSize);
    }
}
