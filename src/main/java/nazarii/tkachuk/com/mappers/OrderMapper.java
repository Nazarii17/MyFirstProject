package nazarii.tkachuk.com.mappers;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderMapper implements CSVMapper<Order> {

    @Override
    public Order mapFromCSV(String s) {
        String[] strings = s.split(",");
        String[] stringsDate = strings[1].split("-");

        return new Order(Integer.parseInt(strings[0].trim()),
                LocalDate.of(
                Integer.parseInt(stringsDate[0].trim()),
                Integer.parseInt(stringsDate[1].trim()),
                Integer.parseInt(stringsDate[2].trim())),
                Integer.parseInt(strings[2].trim()),
                new Customer(Integer.parseInt(strings[3].trim())),
                new Product(Integer.parseInt(strings[4].trim())),
                BigDecimal.valueOf(Double.parseDouble(strings[5].trim())).setScale(2)
        );
    }
}
