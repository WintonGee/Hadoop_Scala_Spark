public class Customer {

    // ID, name, birth date, address, city, ZIP, state, phoneNumber
    // Example record: 12, John Sam, 1989/11/11, 123 Main St, SLO, 93401, CA, 805 555 5555

    String ID, name, birthdate, address, city, ZIP, state, phoneNumber;

    public Customer(String ID, String name, String birthdate, String address, String city, String ZIP, String state, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.ZIP = ZIP;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ID + "," + name + "," + birthdate + "," + address + "," + city + "," + ZIP + "," + state + "," + phoneNumber;
    }

}
