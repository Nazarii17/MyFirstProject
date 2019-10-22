package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.mappers.OrderMapper;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderService {

    public static Order getOrderById(String filePath, int id) {
        List<Order> orderList = FileReaderUtil.readObjects(filePath, new OrderMapper());
        Order correctOne = null;
        for (Order order : orderList) {
            if (order.getId() == id) {
                correctOne = order;
            }
        }
        return correctOne;
    }

    public static int getIndexById(List<Order> list, int id) {

        Integer correctOne = null;
        for (Order order : list) {
            if (order.getId() == id) {
                correctOne = list.indexOf(order);

            }
        }
        return correctOne;
    }

    public static Order getOrdererByDate(String filePath, Integer year, Integer month, Integer day) {

        List<Order> orderList = FileReaderUtil.readObjects(filePath, new OrderMapper());
        Order correctOne = null;
        for (Order order : orderList) {
            if (order.getDateOfOrder().equals(LocalDate.of(year, month, day))) {
                correctOne = order;
            }
        }
        return correctOne;
    }

    public static boolean isOrderExist(String filePath, Order newOrder) {

        List<Order> list = FileReaderUtil.readObjects(filePath, new OrderMapper());

        for (Order order : list) {
            if (order.equals(newOrder)) {
                return true;
            }
        }

        return false;
    }

    public static Order createNewOrder(String filePath, Integer year, Integer month, Integer day,
                                       Integer quantity, Integer customersID, Integer productsID) {

        if (!EntityIDService.isFileExist(filePath)) {
            FileWriterUtil.createFileIfNotExists(filePath);
        }

        EntityIDService.createFileWithMaxID(filePath, new OrderMapper());

        Order newOrder = new Order(EntityIDService.generateIDFromFile(EntityIDService.getIDFilePath(filePath)),
                LocalDate.of(year, month, day),
                quantity,
                new Customer(customersID),
                new Product(productsID),
                BigDecimal.valueOf(quantity).multiply(ProductService.getProductById("products.csv", productsID).getPrice()));


        if (isOrderExist(filePath, newOrder)) {
            throw new RuntimeException("This order already exist");

        }

        return newOrder;
    }

    public static void deleteOrderByID(String filePath, int id) {
        if (!EntityIDService.isIDExist(filePath, id, new OrderMapper())) {
            throw new RuntimeException("ERROR!\n Order with id № " + id + " not found!!!");
        }
        List<Order> orderList = FileReaderUtil.readObjects(filePath, new OrderMapper());
        orderList.remove(getIndexById(orderList, id));


        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(orderList));
    }

    public static void editOrderByID(String filePath, int id, Integer year, Integer month, Integer day,
                                     Integer quantity, Integer customersID, Integer productsID) {
        List<Order> orderList = FileReaderUtil.readObjects(filePath, new OrderMapper());

        if (!EntityIDService.isIDExist(filePath, id, new OrderMapper())) {
            throw new RuntimeException("ERROR!\n Order with id № " + id + " not found!!!");
        }
        Integer index = null;
        for (Order order : orderList) {
            if (order.getId() == id) {
                index = orderList.indexOf(order);
            }
        }
        orderList.set(index, new Order(id,
                LocalDate.of(year, month, day),
                quantity,
                new Customer(customersID),
                new Product(productsID),
                BigDecimal.valueOf(quantity).multiply(ProductService.getProductById("products.csv", productsID).getPrice())
        ));

        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(orderList));
    }
}
