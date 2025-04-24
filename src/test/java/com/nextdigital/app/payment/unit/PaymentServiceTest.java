package com.nextdigital.app.payment.unit;

import com.nextdigital.app.order.application.OrderRepository;
import com.nextdigital.app.order.application.OrderService;
import com.nextdigital.app.order.domain.Order;
import com.nextdigital.app.order.domain.OrderStatus;
import com.nextdigital.app.order.infra.OrderRepositoryImpl;
import com.nextdigital.app.order.infra.OrderServiceImpl;
import com.nextdigital.app.payment.application.PaymentService;
import com.nextdigital.app.payment.infra.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static junit.framework.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {
    private static OrderRepository orderRepository;
    private static PaymentService paymentService;
    private static PaymentService mockedPaymentService;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepositoryImpl();
        paymentService = new PaymentServiceImpl(orderRepository);
        mockedPaymentService = mock(PaymentService.class);

    }

    @Test
    public void shouldMarkAsPaidIfPaymentProcessedOK() {
        OrderService service = new OrderServiceImpl(orderRepository, paymentService);
        Optional<Order> o = service.create();

        assert o.isPresent();

        assertFalse(orderRepository.getOrders().isEmpty());
        assertTrue(service.pay(o.get()));
        assertSame(OrderStatus.PAID, orderRepository.getOrders().getFirst().status());
    }

    @Test
    public void shouldNotMarkAsPaidIfPaymentFails() throws Exception {
        // Arrange
        OrderService service = new OrderServiceImpl(orderRepository, mockedPaymentService);
        Optional<Order> o = service.create();

        assert o.isPresent();

        when(mockedPaymentService.processPayment(any())).thenThrow(new RuntimeException("Runtime error paying"));

        // Act
        boolean result = service.pay(o.get());

        // Assert
        assertFalse(result);
        assertEquals(OrderStatus.CREATED, orderRepository.getOrders().getFirst().status());
    }

}
