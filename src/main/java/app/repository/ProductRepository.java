package app.repository;

import app.domain.Product;

import java.util.List;

public interface ProductRepository {


    Product save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void update(Product product);
    void removeById(Long id);


}

