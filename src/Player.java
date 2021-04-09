import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class Player {
    private int orientation;
    private int room;
    private final Dictionary<String, ArrayList<Object>> objects;

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

    public void turn(String direction) {
        switch (direction) {
            case "left" -> orientation = (orientation + 3) % 4;
            case "right" -> orientation = (orientation + 1) % 4;
            default -> System.err.println("Direction not understanded.\n");
        }
    }

    public int move(Room room) {
        this.room = room.move(orientation);
        return this.room;
    }

    public void unlock(Object object) {
        if (object.isDoor() || object.getType().equals("box")) {
            ArrayList<Object> keys = objects.get("key");
            if (keys != null) {
                Object key = keys.get(keys.size() - 1);
                if (key.getType().equals("key")) {
                    if (object.unlock(key)) {
                        keys.remove(keys.size() - 1);
                        if (keys.size() == 0) {
                            objects.remove("key");
                        }
                    }
                } else {
                    System.err.println("You can only use a key to unlock a box!\n");
                }
            } else {
                System.err.println("You do not carry any key\n");
            }
        }
    }

    public void lock(Object object) {
        if (object.isDoor() || object.getType().equals("box")) {
            ArrayList<Object> keys = objects.get("key");
            if (keys != null) {
                Object key = keys.get(keys.size() - 1);
                if (key.getType().equals("key")) {
                    if (object.lock(key)) {
                        keys.remove(keys.size() - 1);
                        if (keys.size() == 0) {
                            objects.remove("key");
                        }
                    }
                } else {
                    System.err.println("You can only use a key to lock a box!\n");
                }
            } else {
                System.err.println("You do not carry any key\n");
            }
        }
    }

    public void take(Room room, String object) {
        Object.objectsAdd(objects, room.take(object));
    }

    public void take(Object box, String object) {
        if (box.type.equals("box") && !box.isLocked()) {
            Object.objectsAdd(objects, box.take(object));
        }
        else {
            System.err.println("You can only take objects on the floor or in a box!");
        }
    }

    public void drop(Room room, String type) {
        room.drop(Object.objectsRemove(objects, type));
    }

    public void drop(Object box, String type) {
        if (box.type.equals("box") && !box.isLocked()) {
            box.drop(Object.objectsRemove(objects, type));
        }
        else {
            System.err.println("You can only drop objects on the floor or in a box!");
        }
    }

    public void print() {
        if (objects.isEmpty()) {
            System.out.print("You do not carry anything.\n");
        }
        else {
            System.out.print("You carry ");
            Enumeration<String> keys = objects.keys();
            String key;
            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    System.out.print(Object.quantity(objects.get(key).size(), key) + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(Object.quantity(objects.get(key).size(), key) + ".\n");
        }
    }
}
