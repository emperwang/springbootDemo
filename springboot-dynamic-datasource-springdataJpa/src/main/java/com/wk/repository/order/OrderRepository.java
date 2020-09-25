package com.wk.repository.order;

import com.wk.Entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer> {
}
