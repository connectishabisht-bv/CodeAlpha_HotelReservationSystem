import javax.swing.*;
import java.awt.*;

public class HotelGUI extends JFrame {

    private BookingManager manager;

    private JTextArea displayArea;

    public HotelGUI() {

        manager = new BookingManager();

        setTitle(
                "Hotel Reservation System");

        setSize(700,500);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        displayArea = new JTextArea();

        add(new JScrollPane(
                displayArea),
                BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton viewRooms =
                new JButton("View Rooms");

        JButton bookRoom =
                new JButton("Book Room");

        JButton cancelRoom =
                new JButton("Cancel Booking");

        JButton viewBookings =
                new JButton("View Bookings");

        panel.add(viewRooms);
        panel.add(bookRoom);
        panel.add(cancelRoom);
        panel.add(viewBookings);

        add(panel, BorderLayout.NORTH);

        viewRooms.addActionListener(e ->
                showRooms());

        bookRoom.addActionListener(e ->
                bookRoom());

        cancelRoom.addActionListener(e ->
                cancelRoom());

        viewBookings.addActionListener(e ->
                showBookings());

        setVisible(true);
    }

    private void showRooms() {

        displayArea.setText("");

        for(Room room :
                manager.getRooms()) {

            displayArea.append(
                    room.getRoomNumber()
                    + " | "
                    + room.getType()
                    + " | ₹"
                    + room.getPrice()
                    + " | "
                    + (room.isAvailable()
                    ? "Available"
                    : "Booked")
                    + "\n");
        }
    }

    private void bookRoom() {

        String name =
                JOptionPane.showInputDialog(
                        "Customer Name");

        int roomNo =
                Integer.parseInt(
                        JOptionPane
                        .showInputDialog(
                        "Room Number"));

        if(manager.bookRoom(
                name, roomNo)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Successful");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Failed");
        }
    }

    private void cancelRoom() {

        int roomNo =
                Integer.parseInt(
                        JOptionPane
                        .showInputDialog(
                        "Room Number"));

        if(manager.cancelBooking(
                roomNo)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Cancelled");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No Reservation Found");
        }
    }

    private void showBookings() {

        displayArea.setText("");

        for(Reservation r :
                manager.getReservations()) {

            displayArea.append(
                    "Customer: "
                    + r.getCustomerName()
                    + "\nRoom: "
                    + r.getRoomNumber()
                    + "\nType: "
                    + r.getRoomType()
                    + "\nAmount: ₹"
                    + r.getAmount()
                    + "\n\n");
        }
    }
}