package com.nextdigital.app.order.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
@Setter
@ToString
public class Order {
    public Long id;
    @Builder.Default
    public OrderStatus status = OrderStatus.CREATED;
    @Builder.Default
    public List<Item> items = new ArrayList<>();

    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.unitPrice() * item.quantity();
        }
        return total;
    }
}
