package com.nextdigital.app.order.infra;

import com.nextdigital.app.order.application.OrderRepository;
import com.nextdigital.app.order.application.OrderService;
import com.nextdigital.app.order.domain.Order;
import com.nextdigital.app.payment.application.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    @Override
    public Optional<Order> create() {
        return orderRepository.create();
    }

    @Override
    public boolean pay(Order order) {
        try {
            log.info("Paying order {}", order);
            return paymentService.processPayment(order.id());
        } catch (Exception e) {
            System.err.println("Error occurred while paying order: " + order.id() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }
}
