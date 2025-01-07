package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveExceptions;
import app.exceptions.CustomerUpdateException;
import app.exceptions.ProductNotFoundException;
import app.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ProductService productService;

    public CustomerServiceImpl(CustomerRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public Customer save(Customer customer) throws CustomerSaveExceptions {

        if (customer == null) {
            throw new CustomerSaveExceptions("Customer cannot be null");

        }

        if (customer.getName() == null || customer.getName().length() < 3) {
            throw new CustomerSaveExceptions("Customer's name is incorrect");
        }

        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Customer getAllActiveCustomersById(Long id) throws CustomerNotFoundException {

        Customer customer =  repository.findById(id);
        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException(String.format(
                    "Customer with ID %d doesn't exist", id
            ));
        }
        return customer;
    }

    @Override
    public void updateActiveCustomer(Customer customer) throws CustomerUpdateException, CustomerNotFoundException {
        if (customer == null) {
            throw new CustomerUpdateException("Customer cannot be null");

        }

        if (customer.getName() == null || customer.getName().length() < 3) {
            throw new CustomerUpdateException("Customer's name is incorrect");
        }

        Customer existedCustomer = getAllActiveCustomersById(customer.getId());

        if (existedCustomer == null) {
            throw new CustomerUpdateException("Customer inactive or doesn't exist");

        }
    }

    @Override
    public void deleteById(Long id) throws CustomerUpdateException, CustomerNotFoundException {

        Customer customer = getAllActiveCustomersById(id) ;

        if (customer != null) {
            customer.setActive(false);
        }

    }

    @Override
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));

    }

    @Override
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
            if (customer != null) {
                customer.setActive(true);
            }
        }

    @Override
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCustomersCartTotalPrice(Long id) throws CustomerNotFoundException{
        return getAllActiveCustomersById(id).getAllActiveProductsTotalCost();

    }

    @Override
    public double getCustomersCartAveragePrice(Long id) throws CustomerNotFoundException {
        return getAllActiveCustomersById(id).getAllActiveProductsAveragePrice();
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException, ProductNotFoundException {
        Customer customer = getAllActiveCustomersById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.addProduct(product);

    }

    @Override
    public void deleteProductFromCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException {
        Customer customer = getAllActiveCustomersById(customerId);
        customer.removeProductById(productId);

    }

    @Override
    public void clearCustomersCart(Long id) throws CustomerNotFoundException {
        Customer customer = getAllActiveCustomersById(id);
        customer.removeAllProducts();

    }

    }







