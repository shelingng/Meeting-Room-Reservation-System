package smartroom;
import java.util.Scanner;

public class BookingTest {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Scanner scanner = new Scanner(System.in);

        manager.addUser(new AdminUser("Admin", "admin", "admin123"));
        manager.addUser(new RegularUser("Ng She Ling", "sheling", "student123"));
        manager.addRoom(new Room("A101", 30, false, true));
        manager.addRoom(new Room("B202", 10, false, false));

        System.out.println("==========================================");
        System.out.println("   Welcome to Smart Room Booking System   ");
        System.out.println("==========================================");

        while (true) {
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("Type 'exit' to quit");
            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            if (option.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            if (option.equals("2")) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                manager.registerUser(name, username, password);

            } else if (option.equals("1")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                try {
                    User loggedInUser = manager.login(username, password);
                    System.out.println("\nLogin successful! Welcome, " + loggedInUser.getName());

                    boolean sessionActive = true;
                    while (sessionActive) {
                        System.out.println();
                        loggedInUser.showMenu();
                        System.out.print("Select an option: ");
                        int choice = Integer.parseInt(scanner.nextLine());

                        if (loggedInUser.isAdmin()) {
                            switch (choice) {
                                case 1:
                                    System.out.print("Enter Room Number: ");
                                    String roomNum = scanner.nextLine();
                                    System.out.print("Enter Capacity: ");
                                    int cap = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Projector (true/false): ");
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
                                    sessionActive = false; 
                                    break;
                            }
                        } else {
                            RegularUser regUser = (RegularUser) loggedInUser;
                            switch (choice) {
                                case 1: 
                                    manager.viewAvailableRooms(); 
                                    break;
                                case 2:
                                    System.out.print("Enter Room to book: ");
                                    String roomToBook = scanner.nextLine();
                                    try { 
                                        manager.bookRoom(regUser, roomToBook); 
                                    } catch (BookingException e) {
                                         System.out.println(e.getMessage()); 
                                    }
                                    break;
                                case 3: 
                                    regUser.displayMyBooking(); 
                                    break;
                                case 4:
                                    regUser.cancelBooking(); 
                                    break;
                                case 5: 
                                    sessionActive = false; 
                                    break;
                            }
                        }
                    }
                } 
                catch (BookingException e) { 
                    System.out.println("Error: " + e.getMessage()); 
                }
            }
        }
        scanner.close();
    }
}