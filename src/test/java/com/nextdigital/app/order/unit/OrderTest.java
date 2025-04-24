package com.nextdigital.app.order.unit;

import com.nextdigital.app.order.domain.Order;
import com.nextdigital.app.order.domain.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void OrderShouldBeCreated() {
        Order order = Order.builder().build();
        assertNotNull(order);
        assertEquals(OrderStatus.CREATED, order.status());
    }
}
