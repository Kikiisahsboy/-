package com.atguigu.daijia.order.client.cfg;
/*@Project_name:daijia-parent 
@Create 2024/12/9 下午4:59
Description:
*/

import com.atguigu.daijia.order.client.fallback.OrderFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
@Slf4j
public class config {
    @Bean
    public OrderFallback orderFallback() {
        return new OrderFallback();
    }
}
