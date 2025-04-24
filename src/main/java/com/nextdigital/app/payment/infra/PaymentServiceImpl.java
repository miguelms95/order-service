package com.nextdigital.app.payment.infra;

import com.nextdigital.app.order.application.OrderRepository;
import com.nextdigital.app.order.domain.Order;
import com.nextdigital.app.order.domain.OrderStatus;
import com.nextdigital.app.payment.application.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;

    @Override
    public boolean processPayment(Long id) {
        orderRepository.update(id, Order.builder().status(OrderStatus.PAID).build());
        return true;
    }
}
