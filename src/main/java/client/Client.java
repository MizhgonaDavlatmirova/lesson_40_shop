package client;

import app.controller.CustomerController;
import app.controller.ProductController;
import app.domain.Customer;
import app.exceptions.*;
import app.repository.CustomerRepository;
import app.repository.CustomerRepositoryImpl;
import app.repository.ProductRepository;
import app.repository.ProductRepositoryImpl;
import app.service.*;

import java.util.Scanner;

public class Client {

    private static Scanner scanner;
    private static ProductController productController;
    private static CustomerController customerController;

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepositoryImpl();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();


        ProductService productService = new ProductServiceImpl(productRepository);
        CustomerService customerService = new CustomerServiceImpl(customerRepository, productService);

        // Создаём объекты контроллеров Магазина (к ним мы и будем обращатся в коде клиента)
        productController = new ProductController(productService);
        customerController = new CustomerController(customerService);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Выберите действие:");
                System.out.println("1. Операции с продуктами.");
                System.out.println("2. Операции с покупателями.");
                System.out.println("0. Выход.");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } catch (ProductUpdateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void productOperations() throws ProductSaveException, ProductUpdateException, ProductNotFoundException {
        while (true) {

            System.out.println("Выбирите действие:");
            System.out.println("1. Сохранение продукта.");
            System.out.println("2. ПОлучение всех продуктов.");
            System.out.println("3. Получение одного продукта.");
            System.out.println("4. Изменение одного продукта.");
            System.out.println("5. Удаление  продукта по идентификатору.");
            System.out.println("6. Удаление продукта по названию.");
            System.out.println("7. Восстанавление продукта.");
            System.out.println("8. Получение количества продуктов.");
            System.out.println("9. Получение общей стоимости продуков.");
            System.out.println("10. Получение средней стоимости продуктов.");
            System.out.println("0. Отмена.");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Введите название продукта:");
                    String title = scanner.nextLine();
                    System.out.println("Введите цену продукта:");
                    double price = Double.parseDouble(scanner.nextLine());
                    productController.save(title, price);
                    break;
                case 2:
                    productController.getAllActiveProducts().forEach(x -> System.out.println(x));
                    break;
                case 3:
                    System.out.println("Введите идентификатор продукта:");
                    long id = Long.parseLong(scanner.nextLine());
                    System.out.println(productController.getActiveProductById(id));
                    break;
                case 4:
                    System.out.println("Введите идентификатор продукта:");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Введите новую цену продукта:");
                    price = Double.parseDouble(scanner.nextLine());
                    productController.updateActiveProduct(id, price);
                    break;
                case 5:
                    System.out.println("Введите идентификатор продукта:");
                    id = Long.parseLong(scanner.nextLine());
                    productController.deleteById(id);
                    break;
                case 6:
                    System.out.println("Введите название продукта:");
                    title = scanner.nextLine();
                    productController.deleteByTitle(title);
                    break;
                case 7:
                    System.out.println("Введите идентификатор продукта:");
                    id = Long.parseLong(scanner.nextLine());
                    productController.restoreById(id);
                    break;
                case 8:
                    System.out.println("Количество продуктов - " + productController.getActiveProductsNumber());
                    break;
                case 9:
                    System.out.println("Общая стоимость продуктов - " + productController.getActiveProductTotalCost());
                    break;
                case 10:
                    System.out.println("Средняя стоимость продуктов - " + productController.getActiveProductAveragePrice());
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Некорректный ввод!");
                    break;
            }
        }
    }


    public static void customerOperations() throws CustomerSaveExceptions, CustomerUpdateException, CustomerNotFoundException, ProductNotFoundException {
        while (true) {


            System.out.println("Выбирите действие:");
            System.out.println("1. СОхранение покупателя.");
            System.out.println("2. ПОлучение всех покупателей.");
            System.out.println("3. Получение одного покупателя.");
            System.out.println("4. Изменение одного покупателя.");
            System.out.println("5. Удаление  покупателя по идентификатору.");
            System.out.println("6. Удаление покупателя по названию.");
            System.out.println("7. Восстанавление покупателя.");
            System.out.println("8. Получение количество покупателей.");
            System.out.println("9. Получение общей стоимости покупателей.");
            System.out.println("10. Получение средней стоимости покупателей.");
            System.out.println("11. Добавление продукта в корзину.");
            System.out.println("12. Удалени продукта из корзины.");
            System.out.println("13. Отчистка корзины.");
            System.out.println("13. Ответ.");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("ВВедите имя покупателя: ");
                    String name = scanner.nextLine();
                    customerController.save(name);
                    break;
                case 2:
                    customerController.getAllActiveCustomers().forEach(x -> System.out.println());
                    break;
                case 3:
                    System.out.println("Введите идентификатор покупателя: ");
                    long id = Long.parseLong(scanner.nextLine());
                    System.out.println(customerController.getAllActiveCustomersById(id));
                    break;
                case 4:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Введите новое имя покупателя");
                    name = scanner.nextLine();
                    customerController.updateActiveCustomer(id, name);
                    break;
                case 5:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    customerController.deleteById(id);
                    break;
                case 6:
                    System.out.println("ВВедите имя покупателя: ");
                    name = scanner.nextLine();
                    customerController.deleteByName(name);
                    break;
                case 7:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    customerController.restoreById(id);
                    break;
                case 8:
                    System.out.println("Количество покупателей - " + customerController.getActiveCustomersNumber());
                    break;
                case 9:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Стоимост корзины покупателяЮ - " + customerController.getCustomersCartTotalPrice(id));
                    break;
                case 10:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Средняя стоимость продукта в корзине - " + customerController.getCustomersCartAveragePrice(id));
                    break;
                case 11:
                    System.out.println("Введите идентификатор покупателя: ");
                    long customerId = Long.parseLong(scanner.nextLine());
                    System.out.println("Введите идентификатор продукта");
                    long productId = Long.parseLong(scanner.nextLine());
                    customerController.addProductToCustomersCart(customerId, productId);
                    break;
                case 12:
                    System.out.println("Введите идентификатор покупателя: ");
                    customerId = Long.parseLong(scanner.nextLine());
                    System.out.println("Введите идентификатор продукта");
                    productId = Long.parseLong(scanner.nextLine());
                    customerController.deleteProductFromCustomersCart(customerId, productId);
                    break;
                case 13:
                    System.out.println("Введите идентификатор покупателя: ");
                    id = Long.parseLong(scanner.nextLine());
                    customerController.clearCustomersCart(id);
                    break;

                default:
                    System.out.println("Некоректный ввод");
                    break;
            }

        }

    }
}

