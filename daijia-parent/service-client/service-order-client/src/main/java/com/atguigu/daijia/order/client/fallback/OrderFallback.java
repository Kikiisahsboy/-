package com.atguigu.daijia.order.client.fallback;

import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.model.entity.order.OrderInfo;
import com.atguigu.daijia.model.form.order.OrderInfoForm;
import com.atguigu.daijia.model.form.order.StartDriveForm;
import com.atguigu.daijia.model.form.order.UpdateOrderBillForm;
import com.atguigu.daijia.model.form.order.UpdateOrderCartForm;
import com.atguigu.daijia.model.vo.base.PageVo;
import com.atguigu.daijia.model.vo.order.*;
import com.atguigu.daijia.order.client.OrderInfoFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.math.BigDecimal;

/*@Project_name:daijia-parent
@Create 2024/12/9 下午4:49
Description:
*/
@Slf4j
public class OrderFallback implements FallbackFactory<OrderInfoFeignClient> {

    @Override
    public OrderInfoFeignClient create(Throwable cause) {
        return new OrderInfoFeignClient() {

            @Override
            public Result<Long> saveOrderInfo(OrderInfoForm orderInfoForm) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Integer> getOrderStatus(Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> robNewOrder(Long driverId, Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<CurrentOrderInfoVo> searchCustomerCurrentOrder(Long customerId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<CurrentOrderInfoVo> searchDriverCurrentOrder(Long driverId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<OrderInfo> getOrderInfo(Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> driverArriveStartLocation(Long orderId, Long driverId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> updateOrderCart(UpdateOrderCartForm updateOrderCartForm) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> startDrive(StartDriveForm startDriveForm) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Long> getOrderNumByTime(String startTime, String endTime) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> endDrive(UpdateOrderBillForm updateOrderBillForm) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<PageVo> findCustomerOrderPage(Long customerId, Long page, Long limit) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<PageVo> findDriverOrderPage(Long driverId, Long page, Long limit) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<OrderBillVo> getOrderBillInfo(Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<OrderProfitsharingVo> getOrderProfitsharing(Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> sendOrderBillInfo(Long orderId, Long driverId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<OrderPayVo> getOrderPayVo(String orderNo, Long customerId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> updateOrderPayStatus(String orderNo) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<OrderRewardVo> getOrderRewardFee(String orderNo) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> updateCouponAmount(Long orderId, BigDecimal couponAmount) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }

            @Override
            public Result<Boolean> customerCancelNoAcceptOrder(Long orderId) {
                throw new GuiguException(ResultCodeEnum.DATA_ERROR);
            }
        };
    }
}
