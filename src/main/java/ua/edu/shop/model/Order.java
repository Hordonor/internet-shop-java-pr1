package ua.edu.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Order {
    private final int id;
    private final List<Product> products;
    private final BigDecimal totalPrice;
    private final LocalDateTime createdAt;
    @Setter
    private String status;

    public Order(int id, Cart cart) {
        this.id = id;
        this.products = Collections.unmodifiableList(new ArrayList<>(cart.getProducts()));
        this.totalPrice = cart.getTotalPrice();
        this.createdAt = LocalDateTime.now();
        this.status = "Нове";
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Замовлення №").append(id)
                .append(" | ").append(createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")))
                .append(" | Статус: ").append(status).append('\n');
        for (Product product : products) {
            result.append("  ").append(product).append('\n');
        }
        return result.append("Сума: ").append(totalPrice.toPlainString()).append(" грн").toString();
    }
}
