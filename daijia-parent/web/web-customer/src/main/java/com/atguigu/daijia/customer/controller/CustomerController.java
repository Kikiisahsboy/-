package com.atguigu.daijia.customer.controller;

import com.atguigu.daijia.common.login.GuiguLogin;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.customer.service.CustomerService;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "客户API接口管理")
@RestController
@RequestMapping("/customer")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerController {
    @Autowired
    private CustomerService customerInfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Operation(summary = "小程序授权登录")
    @GetMapping("/login/{code}")
    public Result<String> wxLogin(@PathVariable String code) {
        return Result.ok(customerInfoService.login(code));
    }
    @GuiguLogin
    @Operation(summary = "获取客户登录信息")
    @GetMapping("/getCustomerLoginInfo")
    public Result<CustomerLoginVo> getCustomerLoginInfo(@RequestHeader(value = "token") String token) {
        return Result.ok(customerInfoService. getCustomerLoginInfo(token));

    }



}

