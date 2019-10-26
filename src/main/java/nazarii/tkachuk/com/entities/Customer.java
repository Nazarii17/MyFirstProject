package nazarii.tkachuk.com.entities;

import nazarii.tkachuk.com.enums.CSVFormats;

import java.util.Objects;

public class Customer extends Person implements CSVSerializable {
    private String phoneNumber;

    public Customer(Integer id) {
        super(id);
    }

    public Customer(String name, String lastName, String phoneNumber) {
        super(null, name, lastName);
        this.phoneNumber = phoneNumber;
    }

    public Customer(Integer id, String name, String lastName, String phoneNumber) {
        super(id, name, lastName);
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
                "ID = " + id +
                ", First name = '" + name + '\'' +
                ", Last name = '" + lastName + '\'' +
                ", phone number = +380" + phoneNumber +
                '}';
    }

    @Override
    public String toCSVFormattedString() {
        return String.format(CSVFormats.CUSTOMER.getFormatValue(), id+",", name +",", lastName+",",  phoneNumber);
    }

    @Override
    public String toCSVString() {
        return id + "," + name + "," + lastName + "," +  phoneNumber+"\n";
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
