package com.nextdigital.app.order.infra;

import com.nextdigital.app.order.application.OrderRepository;
import com.nextdigital.app.order.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderRepositoryImpl implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    @Override
    public Optional<Order> create() {
        Order order = Order.builder().id(getNextOrderId()).build();
        orders.add(order);
        return Optional.of(order);
    }

    @Override
    public Order update(Long id, Order updatedOrder) {
        Optional<Order> existingOrder = getOrderFromId(id);
        if (existingOrder.isPresent()) {
            int index = orders.indexOf(existingOrder.get());
            Order newOrder = Order.builder()
                    .id(id)
                    .status(updatedOrder.status())
                    .items(updatedOrder.items())
                    .build();

            orders.set(index, newOrder);
            log.info("Order updated: {}", newOrder);
            return newOrder;
        } else {
            log.warn("Order not found with id {}", id);
            return null;
        }
    }


    @Override
    public double getOrderTotal(Long id) {
        Optional<Order> order = getOrderFromId(id);
        return order.map(Order::getTotalPrice).orElse(0.0);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    private Optional<Order> getOrderFromId(Long id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    private Long getNextOrderId() {
        if (orders.isEmpty()) {
            return 1L;
        }
        return orders.getLast().id() + 1;
    }
}
