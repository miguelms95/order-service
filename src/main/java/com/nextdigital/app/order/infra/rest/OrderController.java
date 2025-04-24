package com.nextdigital.app.order.infra.rest;

import com.nextdigital.app.order.application.OrderService;
import com.nextdigital.app.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder() {
        return orderService.create()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payOrder(@PathVariable Long id, @RequestBody Order orderPayload) {
        if (!id.equals(orderPayload.id())) {
            return ResponseEntity.badRequest().build();
        }
        boolean paid = orderService.pay(orderPayload);
        if (paid) {
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Payment OK",
                            "order", orderPayload
                    )
            );
        } else {
            return ResponseEntity.status(402).body(Map.of("message", "Payment failed"));
        }
    }


    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}