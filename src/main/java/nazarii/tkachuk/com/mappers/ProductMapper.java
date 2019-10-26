package nazarii.tkachuk.com.mappers;

import nazarii.tkachuk.com.entities.Product;

import java.math.BigDecimal;

public class ProductMapper implements CSVMapper<Product> {

    @Override
    public Product mapFromCSV(String s) {
        String[] strings = s.split(",");

        return new Product(
                Integer.parseInt(strings[0]) ,
                strings[1],
                BigDecimal.valueOf(Double.parseDouble(strings[2])),
                strings[3]);
    }
}
