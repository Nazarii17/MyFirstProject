package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.Customer;
import nazarii.tkachuk.com.mappers.CustomerMapper;
import nazarii.tkachuk.com.providers.PropertiesProvider;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;
import nazarii.tkachuk.com.utils.ValidatorUtil;

import java.util.List;

public class CustomerService {

    private EntityIDService entityIDService;

    private String customerFilePath;
    private final String CUSTOMER_PROPERTY_NAME = "result.file.name.customers";

    public CustomerService() {
        customerFilePath = PropertiesProvider.getProperty(CUSTOMER_PROPERTY_NAME);
        entityIDService = new EntityIDService(customerFilePath);
    }

    public String getCustomerFilePath() {
        return customerFilePath;
    }

    private Boolean isPhoneNumberExist(String phoneNumber) {
        List<Customer> customerList = FileReaderUtil.readObjects(customerFilePath, new CustomerMapper());

        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isPhoneNumberExist(List<Customer> customerList, String phoneNumber) {

        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerByID(int id) {
        List<Customer> customerList = FileReaderUtil.readObjects(customerFilePath, new CustomerMapper());

        if (!entityIDService.isIDExist(customerList,id)){
            throw new RuntimeException("The customer with ID № \"" + id + "\" isn't exist!!!\"");
        }
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {

        List<Customer> customerList = FileReaderUtil.readObjects(customerFilePath, new CustomerMapper());

        if (!isPhoneNumberExist(customerList, phoneNumber)) {
            throw new RuntimeException("Phone number " + phoneNumber + " isn't exist!!!");
        }
        Customer correctOne = null;
        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                correctOne = customer;
            }
        }
        return correctOne;
    }

    public Customer createNewCustomer(String firstName, String lastName, String phoneNumber) {
        Customer customer = buildNewCustomer(firstName, lastName, phoneNumber);
        return save(customer);
    }

    private Customer buildNewCustomer(String firstName, String lastName, String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        return new Customer(firstName, lastName, phoneNumber);
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!ValidatorUtil.validatePhoneNumber(phoneNumber)) {
            throw new RuntimeException(phoneNumber + " invalid phone number!!!");
        }
        if (isPhoneNumberExist(phoneNumber)) {
            throw new RuntimeException("Phone number " + phoneNumber + " already exist!!!");
        }
    }

    private Customer save(Customer customer) {
        final int id = entityIDService.generateId();

        customer.setId(id);

        FileWriterUtil.writeToFile(customerFilePath, customer.toCSVString());

        return customer;
    }

    public void deleteCustomer(Customer customer) {
        deleteCustomerByID(customer.getId());
    }

    public void deleteCustomerByID(int id) {
        List<Customer> customerList = FileReaderUtil.readObjects(customerFilePath, new CustomerMapper());
        if (entityIDService.isIDExist(customerList, id)) {
            Customer customer = getCustomerByID(id);
            customerList.remove(customer);
        } else {
            throw new RuntimeException("Removal failed!!!\nThe customer with ID № \"" + id + "\" isn't exist!!!");
        }

        FileWriterUtil.overwriteTextToFile(customerFilePath, CSVFormatterUtil.toCSVString(customerList));
        System.out.println("The customer with ID № \"" + id + "\" was successfully deleted!!!");
    }

    public Customer editCustomerByID(int id, String firstName, String lastName, String phoneNumber) {
        List<Customer> customerList = FileReaderUtil.readObjects(customerFilePath, new CustomerMapper());

        Customer targetCustomer = getCustomerByID(id);

        if(!targetCustomer.getPhoneNumber().equals(phoneNumber)
        ){
            validatePhoneNumber(phoneNumber);
        }
        /*Customer targetCustomer = customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ERROR!\n Customer with id № " + id + " not found!!!"));*/
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                targetCustomer = customer;
            }
        }
        if (targetCustomer == null) {
            throw new RuntimeException("Edition failed!!!\n Customer with id № " + id + " not found!!!");
        }
        targetCustomer.setPhoneNumber(phoneNumber);
        targetCustomer.setName(firstName);
        targetCustomer.setLastName(lastName);

        FileWriterUtil.overwriteTextToFile(customerFilePath, CSVFormatterUtil.toCSVString(customerList));
        System.out.println("The customer with ID № \"" + id + "\" was successfully edited!!!");
        return targetCustomer;
    }
}


