package smartroom;
public class RegularUser extends User {
    public RegularUser(String name,String username,String password) {
        super(name,username,password);
    }

    @Override
    public void showMenu() {
        System.out.println("--- User Dashboard ---");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. View My Booking");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Logout");
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
}
