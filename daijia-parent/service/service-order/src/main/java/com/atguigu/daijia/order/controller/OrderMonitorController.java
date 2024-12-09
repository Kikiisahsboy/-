package com.atguigu.daijia.order.controller;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.order.OrderMonitor;
import com.atguigu.daijia.model.entity.order.OrderMonitorRecord;
import com.atguigu.daijia.order.service.OrderMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/monitor")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderMonitorController {
    @Autowired
    private OrderMonitorService orderMonitorService;
    //todo 此处视频中还有一个saveOrderMonitor的接口，是向mysql中写入订单监控数据的，目前还未实现。
    @Operation(summary = "保存订单监控记录数据")
    @PostMapping("/saveOrderMonitorRecord")
    public Result<Boolean> saveMonitorRecord(@RequestBody OrderMonitorRecord orderMonitorRecord) {
        return Result.ok(orderMonitorService.saveOrderMonitorRecord(orderMonitorRecord));
    }

    @Operation(summary="根订单id获取订单监控信息")
    @GetMapping("/getorderMonitor/{orderId}")
    public Result<OrderMonitor> getOrderMonitor(@PathVariable Long orderId){
        return Result.ok(orderMonitorService. getOrderMonitor(orderId));
    }
    @Operation(summary="更新订单监控信息")
    @PostMapping("/updateorderMonitor")
    public Result<Boolean> updateOrderMonitor(@RequestBody OrderMonitor orderMonitor){
        return Result.ok(orderMonitorService.updateById(orderMonitor));
    }
}
