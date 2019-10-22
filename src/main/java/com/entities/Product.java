package com.entities;

import com.enums.CSVFormats;
import java.math.BigDecimal;

public class Product extends Nameble implements CSVSerializable {
    private String name;
    private Integer id;
    private BigDecimal price;
    private String info;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name, Integer id, BigDecimal price, String info) {
        this.name = name;
        this.id = id;
        this.price = price.setScale(2);
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Name = '" + name + '\'' +
                ", ID of the product = " + id +
                ", Price = " + price + "$" +
                ", \nInformation: \n'" + info + " '" +
                '}';
    }

    @Override
    public String toCSVWithFormatString() {
        return String.format(CSVFormats.PRODUCT.getFormatValue(), name + ",", id + ",", price + ",", info);
    }

    @Override
    public String toCSVFileString() {
        return name + "," + id + "," + price + "," + info+"\n";
    }
}
