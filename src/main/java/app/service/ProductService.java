package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;

import java.util.List;

public interface ProductService {

    Product save(Product product) throws ProductSaveException;
    List<Product> getAllActiveProducts();
    Product getActiveProductById(Long id)throws ProductNotFoundException;
    void updateActiveProduct(Product product) throws ProductUpdateException, ProductNotFoundException;
    void deleteById(Long id) throws ProductNotFoundException;
    void deleteByTitle(String title);
    void restoreById(Long id);
    int getActiveProductsNumber();
    double getActiveProductTotalCost();
    double getActiveProductAveragePrice();


}
