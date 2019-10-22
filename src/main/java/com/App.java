package com;

import com.entities.Customer;
import com.entities.Order;
import com.entities.Product;
import com.services.OrderService;
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

        Customer customer1 = new Customer("Jack", "Bush", 1, "503730412");
        Customer customer2 = new Customer("Mary", "Bush", 2, "536546126");
        Customer customer3 = new Customer("Klara", "Kent", 3, "046322342");
        Customer customer4 = new Customer("Frank", "Marlboro", 4, "875743344");
        Customer customer5 = new Customer("Ivan", "Loft", 5, "987654323");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);

        Product product1 = new Product("Critical+", 1, new BigDecimal("25"), "Упаковка: 3 насіння." +
                "Виробник: Dinafem Seeds. Різновид: сатіва 50%; індика 50%. " + "Ефект: потужний; збалансований. " +
                "Фем. / Рег. : фемінізовані. Цвітіння: 45-50 днів. " + "Розмір: 2.5 м. " +
                "Урожай: 625 г / м2 (індаур); 1300 г / рослина (аутдор). Аромат: лимонний. " + "ТГК: 12-16%");
        Product product2 = new Product("Amnesia Lemon", 2, new BigDecimal("16.30"), "Упаковка: 3 насіння." +
                "Різновид: сатіва 60%; індика 40%" + "Ефект: потужний; знеболюючий" + "Фем. / Рег. : фемінізовані" +
                "Цвітіння: 7-8 тижнів" + "Розмір: 100-120 см" + "Урожай: 500 г / рослина" + "ТГК: 20.5%");
        Product product3 = new Product("Кубанський убивця", 3, new BigDecimal("18"), "Упаковка: 3 насіння." +
                "Різновид: сатіва 60%; індика 40%" + "Ефект потужний; розслабляючий" + "Фем. / Рег. : фемінізовані" +
                "Цвітіння: 50-60 днів" + "Розмір: 100-130 см" +
                "Урожай: 500-600 г / м2 (приміщення); 900-1500 г / рослина (грунт)" + "ТГК: 22%");
        Product product4 = new Product("Black Widow ", 4, new BigDecimal("12.20"), "Упаковка: 3 насіння." +
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

//        FileWriterUtil.writeTextToFile("orders.csv",CSVFormatterUtil.toCSVStringNoFormat(orders));


//        List<Product> productList = FileReaderUtil.readObjects("products.csv", new ProductMapper());
//
//        FileWriterUtil.writeTextToFile("products1.csv", CSVFormatterUtil.toCSVStringNoFormat(productList));

//        FileWriterUtil.writeTextToFile("orders+.csv", CSVFormatterUtil.toCSVStringWithFormat(orders));
//        FileWriterUtil.writeTextToFile("orders-.csv", CSVFormatterUtil.toCSVStringNoFormat(orders));
//
//        List<Order> orders1 = FileReaderUtil.readObjects("orders+.csv", new OrderMapper());
//        List<Order> orders2 = FileReaderUtil.readObjects("orders-.csv", new OrderMapper());
//
//        FileWriterUtil.writeTextToFile("orders+1.csv", CSVFormatterUtil.toCSVStringWithFormat(orders1));
//        FileWriterUtil.writeTextToFile("orders-1.csv", CSVFormatterUtil.toCSVStringNoFormat(orders2));


//        EntityIDService.writeMaxIDFromListToFile("id.csv",products);

//        System.out.println(FileReaderUtil.readStringFromFile("id.csv"));
//        System.out.println(EntityIDService.generateIDFromFile("id.csv"));





//        Customer newCustomer = CustomerService.createNewCustomer("customers.csv",
//                "Alah","Babah","981374239");
//        customers.add(newCustomer);
//        FileWriterUtil.writeTextToFile("customers.csv", newCustomer.toCSVFileString());
//
//        Customer newCustomer1 = CustomerService.createNewCustomer("customers.csv",
//                "Jack","Daniels","989735554");
//        customers.add(newCustomer1);
//        FileWriterUtil.writeTextToFile("customers.csv", newCustomer1.toCSVFileString());

//        Product newProduct = ProductService.createNewProduct("product.csv","Жорсткезне єбашиво++",
//                                                                   20.40,"Мама мене їбошить");
//        products.add(newProduct);
//        FileWriterUtil.writeTextToFile("product.csv", newProduct.toCSVFileString());
////        Product newProduct1 = ProductService.createNewProduct("products.csv","Коломийський грьоб",
//                                                                   4.40,"Тату мене їбошить");
//        products.add(newProduct1);
//        FileWriterUtil.writeTextToFile("products.csv", newProduct1.toCSVFileString());


//

//        Order newOrder = OrderService.createNewOrder("orders.csv", 993,11,30,1000,3,5);
//        orders.add(newOrder);
//        FileWriterUtil.writeTextToFile("orders.csv",newOrder.toCSVFileString());


//        OrderService.deleteOrderByID("orders.csv",4);

//        ProductService.deleteProductByID("products.csv", 6);

//        CustomerService.deleteCustomerByID("customers.csv", 22);

//        CustomerService.editCustomerByID("customers.csv",32,"Cool","Cash", "231132554");

//        ProductService.editProductByID("products.csv", 2,"Косів Гуцулія+",100.33,"Зарплата у вчителів = 4000");

//        FileWriterUtil.writeTextToFile("orders.csv", CSVFormatterUtil.toCSVStringNoFormat(orders));
//        List<Order> list = FileReaderUtil.readObjects("orders.csv",new OrderMapper());
//        FileWriterUtil.writeTextToFile("orders.csv", CSVFormatterUtil.toCSVStringNoFormat(list));


//        Order newOrder = OrderService.createNewOrder("orders1.csv", 993,11,30,1000,3,5);
//        orders.add(newOrder);
//        FileWriterUtil.writeTextToFile("orders1.csv",newOrder.toCSVFileString());

//        OrderService.deleteOrderByID("orders.csv",5);
        OrderService.editOrderByID("orders.csv",9,1993,11,1,900,2,3);

    }
}
