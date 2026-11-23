package ua.edu.shop.service;

import ua.edu.shop.model.Cart;
import ua.edu.shop.model.Category;
import ua.edu.shop.model.Order;
import ua.edu.shop.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopService {
    private final List<Category> categories = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Order> orderHistory = new ArrayList<>();
    private final Cart cart = new Cart();

    public ShopService() {
        seedCatalog();
    }

    private void seedCatalog() {
        Category electronics = new Category(1, "Електроніка", "Техніка та електронні пристрої");
        Category smartphones = new Category(2, "Смартфони", "Мобільні телефони та аксесуари");
        Category accessories = new Category(3, "Аксесуари", "Корисні аксесуари для техніки");
        categories.addAll(List.of(electronics, smartphones, accessories));

        products.addAll(List.of(
                new Product(1, "Ноутбук Lenovo IdeaPad", new BigDecimal("19999.99"),
                        "Ноутбук для роботи та навчання", electronics),
                new Product(2, "Смартфон Samsung Galaxy", new BigDecimal("12999.50"),
                        "Смартфон із яскравим екраном", smartphones),
                new Product(3, "Навушники JBL Tune", new BigDecimal("2499.00"),
                        "Бездротові навушники з шумозаглушенням", accessories),
                new Product(4, "Миша Logitech M185", new BigDecimal("699.00"),
                        "Бездротова оптична миша", accessories),
                new Product(5, "Монітор Dell 24", new BigDecimal("7499.00"),
                        "Монітор Full HD для дому та офісу", electronics),
                new Product(6, "Планшет Apple iPad", new BigDecimal("18999.00"),
                        "Компактний планшет для роботи й розваг", electronics)
        ));
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public List<Category> getCategories() {
        return List.copyOf(categories);
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrderHistory() {
        return List.copyOf(orderHistory);
    }

    public Product findProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public List<Product> searchProducts(String query) {
        String normalized = query.trim().toLowerCase(Locale.ROOT);
        return products.stream()
                .filter(product -> product.getName().toLowerCase(Locale.ROOT).contains(normalized)
                        || product.getCategory().getName().toLowerCase(Locale.ROOT).contains(normalized))
                .toList();
    }

    public Order checkout() {
        if (cart.isEmpty()) {
            return null;
        }
        Order order = new Order(orderHistory.size() + 1, cart);
        orderHistory.add(order);
        cart.clear();
        return order;
    }
}
