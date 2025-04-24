package com.nextdigital.app.order.unit;

import com.nextdigital.app.order.application.OrderRepository;
import com.nextdigital.app.order.application.OrderService;
import com.nextdigital.app.order.domain.Order;
import com.nextdigital.app.order.infra.OrderRepositoryImpl;
import com.nextdigital.app.order.infra.OrderServiceImpl;
import com.nextdigital.app.payment.application.PaymentService;
import com.nextdigital.app.payment.infra.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class OrderServiceTest {

    private static OrderRepository orderRepository;
    private static PaymentService paymentService;

    @BeforeAll
    public static void setUp() {
        orderRepository = new OrderRepositoryImpl();
        paymentService = new PaymentServiceImpl(orderRepository);
    }
    @Test
    public void createOrder_shouldReturnOrderCreated() {
        OrderService orderService = new OrderServiceImpl(orderRepository, paymentService);
        Optional<Order> o = orderService.create();

        assertTrue(o.isPresent());
    }

    @Test
    public void createOrder_shouldReturnOrderPriceZero() {
        OrderService orderService = new OrderServiceImpl(orderRepository, paymentService);
        Optional<Order> o = orderService.create();

        assertTrue(o.isPresent());
        assertEquals(0.0, o.get().getTotalPrice());
    }
}
