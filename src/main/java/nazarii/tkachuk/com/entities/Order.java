package nazarii.tkachuk.com.entities;

import nazarii.tkachuk.com.enums.CSVFormats;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order extends EntityID implements CSVSerializable {
    private Integer id;
    private LocalDate dateOfOrder;
    private Integer quantity;
    private Customer customer;
    private Product product;
    private Integer customerID;
    private Integer productID;
    private BigDecimal price;

    public Order(int id, LocalDate dateOfOrder, Integer quantity, Customer customer, Product product) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.quantity = quantity;
        this.customer = customer;
        this.product = product;
        this.customerID = customer.getId();
        this.productID = product.getId();
        this.price = BigDecimal.valueOf(quantity).multiply(product.getPrice());
    }

    public Order(int id, LocalDate dateOfOrder, Integer quantity, Customer customer, Product product,BigDecimal price) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.quantity = quantity;
        this.customer = customer;
        this.product = product;
        this.customerID = customer.getId();
        this.productID = product.getId();
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateTime) {
        this.dateOfOrder = dateTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + id +
                ", dateTime=" + dateOfOrder +
                ", quantity=" + quantity +
                ", customerID=" + customerID +
                ", productID=" + productID +
                '}';
    }

    @Override
    public String toCSVWithFormatString() {
        return String.format(CSVFormats.ORDER.getFormatValue(),  id + ",",dateOfOrder + ",",
                quantity + ",", customerID + ",", productID  + ",", price);
    }

    @Override
    public String toCSVFileString() {
        return id + ","+ dateOfOrder + ","+ quantity + "," + customerID + "," + productID  + "," + price+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getDateOfOrder().equals(order.getDateOfOrder()) &&
                getQuantity().equals(order.getQuantity()) &&
                customerID.equals(order.customerID) &&
                productID.equals(order.productID) &&
                price.equals(order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateOfOrder(), getQuantity(), customerID, productID, price);
    }

    private class DateofOrder {
        LocalDate localDate;
        DateTimeFormatter dateTimeFormatter;

        public DateofOrder(LocalDate localDate) {
            this.localDate = localDate;
            this.dateTimeFormatter = DateTimeFormatter.ofPattern("MM, dd, YYYY");
        }
    }
}