package app.controller;

import app.domain.Customer;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveExceptions;
import app.exceptions.CustomerUpdateException;
import app.exceptions.ProductNotFoundException;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

       public Customer save(String name) throws CustomerSaveExceptions {
         Customer customer = new Customer(name);
         return service.save(customer);
       }
    public List<Customer> getAllActiveCustomers(){
        return service.getAllActiveCustomers();
    }
    public  Customer getAllActiveCustomersById(Long id) throws CustomerNotFoundException {
        return service.getAllActiveCustomersById(id);

    }
    public void updateActiveCustomer(Long id, String name ) throws CustomerUpdateException, CustomerUpdateException, CustomerNotFoundException {
        Customer customer = new Customer(id, name);
        service.updateActiveCustomer(customer);



    }
    public void deleteById(Long id) throws CustomerUpdateException, CustomerNotFoundException {
        service.deleteById(id);

    }
    public void deleteByName(String name) {
        service.deleteByName(name);

    }
    public void restoreById(Long id) {
        service.restoreById(id);

    }
    public int getActiveCustomersNumber() {
        return service.getActiveCustomersNumber();

    }
    public double getCustomersCartTotalPrice(Long id)throws CustomerNotFoundException {
        return service.getCustomersCartTotalPrice(id);

    }
    public double getCustomersCartAveragePrice(Long id)throws CustomerNotFoundException {
        return service.getCustomersCartAveragePrice(id);

    }
    public void addProductToCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException, ProductNotFoundException {
        service.addProductToCustomersCart(customerId, productId);

    }
    public void deleteProductFromCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException {
        service.deleteProductFromCustomersCart(customerId, productId);

    }
    public void clearCustomersCart(Long id) throws CustomerNotFoundException {
        service.clearCustomersCart(id);

    }
}
