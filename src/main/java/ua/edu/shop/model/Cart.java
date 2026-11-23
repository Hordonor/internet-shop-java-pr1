package ua.edu.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(int productId) {
        return products.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Кошик порожній.";
        }

        StringBuilder result = new StringBuilder("Кошик:\n");
        for (Product product : products) {
            result.append("  ").append(product).append('\n');
        }
        return result.append("Разом: ").append(getTotalPrice().toPlainString()).append(" грн").toString();
    }
}
