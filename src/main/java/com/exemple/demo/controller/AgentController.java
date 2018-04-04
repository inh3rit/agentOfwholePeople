package com.exemple.demo.controller;

import com.exemple.demo.Const.SqlParam;
import com.exemple.demo.domain.Result;
import com.exemple.demo.enums.ResultEnum;
import com.exemple.demo.sevice.IAgentService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private IAgentService agentService;

    /**
     * 分页获取经纪人列表
     *
     * @param pageNo Integer, 分页数字，从0开始
     * @return Result
     */
    @GetMapping("/list")
    public Result userList(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("agentCount", agentService.agentCount());
        resultMap.put("pageNo", pageNo);
        resultMap.put("pageSize", SqlParam.PageSize);
        resultMap.put("agentList", agentService.agentList(pageNo));
        return new Result<>(ResultEnum.SUCCESS, resultMap);
    }

}
