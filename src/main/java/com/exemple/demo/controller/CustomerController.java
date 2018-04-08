package com.exemple.demo.controller;

import com.exemple.demo.Const.SqlParam;
import com.exemple.demo.domain.Result;
import com.exemple.demo.enums.ResultEnum;
import com.exemple.demo.sevice.ICustomerService;
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
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 分页获取顾客列表
     *
     * @param agentIdNum String, 经纪人身份证号码
     * @return Result
     */
    @GetMapping("/list")
    public Result userList(@RequestParam("agentIdNum") String agentIdNum) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("customerCount", customerService.customerCount(agentIdNum));
        resultMap.put("pageSize", SqlParam.PageSize);
        resultMap.put("customerList", customerService.customerList(agentIdNum));
        return new Result<>(ResultEnum.SUCCESS, resultMap);
    }
}
