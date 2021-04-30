import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class Player {
    // The orientation of a player is an integer between 0 and 3.
    private int orientation;
    // The id of the room where the player is.
    private int room;
    // objects store the objects that the player carry.
    private final Dictionary<String, PortableObject> objects;

    public Player(int orientation, int room) {
        this.orientation = orientation;
        this.room = room;
        this.objects = new Hashtable<>();
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
        PortableObject.add(objects, coins.buy(supply));
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
    public void take(Room room, String object, int quantity) {
        PortableObject.add(objects, room.take(object, quantity));
    }

    /*
        The player take a portable object on the ground of a room.
     */
    public void take(Box box, String object, int quantity) {
        PortableObject.add(objects, box.remove(object, quantity));
    }

    /*
        The player drop a portable object on the ground of a room.
     */
    public void drop(Room room, String type, int quantity) {
        room.drop(PortableObject.remove(objects, type, quantity));
    }

    /*
        The player drop a portable object on the ground of a room.
     */
    public void drop(Box box, String type, int quantity) {
        box.add(PortableObject.remove(objects, type, quantity));
    }

    /*
        Print what the player is carrying.
     */
    public void print() {
        if (objects.isEmpty()) {
            System.out.println("You do not carry anything.\n");
        } else {
            System.out.print("You carry ");
            Enumeration<String> keys = objects.keys();
            String key;
            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    objects.get(key).print();
                    System.out.print(", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            objects.get(key).print();
            System.out.println(".\n");
        }
    }
}
