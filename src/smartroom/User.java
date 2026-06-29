package smartroom;

public abstract class User implements MenuDisplayable {
    private String name;
    private String username;
    private String password;
    private Room assignRoom; 

    public User(String name,String username,String password) {
        this.name=name;
        this.username=username;
        this.password=password;
        this.assignRoom=null;
    }

    public String getName() {return name;}
    public String getUsername() {return username; }
    public String getPassword() {return password;}
    public Room getAssignRoom() {return assignRoom;}

    public void assignRoom(Room room) {
        this.assignRoom=room;
    }

    public void cancelBooking() {
        if (this.assignRoom!=null) {
            this.assignRoom.setBooked(false);
            this.assignRoom=null;
            System.out.println("Booking cancelled successfully.");
        }
        else {
            System.out.println("No active booking to cancel.");
        }
    }

    public void displayMyBooking() {
        if (assignRoom!=null) {
            System.out.println("You have booked Room: " + assignRoom.getRoomID());
        }
        else {
            System.out.println("You have no current bookings.");
        }
    }

    public abstract boolean isAdmin();
}