public class Reservation {

    private String customerName;
    private int roomNumber;
    private String roomType;
    private double amount;

    public Reservation(String customerName,
                       int roomNumber,
                       String roomType,
                       double amount) {

        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return customerName + "," +
               roomNumber + "," +
               roomType + "," +
               amount;
    }
}