package app.repository;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {

  private final Map<Long, Customer>  database = new HashMap<>();
  private long currentId = 0;

    @Override
    public Customer save(Customer customer) {
        customer.setId(++currentId);
        database.put(currentId, customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
//        return.database.values().stream().toList();
        return new ArrayList<>(database.values());

    }

    @Override
    public Customer findById(Long id) {
        return database.get(id);
    }

    @Override
    public void update(Customer customer) {
        Long id = customer.getId();
        String newName = customer.getName();

        Customer existedCustomer = findById(id);
        if (existedCustomer != null) {
            existedCustomer.setName(newName);
        }

    }

    @Override
    public void removeById(Long id) {
        database.remove(id);

    }

    //
    public static void main(String[] args) {

        // создаём объект репозитория
        CustomerRepository repository = new CustomerRepositoryImpl();

        // тестируем метод save
        repository.save(new Customer("John", true));
        repository.save(new Customer("Jack", true));
        repository.save(new Customer("Jenna", true));


//        repository.findAll().forEach(x-> System.out.println(x));
//
//        System.out.println(repository.findById(3L));
//
//        System.out.println(repository.findById(1L));
//        repository.update(new Customer(1L, "John Doe"));
//        System.out.println(repository.findById(1L));
//

//        тестируем метод remove
//        repository.removeById(1L);
//        repository.findAll().forEach(x-> System.out.println(x));
//




    }
}
