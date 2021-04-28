/*
    A door is an lockable object that pass be pass through or not (wall id isDoor is False).
 */
public class Door extends LockableObject {
    private final boolean isDoor;
    private final int[] rooms;

    public Door(int room1, int room2, boolean isDoor) {
        super();
        this.isDoor = isDoor;
        this.rooms = new int[]{room1, room2};
    }

    public Door(int room1, int room2) {
        super();
        this.isDoor = Math.random() < .4;
        this.rooms = new int[]{room1, room2};
    }

    @Override
    public String getType() {
        return "door";
    }

    /*
        Return if is a door and not a wall.
     */
    public boolean isDoor() {
        return isDoor;
    }

    /*
        Update the door with a second room if it has not one yet.
     */
    public void uploadDoor(int room) {
        if (rooms[1] == -1) {
            rooms[1] = room;
        }
    }

    /*
        Return the id of the room at the other side of a door.
     */
    public int pass(int room) {
        if (rooms[0] == room) {
            return rooms[1];
        } else if (rooms[1] == room) {
            return rooms[0];
        } else {
            throw new IllegalArgumentException("You are not supposed to go through this door!\n");
        }
    }

    /*
        Print the commends involving doors.
     */
    public static void help() {
        System.out.println("Unlock door with key : Unlock door");
        System.out.println("Lock door with key   : Lock door\n");
    }
}
