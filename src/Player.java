public class Player {
    // The orientation of a player is an integer between 0 and 3.
    private int orientation;
    // The id of the room where the player is.
    private int room;
    // objects store the objects that the player carry.
    private final PortableObjectDictionary objects;

    public Player(int orientation, int room) {
        this.orientation = orientation;
        this.room = room;
        this.objects = new PortableObjectDictionary(true);
    }

    public int getOrientation() {
        return orientation;
    }

    public int getRoom() {
        return room;
    }

    /*
        Change the orientation of a player given the direction to turn
        (String), "left" or "right".
     */
    public void turn(String direction) {
        switch (direction) {
            case "left":
                orientation = (orientation + 3) % 4;
                break;
            case "right":
                orientation = (orientation + 1) % 4;
                break;
            default:
                throw new IllegalArgumentException("Direction not allowed!\n");
        }
    }

    /*
        Update and return the id of the room where a player is, after he/she
        has walked in the room at the other side of the door he/she faced (when
        it is possible).
     */
    public int move(Room room) {
        this.room = room.move(orientation);
        return this.room;
    }

    /*
        The player buy portable object with currencies.
     */
    public void buy(String supply) {
        Currency coins = (Currency) objects.get("coin");
        objects.add(coins.buy(supply));
        if (coins.getQuantity() == 0) {
            objects.remove("coin");
        }
    }

    /*
        The player unlock a lockable object.
     */
    public void unlock(LockableObject lockableObject) {
        PortableObject keys = objects.get("key");
        if (keys != null) {
            Key key = (Key) keys.copy(1);
            if (lockableObject.unlock(key)) {
                keys.remove(1);
                if (keys.getQuantity() == 0) {
                    objects.remove("key");
                }
            }
        } else {
            throw new IllegalStateException("You do not carry any key\n");
        }
    }

    /*
        The player lock a lockable object.
     */
    public void lock(LockableObject lockableObject) {
        PortableObject keys = objects.get("key");
        if (keys != null) {
            Key key = (Key) keys.copy(1);
            if (lockableObject.lock(key)) {
                keys.remove(1);
                if (keys.getQuantity() == 0) {
                    objects.remove("key");
                }
            }
        } else {
            throw new IllegalStateException("You do not carry any key!\n");
        }
    }

    /*
        The player take a portable object on the ground of a room.
     */
    public void take(Room room, String object) {
        objects.add(room.take(object));
    }

    /*
        The player take a portable object on the ground of a room.
     */
    public void take(Box box, String object) {
        objects.add(box.remove(object));
    }

    /*
        The player drop a portable object on the ground of a room.
     */
    public void drop(Room room, String type) {
        room.drop(objects.remove(type));
    }

    /*
        The player drop a portable object on the ground of a room.
     */
    public void drop(Box box, String type) {
        box.add(objects.remove(type));
    }

    /*
        Print what the player is carrying.
     */
    public void print() {
        objects.print(true);
    }
}
