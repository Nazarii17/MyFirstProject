package nazarii.tkachuk.com.entities;

import nazarii.tkachuk.com.enums.CSVFormats;

import java.util.Objects;

public class Customer extends Person implements CSVSerializable {
    private String phoneNumber;

    public Customer(Integer id) {
        super(id);
    }

    public Customer(String firstName, String lastName, Integer id, String phoneNumber) {
        super(firstName, String.format(lastName), id);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "First name = '" + name + '\'' +
                ", Last name = '" + lastName + '\'' +
                ", ID = " + id +
                ", phone number = +380" + phoneNumber +
                '}';
    }

    @Override
    public String toCSVWithFormatString() {
        return String.format(CSVFormats.CUSTOMER.getFormatValue(), name +",", lastName+",", id+",", phoneNumber);
    }

    @Override
    public String toCSVFileString() {
        return name + "," + lastName + "," + id + "," + phoneNumber+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPhoneNumber());
    }
}
