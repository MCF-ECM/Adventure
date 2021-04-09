public class Door extends Object {
    private final boolean isDoor;
    private boolean locked;
    private final int[] rooms;

    public Door(int room1, int room2, boolean isDoor) {
        super("door", false);
        this.isDoor = isDoor;
        this.locked = Math.random() < .6;
        this.rooms = new int[]{room1, room2};
    }

    public Door(int room1, int room2) {
        super("door",false);
        this.isDoor = Math.random() < .4;
        this.locked = Math.random() < .6;
        this.rooms = new int[]{room1, room2};
    }

    @Override
    public boolean isDoor() {
        return isDoor;
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
        if (isDoor) {
            if (locked) {
                if (key.getType().equals("key")) {
                    locked = false;
                    System.out.println("Door unlocked.\n");
                    return true;
                } else {
                    throw new IllegalArgumentException("You need a key to unlock a door!");
                }
            } else {
                System.err.println("Door alredy unlocked.\n");
                return false;
            }
        }
        else {
            throw new IllegalArgumentException("You cannot unlock a wall!");
        }
    }

    public boolean lock(Object key) {
        if (isDoor) {
            if (locked) {
                System.err.println("Door alredy locked.\n");
                return false;
            }
            else {
                if (key.getType().equals("key")) {
                    locked = true;
                    System.out.println("Door locked.\n");
                    return true;
                } else {
                    throw new IllegalArgumentException("You need a key to lock a door!");
                }
            }
        }
        else {
            throw new IllegalArgumentException("You cannot lock a wall!");
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
