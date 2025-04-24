package com.nextdigital.app.order.application;

import com.nextdigital.app.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> create();
    boolean pay(Order order);
    List<Order> getOrders();
}
