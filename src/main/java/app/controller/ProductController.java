package app.controller;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.service.ProductService;
import app.service.ProductUpdateException;

import java.util.List;

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;


    }
    public Product save(String title, double price) throws ProductSaveException {
        Product product = new Product( title,  price);
        return service.save(product);

    }
    public  List<Product> getAllActiveProducts() {
        return service.getAllActiveProducts();

    }
    public Product getActiveProductById(Long id)throws ProductNotFoundException {
        return service.getActiveProductById(id);
    }
    public void updateActiveProduct(Long id, double price) throws ProductUpdateException, ProductNotFoundException {

    Product product = new Product(id, price);
    service.updateActiveProduct(product);
    }

    public void deleteById(Long id) throws ProductNotFoundException {
        service.deleteById(id);
    }
    public void deleteByTitle(String title) {
        service.deleteByTitle(title);
    }
    public void restoreById(Long id) {
        service.restoreById(id);
    }
    public int getActiveProductsNumber() {
        return service.getActiveProductsNumber();

    }
    public double getActiveProductTotalCost() {

        return service.getActiveProductTotalCost();
    }
    public double getActiveProductAveragePrice() {
        return service.getActiveProductAveragePrice();
    }

}

