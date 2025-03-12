package mypackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, RoomCategory.SINGLE));
        rooms.add(new Room(102, RoomCategory.DOUBLE));
        rooms.add(new Room(103, RoomCategory.SUITE));
    }

    public List<Room> searchAvailableRooms(RoomCategory category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory() == category && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, Date checkInDate, Date checkOutDate) {
        if (room.isAvailable()) {
            room.bookRoom();
            Reservation reservation = new Reservation(room, checkInDate, checkOutDate);
            reservations.add(reservation);
            return reservation;
        }
        return null;
    }

    public void cancelReservation(Reservation reservation) {
        reservation.getRoom().cancelBooking();
        reservations.remove(reservation);
    }
}
