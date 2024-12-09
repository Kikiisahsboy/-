package com.atguigu.daijia.map.repository;
/*@Project_name:daijia-parent 
@Create 2024/11/18 下午4:51
Description:
*/

import com.atguigu.daijia.model.entity.map.OrderServiceLocation;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderServiceLocationRepository
        extends MongoRepository<OrderServiceLocation,String> {
    List<OrderServiceLocation> findByOrderIdOrderByCreateTimeAsc(Long orderId);
}
