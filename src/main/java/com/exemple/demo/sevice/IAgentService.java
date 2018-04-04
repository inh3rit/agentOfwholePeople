package com.exemple.demo.sevice;

import com.exemple.demo.domain.Agent;

import java.util.List;

public interface IAgentService {

    int agentCount();

    List<Agent> agentList(Integer pageNo);
}
