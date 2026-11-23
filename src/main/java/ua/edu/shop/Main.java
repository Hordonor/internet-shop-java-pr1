package ua.edu.shop;

import ua.edu.shop.model.Order;
import ua.edu.shop.model.Product;
import ua.edu.shop.service.ShopService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ShopService shop = new ShopService();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("Вітаємо в інтернет-магазині!");
        while (true) {
            printMenu();
            Integer choice = readInteger("Ваш вибір: ");
            if (choice == null) {
                break;
            }
            switch (choice) {
                case 1 -> showCatalog();
                case 2 -> searchCatalog();
                case 3 -> addToCart();
                case 4 -> System.out.println(shop.getCart());
                case 5 -> removeFromCart();
                case 6 -> checkout();
                case 7 -> showOrderHistory();
                case 0 -> {
                    System.out.println("Дякуємо за покупку. До побачення!");
                    return;
                }
                default -> System.out.println("Невідома опція. Оберіть пункт меню від 0 до 7.");
            }
        }
        System.out.println("Завершення роботи магазину.");
    }

    private void printMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.println("1 - Переглянути каталог товарів");
        System.out.println("2 - Пошук за назвою або категорією");
        System.out.println("3 - Додати товар до кошика");
        System.out.println("4 - Переглянути кошик");
        System.out.println("5 - Видалити товар із кошика");
        System.out.println("6 - Оформити замовлення");
        System.out.println("7 - Історія замовлень");
        System.out.println("0 - Вийти");
    }

    private void showCatalog() {
        System.out.println("\n--- КАТАЛОГ ---");
        shop.getProducts().forEach(System.out::println);
    }

    private void searchCatalog() {
        System.out.print("Введіть назву товару або категорію: ");
        String query = scanner.nextLine().trim();
        List<Product> matches = shop.searchProducts(query);
        if (matches.isEmpty()) {
            System.out.println("Нічого не знайдено.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    private void addToCart() {
        Integer id = readInteger("Введіть ID товару: ");
        if (id == null) return;
        Product product = shop.findProductById(id);
        if (product == null) {
            System.out.println("Товар із таким ID не знайдено.");
            return;
        }
        shop.getCart().addProduct(product);
        System.out.println("Додано до кошика: " + product.getName());
    }

    private void removeFromCart() {
        if (shop.getCart().isEmpty()) {
            System.out.println("Кошик порожній.");
            return;
        }
        System.out.println(shop.getCart());
        Integer id = readInteger("Введіть ID товару для видалення (усі одиниці цього товару): ");
        if (id == null) return;
        if (shop.getCart().removeProduct(id)) {
            System.out.println("Товар видалено з кошика.");
        } else {
            System.out.println("Товару з таким ID немає в кошику.");
        }
    }

    private void checkout() {
        Order order = shop.checkout();
        if (order == null) {
            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
            return;
        }
        System.out.println("Замовлення оформлено:");
        System.out.println(order);
    }

    private void showOrderHistory() {
        List<Order> orders = shop.getOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
            return;
        }
        System.out.println("\n--- ІСТОРІЯ ЗАМОВЛЕНЬ ---");
        orders.forEach(order -> System.out.println(order + "\n"));
    }

    private Integer readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Введіть число.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Некоректне число. Спробуйте ще раз.");
            }
        }
    }
}
