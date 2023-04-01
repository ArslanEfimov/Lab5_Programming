package Organization;

public class Address {
    private String street; //Длина строки не должна быть больше 130, Поле может быть null
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public Address(String street){
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
