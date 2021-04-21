public class Door extends LockableObject {
    private final boolean isDoor;
    private final int[] rooms;

    public Door(int room1, int room2, boolean isDoor) {
        super("door");
        this.isDoor = isDoor;
        this.rooms = new int[]{room1, room2};
    }

    public Door(int room1, int room2) {
        super("door");
        this.isDoor = Math.random() < .4;
        this.rooms = new int[]{room1, room2};
    }

    public boolean isDoor() {
        return isDoor;
    }

    public void uploadDoor(int room) {
        if (rooms[1] == -1) {
            rooms[1] = room;
        }
    }

    public int pass(int room) {
        if (rooms[0] == room) {
            return rooms[1];
        } else if (rooms[1] == room) {
            return rooms[0];
        } else {
            throw new IllegalArgumentException("You are not supposed to go through this door.\n");
        }
    }

    public static void help() {
        System.out.println("Unlock door with key : Unlock door");
        System.out.println("Lock door with key   : Lock door\n");
    }
}
