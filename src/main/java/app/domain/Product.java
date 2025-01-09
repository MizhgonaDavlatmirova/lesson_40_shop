package app.domain;

import java.util.Objects;
public class Product {


    private Long id;
    private String title;
    private double price;
    private boolean active;

    public Product(Long id, double price) {
        this.id = id;
        this.price = price;
    }

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Product(String title, double price, boolean active) {
        this.title = title;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Продукт: id - %d, наименование - %s, цена - %.2f, активен - %s.",
                id, title, price, active ? "да" : "нет");
    }
}
