package app.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private boolean active;
    private List<Product> products = new ArrayList<>();

    // Метод, который добавляет один продукт в корзину покупателя.
    public void addProduct(Product product) {
        if (product.isActive()) {
            products.add(product);
        }
    }

    // Получение списка всех активных продуктов покупателя.
    public List<Product> getAllActiveProducts() {
        return products
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    // Удаление продукта из списка по идентификатору продукта
    public void removeProductById(Long id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    // Полная очистка списка продуктов
    public void removeAllProducts() {
        products.clear();
    }

    // Получение общей стоимости всех активных продуктов в корзине
    public double getAllActiveProductsTotalCost() {
        return products
                .stream()
                .filter(x -> x.isActive())
                .mapToDouble(x -> x.getPrice())
                .sum();
    }

    // Получение средней стоимости активных продуктов в корзине
    public double getAllActiveProductsAveragePrice() {
        return products
                .stream()
                .filter(x -> x.isActive())
                .mapToDouble(x -> x.getPrice())
                .average()
                .orElse(0.0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return active == customer.active && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(products, customer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, products);
    }

    @Override
    public String toString() {
        return String.format("Покупатель: id - %d, имя - %s, активен - %s.",
                id, name, active ? "да" : "нет");
    }



}
