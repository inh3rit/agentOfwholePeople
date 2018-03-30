package com.exemple.demo.controller;

import com.exemple.demo.domain.MIniUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序接口
 */
@RestController
@RequestMapping("mini")
public class MiniProgramController {

    @RequestMapping(value = "input", method = RequestMethod.POST)
    public String register(MIniUser mIniUser) {

        return null;
    }
}

