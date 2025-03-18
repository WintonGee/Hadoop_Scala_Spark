public class Sales {

    // ID, date, time, storeID, customerID
    // Example record: 13, 2017/01/01, 13:23:11, 23, 56

    String ID, date, time, storeID, customerID;

    public Sales(String ID, String date, String time, String storeID, String customerID) {
        this.ID = ID;
        this.date = date;
        this.time = time;
        this.storeID = storeID;
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return ID + "," + date + "," + time + "," + storeID + "," + customerID;
    }

}

