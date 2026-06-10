import java.io.*;
import java.util.*;

public class BookingManager {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations =
            new ArrayList<>();

    public BookingManager() {
        loadRooms();
        loadReservations();
    }

    private void loadRooms() {

        rooms.add(new Room(101,
                "Standard", 2000, true));

        rooms.add(new Room(102,
                "Standard", 2000, true));

        rooms.add(new Room(201,
                "Deluxe", 3500, true));

        rooms.add(new Room(202,
                "Deluxe", 3500, true));

        rooms.add(new Room(301,
                "Suite", 6000, true));
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public boolean bookRoom(String customer,
                            int roomNo) {

        for(Room room : rooms) {

            if(room.getRoomNumber() == roomNo
                    && room.isAvailable()) {

                if(Payment.processPayment(
                        room.getPrice())) {

                    room.setAvailable(false);

                    Reservation reservation =
                            new Reservation(
                                    customer,
                                    roomNo,
                                    room.getType(),
                                    room.getPrice());

                    reservations.add(reservation);

                    saveReservations();

                    return true;
                }
            }
        }

        return false;
    }

    public boolean cancelBooking(int roomNo) {

        Iterator<Reservation> iterator =
                reservations.iterator();

        while(iterator.hasNext()) {

            Reservation r = iterator.next();

            if(r.getRoomNumber() == roomNo) {

                iterator.remove();

                for(Room room : rooms) {
                    if(room.getRoomNumber()
                            == roomNo) {
                        room.setAvailable(true);
                    }
                }

                saveReservations();
                return true;
            }
        }

        return false;
    }

    public ArrayList<Reservation>
    getReservations() {
        return reservations;
    }

    private void saveReservations() {

        try(PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    "reservations.txt"))) {

            for(Reservation r :
                    reservations) {

                writer.println(r);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReservations() {

        File file =
                new File("reservations.txt");

        if(!file.exists()) return;

        try(BufferedReader br =
                    new BufferedReader(
                            new FileReader(file))) {

            String line;

            while((line =
                    br.readLine()) != null) {

                String[] data =
                        line.split(",");

                reservations.add(
                        new Reservation(
                                data[0],
                                Integer.parseInt(
                                        data[1]),
                                data[2],
                                Double.parseDouble(
                                        data[3])));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}