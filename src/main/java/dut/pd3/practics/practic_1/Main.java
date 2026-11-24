package dut.pd3.practics.practic_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення об'єктів класу Product з вказівкою категорії
        Product product1 = new Product(1, "Ноутбук", 19999.99,
                "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50,
                "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00,
                "Бездротові навушники з шумозаглушенням", accessories);

        // Оголошення товарів і категорій з попереднього коду
        Cart cart = new Cart();
        List<Product> products = List.of(product1, product2, product3);
        List<Order> orderHistory = new ArrayList<>();
        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Знайти товар за назвою або категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    Product productToAdd = findProductById(products, id);
                    if (productToAdd == null) {
                        System.out.println("Товар з таким ID не знайдено");
                    } else {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар додано до кошика.");
                    }
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній.");
                    } else {
                        System.out.println(cart);
                        System.out.println("Введіть ID товару для видалення з кошика:");
                        int idToRemove = scanner.nextInt();
                        Product productToRemove = null;
                        for (Product product : cart.getProducts()) {
                            if (product.getId() == idToRemove) {
                                productToRemove = product;
                                break;
                            }
                        }
                        if (productToRemove == null) {
                            System.out.println("Товар з таким ID не знайдено у кошику.");
                        } else {
                            cart.removeProduct(productToRemove);
                            System.out.println("Товар видалено з кошика.");
                        }
                    }
                    break;
                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");
                        for (Order order : orderHistory) {
                            System.out.println(order);
                            System.out.println();
                        }
                    }
                    break;
                case 7:
                    scanner.nextLine(); // Очищення символу нового рядка після nextInt()
                    System.out.println("Введіть назву товару або категорію для пошуку:");
                    String query = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    boolean found = false;
                    for (Product product : products) {
                        if (product.getName().toLowerCase(Locale.ROOT).contains(query)
                                || product.getCategory().getName().toLowerCase(Locale.ROOT).contains(query)) {
                            System.out.println(product);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Товари не знайдено.");
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }

    private static Product findProductById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
