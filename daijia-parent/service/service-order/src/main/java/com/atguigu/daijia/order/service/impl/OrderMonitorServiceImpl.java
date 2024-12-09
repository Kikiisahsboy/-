package com.atguigu.daijia.order.service.impl;

import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.model.entity.order.OrderMonitor;
import com.atguigu.daijia.model.entity.order.OrderMonitorRecord;
import com.atguigu.daijia.order.mapper.OrderMonitorMapper;
import com.atguigu.daijia.order.repository.OrderMonitorRecordRepository;
import com.atguigu.daijia.order.service.OrderMonitorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderMonitorServiceImpl extends ServiceImpl<OrderMonitorMapper, OrderMonitor> implements OrderMonitorService {

    @Autowired
    private OrderMonitorRecordRepository orderMonitorRecordRepository;
    @Autowired
    private OrderMonitorMapper orderMonitorMapper;
    @Override
    public Boolean saveOrderMonitorRecord(OrderMonitorRecord orderMonitorRecord) {
        orderMonitorRecordRepository.save(orderMonitorRecord);//MongoDB中crud的方法为MongoRepository和MongoTemplate
        return true;
    }

    @Override
    public OrderMonitor getOrderMonitor(Long orderId) {
        LambdaQueryWrapper<OrderMonitor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderMonitor::getOrderId, orderId);
        OrderMonitor orderMonitor = orderMonitorMapper.selectOne(wrapper);
        if(orderMonitor == null){
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        return orderMonitor;
    }
}
