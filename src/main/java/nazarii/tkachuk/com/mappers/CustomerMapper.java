package nazarii.tkachuk.com.mappers;

import nazarii.tkachuk.com.entities.Customer;

public class CustomerMapper implements CSVMapper<Customer> {

    @Override
    public Customer mapFromCSV(String s) {
        String[] strings = s.split(",");
        return new Customer(Integer.parseInt(strings[0].trim()), strings[1].trim(), strings[2].trim(), strings[3].trim());
    }
}
