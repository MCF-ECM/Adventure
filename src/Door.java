public class Door extends Object {
    /*
        id = -2 : Not a "real" door
        id = -1 : Commun door
        id >= 0 : Specific door
    */
    private static int previousId = -1;
    private boolean locked;
    private int[] rooms;

    public Door(int id, int room1, int room2) {
        super(id, false);
        rooms = new int[]{room1, room2};
        locked = Math.random() < 0.6;
    }

    public Door(int room1, int room2) {
        super(nextId(), false);
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

    public int getId() {
        return id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void uploadDoor(int room) {
        if (rooms[1] == -2) {
            rooms[1] = room;
        }
    }

    public void unlock(Key key) {
        if (locked) {
            if (id == key.id) {
                locked = false;
                System.out.println("Door unlocked.\n");
            } else {
                System.err.println("Wrong key.\n");
            }
        }
        else {
            System.err.println("Door alredy unlocked.\n");
        }
    }

    public void lock(Key key) {
        if (locked) {
            System.err.println("Door alredy locked.\n");
        }
        else {
            if (id == key.id) {
                locked = true;
                System.out.println("Door locked.\n");
            } else {
                System.err.println("Wrong key.\n");
            }
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
