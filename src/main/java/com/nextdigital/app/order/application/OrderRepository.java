package com.nextdigital.app.order.application;

import com.nextdigital.app.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> create();
    Order update(Long id, Order order);
    double getOrderTotal(Long id);
    List<Order>  getOrders();
}
