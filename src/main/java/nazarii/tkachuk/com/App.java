package nazarii.tkachuk.com;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.services.CustomerService;
import nazarii.tkachuk.com.services.OrderService;
import nazarii.tkachuk.com.services.ProductService;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class App {
    public static String filePath = "cusomers.csv";

    public static void main(String[] args) {

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        Customer customer1 = new Customer(1, "Jack", "Bush", "503730412");
        Customer customer2 = new Customer(2, "Mary", "Bush", "536546126");
        Customer customer3 = new Customer(3, "Klara", "Kent", "046322342");
        Customer customer4 = new Customer(4, "Frank", "Marlboro", "875743344");
        Customer customer5 = new Customer(5, "Ivan", "Loft", "987654323");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);

        Product product1 = new Product(1, "Critical+", new BigDecimal("25"), "Упаковка: 3 насіння." +
                "Виробник: Dinafem Seeds. Різновид: сатіва 50%; індика 50%. " + "Ефект: потужний; збалансований. " +
                "Фем. / Рег. : фемінізовані. Цвітіння: 45-50 днів. " + "Розмір: 2.5 м. " +
                "Урожай: 625 г / м2 (індаур); 1300 г / рослина (аутдор). Аромат: лимонний. " + "ТГК: 12-16%");
        Product product2 = new Product(2, "Amnesia Lemon", new BigDecimal("16.30"), "Упаковка: 3 насіння." +
                "Різновид: сатіва 60%; індика 40%" + "Ефект: потужний; знеболюючий" + "Фем. / Рег. : фемінізовані" +
                "Цвітіння: 7-8 тижнів" + "Розмір: 100-120 см" + "Урожай: 500 г / рослина" + "ТГК: 20.5%");
        Product product3 = new Product(3, "Кубанський убивця", new BigDecimal("18"), "Упаковка: 3 насіння." +
                "Різновид: сатіва 60%; індика 40%" + "Ефект потужний; розслабляючий" + "Фем. / Рег. : фемінізовані" +
                "Цвітіння: 50-60 днів" + "Розмір: 100-130 см" +
                "Урожай: 500-600 г / м2 (приміщення); 900-1500 г / рослина (грунт)" + "ТГК: 22%");
        Product product4 = new Product(4, "Black Widow ", new BigDecimal("12.20"), "Упаковка: 3 насіння." +
                "Різновид: сатіва 25%; індика 75%" + "Ефект: потужний; розслабляючий" + "Фем. / Рег. : фемінізовані" +
                "Цвітіння: 8-9 тижнів" + "Розмір: 100-120 см." + "Урожай: 400-500 г / м2" + "ТГК: 21%");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        Order order1 = new Order(1, LocalDate.of(1945, 1, 13), 37, customer2, product3);
        Order order2 = new Order(2, LocalDate.of(1913, Month.FEBRUARY, 11), 10, customer3, product1);
        Order order3 = new Order(3, LocalDate.of(1913, Month.FEBRUARY, 11), 103, customer3, product2);
        Order order4 = new Order(4, LocalDate.now(), 20, customer5, product1);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        FileWriterUtil.writeTextToFile("products.csv", CSVFormatterUtil.toCSVStringNoFormat(products));
        FileWriterUtil.writeTextToFile("orders.csv", CSVFormatterUtil.toCSVStringNoFormat(orders));
        FileWriterUtil.writeTextToFile("customers.csv", CSVFormatterUtil.toCSVStringNoFormat(customers));


        Product newProduct = ProductService.createNewProduct("product.csv","Жорсткезне єбашиво++",
                20.40,"Мама мене їбошить");
        products.add(newProduct);
        FileWriterUtil.writeTextToFile("product.csv", newProduct.toCSVFileString());
        Product newProduct1 = ProductService.createNewProduct("products.csv","Коломийський грьоб",
                4.40,"Тату мене їбошить");
        products.add(newProduct1);
        FileWriterUtil.writeTextToFile("products.csv", newProduct1.toCSVFileString());


        Order newOrder = OrderService.createNewOrder("orders.csv", 993,11,30,1000,3,5);
        orders.add(newOrder);
        FileWriterUtil.writeTextToFile("orders.csv",newOrder.toCSVFileString());


        CustomerService.deleteCustomerByID("customers.csv",7);
        CustomerService.editCustomerByID("customers.csv",8,"Nigga", "Corn","981674230" );

        Customer newCustomer = CustomerService.createNewCustomer("customers.csv",
                "Alah","Babah","981674239");
        customers.add(newCustomer);
        FileWriterUtil.writeTextToFile("customers.csv", newCustomer.toCSVFileString());


    }
}
