package smartroom;
import java.util.ArrayList;

public class BookingManager {
    // ArrayList implementation (Requirement 3) 
    private ArrayList<User> userList;
    private ArrayList<Room> roomList;

    public BookingManager() {
        userList = new ArrayList<>();
        roomList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
        // Removed the print statement so system initialization remains hidden from the user
    }

    // Method Overloading (Requirement 6): Signature 1 
    public void addRoom(Room room) {
        roomList.add(room);
        // Removed the print statement so system initialization remains hidden from the user
    }

    // Method Overloading (Requirement 6): Signature 2 (Used by Admin actively)
    public void addRoom(String roomNumber, int capacity, boolean hasProjector) {
        Room newRoom = new Room(roomNumber, capacity, false, hasProjector);
        roomList.add(newRoom);
        System.out.println("Success: Room " + roomNumber + " has been added to the system.");
    }

    // Exception Handling (Requirement 8) 
    public User login(String username, String password) throws BookingException {
        for (User u : userList) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        throw new BookingException("Invalid username or password. Please try again.");
    }

    public void bookRoom(RegularUser user, String roomNumber) throws BookingException {
        if (user.getAssignRoom() != null) {
            throw new BookingException("Booking failed: You already have an active room reservation.");
        }

        for (Room r : roomList) {
            if (r.getRoomID().equals(roomNumber)) {
                if (!r.getIsBooked()) {
                    r.setBooked(true);
                    user.assignRoom(r);
                    System.out.println("Success: Room " + roomNumber + " has been booked!");
                    return;
                } else {
                    throw new BookingException("Booking failed: Room " + roomNumber + " is currently occupied.");
                }
            }
        }
        throw new BookingException("Booking failed: The specified room does not exist.");
    }

    public void viewAllUser() {
        System.out.println("--- Registered Users ---");
        for (User u : userList) {
            System.out.println("Name: " + u.getName() + " | Role: " + (u.isAdmin() ? "System Admin" : "Regular User"));
        }
    }

    public void viewAvailableRooms() {
        System.out.println("--- Available Rooms ---");
        for (Room r : roomList) {
            if (!r.getIsBooked()) {
                String projectorStatus = r.getHasProjector() ? "Yes" : "No";
                System.out.println("Room: " + r.getRoomID() + " | Capacity: " + r.getCapacity() + " | Projector: " + projectorStatus);
            }
        }
    }

    public void viewAllRoom() {
        System.out.println("--- All Rooms Overview ---");
        for (Room r : roomList) {
            String bookingStatus = r.getIsBooked() ? "Occupied" : "Available";
            String projectorStatus = r.getHasProjector() ? "Yes" : "No";
            
            System.out.println("Room: " + r.getRoomID() + 
                               " | Capacity: " + r.getCapacity() + 
                               " | Projector: " + projectorStatus + 
                               " | Status: " + bookingStatus);
        }
    }
}