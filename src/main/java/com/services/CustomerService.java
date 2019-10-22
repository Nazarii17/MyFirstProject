package com.services;

import com.entities.Customer;
import com.mappers.CustomerMapper;
import com.utils.CSVFormatterUtil;
import com.utils.FileReaderUtil;
import com.utils.FileWriterUtil;
import com.utils.ValidatorUtil;
import java.util.List;

public class CustomerService {

    public static Boolean isPhoneNumberExist(String filePath, String phoneNumber) {
        List<Customer> customerList = FileReaderUtil.readObjects(filePath, new CustomerMapper());

        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public static Customer getCustomerByID(String filePath, int id) {
        List<Customer> customerList = FileReaderUtil.readObjects(filePath, new CustomerMapper());
        Customer correctOne = null;
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                correctOne = customer;
            }
        }
        return correctOne;
    }

    public static Customer getCustomerByPhoneNumber(String filePath, String phoneNumber) {
        List<Customer> customerList = FileReaderUtil.readObjects(filePath, new CustomerMapper());
        Customer correctOne = null;
        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                correctOne = customer;
            }
        }
        return correctOne;
    }

    public static Customer createNewCustomer(String filePath, String firstName, String lastName, String phoneNumber) {

        if (!EntityIDService.isFileExist(filePath)){
            FileWriterUtil.createFileIfNotExists(filePath);
        }

        if ((!ValidatorUtil.validatePhoneNumber(phoneNumber) | isPhoneNumberExist(filePath, phoneNumber))) {
            throw new RuntimeException("Далбайоб, введи нормальний телефон, гавно ти собаче, щоб тебе качка копнула, сука!!!");
        }

        EntityIDService.createFileWithMaxID(filePath, new CustomerMapper());

        return new Customer(firstName, lastName, EntityIDService.generateIDFromFile(EntityIDService.getIDFilePath(filePath)), phoneNumber);
    }

    public static void deleteCustomerByID(String filePath, int id) {
        List<Customer> customerList = FileReaderUtil.readObjects(filePath, new CustomerMapper());
        Customer customer = getCustomerByID(filePath, id);
        customerList.remove(customer);

        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(customerList));
    }

    public static void editCustomerByID(String filePath, int id, String firstName, String lastName, String phoneNumber) {
        List<Customer> customerList = FileReaderUtil.readObjects(filePath, new CustomerMapper());

        if (!ValidatorUtil.validatePhoneNumber(phoneNumber)) {
            throw new RuntimeException(phoneNumber + " invalid phone number!!!");
        }
        if (isPhoneNumberExist(filePath, phoneNumber)) {
            throw new RuntimeException("Phone number " + phoneNumber + " already exist!!!");
        }
        if (!EntityIDService.isIDExist(filePath, id, new CustomerMapper())) {
            throw new RuntimeException("ERROR!\n Customer with id № " + id + " not found!!!");
        }
        Integer index = null;
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                index = customerList.indexOf(customer);
            }
            if (isPhoneNumberExist(filePath, phoneNumber)) {
                throw new RuntimeException("Phone number " + phoneNumber + " already exist!!!");
            }
        }
        customerList.set(index, new Customer(firstName, lastName, id, phoneNumber));

        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(customerList));
    }
}


