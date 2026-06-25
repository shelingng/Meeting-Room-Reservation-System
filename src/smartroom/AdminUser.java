package smartroom;

public class AdminUser extends User {
    public AdminUser(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("--- Admin Dashboard ---");
        System.out.println("1. Add Room");
        System.out.println("2. View All Users");
        System.out.println("3. View All Rooms");
        System.out.println("4. Logout");
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}