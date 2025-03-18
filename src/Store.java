public class Store {

    // ID, storeName, address, city, ZIP, state, phoneNumber
    // Example record: 12, Best Buy, 123 Main St, SLO, 93401, CA, 805 555 5555

    public String ID, storeName, address, city, ZIP, state, phoneNumber;

    public Store(String ID, String storeName, String address, String city, String ZIP, String state, String phoneNumber) {
        this.ID = ID;
        this.storeName = storeName;
        this.address = address;
        this.city = city;
        this.ZIP = ZIP;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ID + "," + storeName + "," + address + "," + city + "," + ZIP + "," + state + "," + phoneNumber;
    }

}
