package nazarii.tkachuk.com.mappers;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.utils.StringUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderMapper implements CSVMapper<Order> {

    @Override
    public Order mapFromCSV(String s) {
//        String[] strings = s.split(",");
        String[] strings = StringUtil.stringTrim(s.split(","));
        String[] stringsDate = strings[1].split("-");
        
        LocalDate date = LocalDate.of(
                Integer.parseInt(stringsDate[0]),
                Integer.parseInt(stringsDate[1]),
                Integer.parseInt(stringsDate[2]));

        return new Order(
                Integer.parseInt(strings[0]),
                date,
                Integer.parseInt(strings[2]),
                new Customer(Integer.parseInt(strings[3])),
                new Product(Integer.parseInt(strings[4])),
                new BigDecimal(strings[5])
        );
    }
}
