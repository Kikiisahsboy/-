package com.atguigu.daijia.driver.client;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.customer.CustomerInfo;
import com.atguigu.daijia.model.entity.driver.DriverSet;
import com.atguigu.daijia.model.form.driver.DriverFaceModelForm;
import com.atguigu.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.atguigu.daijia.model.vo.driver.DriverAuthInfoVo;
import com.atguigu.daijia.model.vo.driver.DriverInfoVo;
import com.atguigu.daijia.model.vo.driver.DriverLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-driver")
public interface DriverInfoFeignClient {
    @GetMapping("/driver/info/login/{code}")
    Result<Long> login(@PathVariable("code") String code);

    @GetMapping("/driver/info/getDriverLoginInfo/{driverId}")
    public Result<DriverLoginVo> getDriverLoginInfo(@PathVariable Long driverId);

    @GetMapping("/driver/info/getDriverAuthInfo/{driverId}")
    Result<DriverAuthInfoVo> getDriverAuthInfo(@PathVariable("driverId") Long driverId);

    @PostMapping("/driver/info/updateDriverAuthInfo")
    Result<Boolean> UpdateDriverAuthInfo(@RequestBody UpdateDriverAuthInfoForm updateDriverAuthInfoForm);

    @PostMapping("/driver/info/creatDriverFaceModel")
    Result<Boolean> creatDriverFaceModel(@RequestBody DriverFaceModelForm driverFaceModelForm);
    /**
     * 获取司机设置信息
     * @param driverId
     * @return
     */
    @GetMapping("/driver/info/getDriverSet/{driverId}")
    Result<DriverSet> getDriverSet(@PathVariable("driverId") Long driverId);

    @GetMapping("/driver/info/isFaceRecognition/{driverId}")
    Result<Boolean> isFaceRecognition(@PathVariable("driverId") Long driverId);

    @PostMapping("/driver/info/verifyDriverFace")
    Result<Boolean> verifyDriverFace(@RequestBody DriverFaceModelForm driverFaceModelForm);

    @GetMapping("/driver/info/updateServiceStatus/{driverId}/{status}")
    Result<Boolean> updateServiceStatus(@PathVariable("driverId") Long driverId, @PathVariable("status") Integer status);
    /**
     * 获取司机基本信息
     */
    @GetMapping("/driver/info/getDriverInfo/{driverId}")
    Result<DriverInfoVo> getDriverInfo(@PathVariable("driverId") Long driverId);

    @GetMapping("/driver/info/getDriverOpenId/{driverId}")
    Result<String> getDriverOpenId(@PathVariable("driverId") Long driverId);
}