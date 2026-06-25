package smartroom;
import java.util.Scanner;

public class BookingTest {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize the system silently
        manager.addUser(new AdminUser("Admin", "admin", "admin123"));
        manager.addUser(new RegularUser("Ng She Ling", "sheling", "student123"));
        manager.addRoom(new Room("A101", 30, false, true));
        manager.addRoom(new Room("B202", 10, false, false));

        System.out.println("==========================================");
        System.out.println("   Welcome to Smart Room Booking System   ");
        System.out.println("==========================================");

        // Main Application Loop
        while (true) {
            System.out.println("\n--- Login System ---");
            System.out.print("Enter username (or type 'exit' to quit): ");
            String username = scanner.nextLine();
            
            if (username.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            try {
                // Attempt to log the user in
                User loggedInUser = manager.login(username, password);
                System.out.println("\nLogin successful! Welcome, " + loggedInUser.getName());

                // Inner Loop: Keeps the user logged in until they choose to logout
                boolean sessionActive = true;
                while (sessionActive) {
                    System.out.println();
                    loggedInUser.showMenu(); // Polymorphism: Shows Admin or Regular menu
                    System.out.print("Select an option: ");
                    
                    int choice;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        continue;
                    }

                    System.out.println(); // Blank line for cleaner output

                    // Handle Admin Options
                    if (loggedInUser.isAdmin()) {
                        switch (choice) {
                            case 1:
                                System.out.print("Enter New Room Number: ");
                                String roomNum = scanner.nextLine();
                                System.out.print("Enter Room Capacity: ");
                                int cap = Integer.parseInt(scanner.nextLine());
                                System.out.print("Does it have a projector? (true/false): ");
                                boolean proj = Boolean.parseBoolean(scanner.nextLine());
                                manager.addRoom(roomNum, cap, proj);
                                break;
                            case 2:
                                manager.viewAllUser();
                                break;
                            case 3:
                                manager.viewAllRoom();
                                break;
                            case 4:
                                System.out.println("Logging out...");
                                sessionActive = false; // Breaks the inner loop to return to login
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } 
                    // Handle Regular User Options
                    else {
                        RegularUser regUser = (RegularUser) loggedInUser;
                        switch (choice) {
                            case 1:
                                manager.viewAvailableRooms();
                                break;
                            case 2:
                                // NEW LOGIC: Loop until successful booking or user cancels
                                while (true) {
                                    System.out.print("Enter the Room Number you want to book (or type 'cancel' to go back): ");
                                    String roomToBook = scanner.nextLine();
                                    
                                    if (roomToBook.equalsIgnoreCase("cancel")) {
                                        System.out.println("Returning to main menu...");
                                        break; // Exits the booking loop
                                    }

                                    try {
                                        manager.bookRoom(regUser, roomToBook);
                                        break; // Successfully booked, exit the loop
                                    } catch (BookingException e) {
                                        System.out.println(e.getMessage() + " Please try again.\n");
                                        
                                        // If the error is because they already have a booking, stop prompting
                                        if (e.getMessage().contains("already have an active room reservation")) {
                                            break;
                                        }
                                    }
                                }
                                break;
                            case 3:
                                regUser.displayMyBooking();
                                break;
                            case 4:
                                regUser.cancelBooking();
                                break;
                            case 5:
                                System.out.println("Logging out...");
                                sessionActive = false; // Breaks the inner loop to return to login
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                }

            } catch (BookingException e) {
                // Catches wrong passwords and keeps the login system running
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        
        scanner.close(); 
    }
}
