package mypackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Payment payment = new Payment();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Enter room category (SINGLE, DOUBLE, SUITE): ");
                    String categoryInput = scanner.nextLine().toUpperCase();
                    RoomCategory category;
                    try {
                        category = RoomCategory.valueOf(categoryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category.");
                        break;
                    }
                    List<Room> availableRooms = hotel.searchAvailableRooms(category);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available in this category.");
                    } else {
                        for (Room room : availableRooms) {
                            System.out.println("Room Number: " + room.getRoomNumber());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();  

                    Room roomToBook = null;
                    for (Room room : hotel.searchAvailableRooms(RoomCategory.SINGLE)) { 
                        if (room.getRoomNumber() == roomNumber) {
                            roomToBook = room;
                            break;
                        }
                    }
                    if (roomToBook == null || !roomToBook.isAvailable()) {
                        System.out.println("Room not available.");
                        break;
                    }

                    System.out.println("Enter check-in date (YYYY-MM-DD): ");
                    Date checkInDate = parseDate(scanner.nextLine());
                    System.out.println("Enter check-out date (YYYY-MM-DD): ");
                    Date checkOutDate = parseDate(scanner.nextLine());

                    if (checkInDate == null || checkOutDate == null) {
                        System.out.println("Invalid date format.");
                        break;
                    }

                    Reservation reservation = hotel.makeReservation(roomToBook, checkInDate, checkOutDate);
                    if (reservation != null) {
                        System.out.println("Reservation made successfully!");
                        if (payment.processPayment(100.0)) { 
                            System.out.println("Payment processed successfully.");
                        } else {
                            System.out.println("Payment failed.");
                        }
                    } else {
                        System.out.println("Failed to make reservation.");
                    }
                    break;

                case 3:
                    System.out.println("Viewing booking details is not implemented yet.");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}




















