package ua.edu.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;

    @Override
    public String toString() {
        return String.format("[%d] %s | %s грн | %s | %s",
                id, name, price.toPlainString(), category, description);
    }
}
