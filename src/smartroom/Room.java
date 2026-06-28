package smartroom;
public class Room {
    private String roomNumber;
    private int capacity;
    private boolean isBooked;
    private boolean hasProjector;

    public Room(String roomNumber,int capacity,boolean isBooked,boolean hasProjector) {
        this.roomNumber=roomNumber;
        this.capacity=capacity;
        this.isBooked=isBooked;
        this.hasProjector=hasProjector;
    }

    public void setRoom(String roomNumber,int capacity,boolean hasProjector) {
        this.roomNumber=roomNumber;
        this.capacity=capacity;
        this.hasProjector=hasProjector;
    }

    public void setBooked(boolean status) {
        this.isBooked=status;
    }

    public boolean getHasProjector() {return hasProjector;}
    public String getRoomID() {return roomNumber;}
    public boolean getIsBooked() {return isBooked;}
    public int getCapacity() {return capacity;}
}