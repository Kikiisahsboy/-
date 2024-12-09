package com.atguigu.daijia.driver.service.impl;

import com.atguigu.daijia.common.constant.RedisConstant;
import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.dispatch.client.NewOrderFeignClient;
import com.atguigu.daijia.driver.client.DriverInfoFeignClient;
import com.atguigu.daijia.driver.service.DriverService;
import com.atguigu.daijia.map.client.LocationFeignClient;
import com.atguigu.daijia.model.form.driver.DriverFaceModelForm;
import com.atguigu.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.atguigu.daijia.model.vo.driver.DriverAuthInfoVo;
import com.atguigu.daijia.model.vo.driver.DriverLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverInfoFeignClient client;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LocationFeignClient locationClient;
    @Autowired
    private NewOrderFeignClient newOrderClient;
    @Override
    public String login(String code) {
        Result<Long> longResult = client.login(code);
        Long DriverId = longResult.getData();
        if(longResult.getCode()!=200){
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        String token=UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX + token,
                DriverId.toString(),
                RedisConstant.USER_LOGIN_KEY_TIMEOUT,
                TimeUnit.SECONDS);
        return token;
    }

    @Override
    public DriverAuthInfoVo getDriverAuthInfo(Long driverId) {
        Result<DriverAuthInfoVo> driverAuthInfo = client.getDriverAuthInfo(driverId);
        return driverAuthInfo.getData();
    }

    @Override
    public Boolean updateDriverAuthInfo(UpdateDriverAuthInfoForm updateDriverAuthInfoForm) {
        return client.UpdateDriverAuthInfo(updateDriverAuthInfoForm).getData();
    }

    @Override
    public Boolean creatDriverFaceModel(DriverFaceModelForm driverFaceModelForm) {
        return client.creatDriverFaceModel(driverFaceModelForm).getData();
    }

    @Override
    public Boolean isFaceRecognition(Long driverId) {
        return client.isFaceRecognition(driverId).getData();
    }

    @Override
    public Boolean verifyDriverFace(DriverFaceModelForm driverFaceModelForm) {
        return client.verifyDriverFace(driverFaceModelForm).getData();
    }

    @Override
    public Boolean startService(Long driverId) {
        DriverLoginVo driverLoginVo = client.getDriverLoginInfo(driverId).getData();
        Integer authStatus = driverLoginVo.getAuthStatus();
        if(authStatus!=2){//1、判断是否完成认证（身份证、驾驶证）
            throw new GuiguException(ResultCodeEnum.AUTH_ERROR);
        }
        Boolean isFace = client.isFaceRecognition(driverId).getData();
        if(!isFace){//2、判断是否完成人脸识别
            throw new GuiguException(ResultCodeEnum.FACE_ERROR);
        }
        client.updateServiceStatus(driverId,1);
        //4 删除redis司机位置信息
        locationClient.removeDriverLocation(driverId);

        //5 清空司机临时队列数据
        newOrderClient.clearNewOrderQueueData(driverId);
        return true;
    }

    @Override
    public Boolean stopService(Long driverId) {
        //将司机接单状态设置为未接单
        client.updateServiceStatus(driverId,0);
        //删除司机位置信息
        locationClient.removeDriverLocation(driverId);
        //清空司机临时队列
        newOrderClient.clearNewOrderQueueData(driverId);
        return true;
    }

}
