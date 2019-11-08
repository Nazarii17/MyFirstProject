package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.mappers.OrderMapper;
import nazarii.tkachuk.com.providers.PropertiesProvider;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private EntityIDService entityIDService;

    private String orderFilePath;
    private final String ORDER_PROPERTY_NAME = "result.file.name.orders";

    public OrderService() {
        orderFilePath = PropertiesProvider.getProperty(ORDER_PROPERTY_NAME);
        entityIDService = new EntityIDService(orderFilePath);
    }

    public String getORDER_FILE_PATH() {
        return orderFilePath;
    }

    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();

    public Order getOrderById(int id) {
        List<Order> orderList = FileReaderUtil.readObjects(orderFilePath, new OrderMapper());

        if (!entityIDService.isIDExist(orderList, id)) {
            throw new RuntimeException("The order with ID № \"" + id + "\" isn't exist!!!\"");
        }
        Order correctOne = null;
        for (Order order : orderList) {
            if (order.getId() == id) {
                correctOne = order;
            }
        }
        return correctOne;
    }

    private int getIndexById(List<Order> list, int id) {

        if (!entityIDService.isIDExist(list, id)) {
            throw new RuntimeException("The order with ID № \"" + id + "\" isn't exist!!!\"");
        }
        Integer correctOne = null;
        for (Order order : list) {
            if (order.getId() == id) {
                correctOne = list.indexOf(order);
            }
        }
        return correctOne;
    }

    public List<Order> getOrdersByDate(Integer year, Integer month, Integer day) {

        List<Order> orderList = FileReaderUtil.readObjects(orderFilePath, new OrderMapper());
        List<Order> wantedOrders= new ArrayList<>();

        if (!isDateExist(orderList,year,month,day)){
            throw new RuntimeException("Order of \""+year+"-"+month+"-"+day+"\" isn't exist!!!");
        }

        for (Order order : orderList) {
            if (order.getOrderDate().equals(LocalDate.of(year, month, day))) {
                wantedOrders.add(order);
            }
        }
        return wantedOrders;
    }

    public <T extends Order> Boolean isDateExist(List<T> entities, Integer year, Integer month, Integer day){
        LocalDate checkedDate = entityIDService.buildDate(year,month,day);
        for (T t: entities){
            if (t.getOrderDate().equals(checkedDate)){
                return true;
            }
        }
        return false;
    }

    private boolean isOrderExist(Order targetOrder) {

        List<Order> list = FileReaderUtil.readObjects(orderFilePath, new OrderMapper());

        for (Order order : list) {
            if (order.equals(targetOrder)) {
                return true;
            }
        }
        return false;
    }

    public Order createNewOrder(Integer year, Integer month, Integer day,
                                Integer quantity, Integer customersID, Integer productsID) {
        Order order = buildNewOrder(year, month, day,
                quantity, customersID, productsID);

        if (isOrderExist(order)) {
            throw new RuntimeException("This order already exist");
        }

        return save(order);
    }

    private Order buildNewOrder(Integer year, Integer month, Integer day,
                               Integer quantity, Integer customersID, Integer productsID) {
        validateOrderData();

        LocalDate date = entityIDService.buildDate(year, month, day);
        Customer customer = customerService.getCustomerByID(customersID);
        Product product = productService.getProductById(productsID);

        return new Order(
                date,
                quantity,
                customer,
                product,
                BigDecimal.valueOf(quantity).multiply(productService.getProductById(productsID).getPrice()));
    }

    private Order validateOrderData() {
        return null;
    }

    private Order save(Order order) {
        final int id = entityIDService.generateId();

        order.setId(id);
        FileWriterUtil.writeToFile(orderFilePath, order.toCSVString());

        return order;
    }

    public void deleteOrderByID(int id) {
        if (!entityIDService.isIDExist(orderFilePath, id, new OrderMapper())) {
            throw new RuntimeException("\"Removal failed!!!\n Order with id № " + id + " not found!!!");
        }
        List<Order> orderList = FileReaderUtil.readObjects(orderFilePath, new OrderMapper());
        orderList.remove(getIndexById(orderList, id));

        FileWriterUtil.overwriteTextToFile(orderFilePath, CSVFormatterUtil.toCSVString(orderList));
        System.out.println("The order with ID № \"" + id + "\" was successfully deleted!!!");
    }

    public void editOrderByID(int id, Integer year, Integer month, Integer day,
                                     Integer quantity, Integer customersID, Integer productsID) {
        List<Order> orderList = FileReaderUtil.readObjects(orderFilePath, new OrderMapper());

//        Order targetOrder = getOrderById(id);
        Order targetOrder = null;
        for (Order order: orderList){
            if (order.getId()==id){
                targetOrder = order;
            }
        }

        if (targetOrder == null){
            throw new RuntimeException("Edition failed!!!\n Order with id № " + id + " not found!!!");
        }

//        validation()

        targetOrder.setOrderDate(entityIDService.buildDate(year,month,day));
        targetOrder.setQuantity(quantity);
        targetOrder.setCustomer(new Customer(customersID));
        targetOrder.setProduct(new Product(productsID));

        FileWriterUtil.overwriteTextToFile(orderFilePath, CSVFormatterUtil.toCSVString(orderList));
    }
}
