public class Door extends Object {
    /*
        id = -2 : Not a "real" door
        id = -1 : Commun door
        id >= 0 : Specific door
    */
    private static int previousId = -1;
    private boolean locked;
    private final int[] rooms;

    public Door(int id, int room1, int room2) {
        super(id, "door", false);
        rooms = new int[]{room1, room2};
        locked = Math.random() < 0.6;
    }

    public Door(int room1, int room2) {
        super(nextId(), "door",false);
        rooms = new int[]{room1, room2};
        locked = Math.random() < 0.6;
    }

    private static int nextId() {
        if (Math.random() < .5) {
            return -2;
        }
        else if (Math.random() < 0.1) {
            return ++previousId;
        }
        else {
            return -1;
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void uploadDoor(int room) {
        if (rooms[1] == -1) {
            rooms[1] = room;
        }
    }

    public boolean unlock(Object key) {
        if (!locked) {
            System.err.println("Door alredy unlocked.\n");
            return false;
        }
        else if (id == key.getId()) {
            locked = false;
            System.out.println("Door unlocked.\n");
            return true;
        }
        else {
            System.err.println("Wrong key.\n");
            return false;
        }
    }

    public boolean lock(Object key) {
        if (locked) {
            System.err.println("Door alredy locked.\n");
            return false;
        }
        else if (id == key.getId()) {
            locked = true;
            System.out.println("Door locked.\n");
            return true;
        } else {
            System.err.println("Wrong key.\n");
            return false;
        }
    }

    public int pass(int room) {
        if (rooms[0] == room) {
            return rooms[1];
        }
        else if (rooms[1] == room) {
            return rooms[0];
        }
        else {
            throw new IllegalStateException("You are not supposed to go through this door.");
        }
    }

    public static void help() {
        System.out.println("Unlock <door> with <key> : Unlock door");
        System.out.println("Lock <door> with <key>   : Lock door\n");
    }
}
