package com.atguigu.daijia.order.repository;
/*@Project_name:daijia-parent 
@Create 2024/11/19 上午10:03
Description:
*/

import com.atguigu.daijia.model.entity.order.OrderMonitorRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMonitorRecordRepository extends MongoRepository<OrderMonitorRecord,String> {
}
