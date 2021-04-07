import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class Player {
    private int orientation;
    private int room;
    private Dictionary<String, ArrayList<Object>> objects;

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

    public void take(Room room, String object) {
        Object taken = room.take(object);
        if (taken.portable) {
            ArrayList<Object> array = objects.get(object);
            if (array == null) {
                array = new ArrayList<>();
            }
            array.add(taken);
            objects.put(object, array);
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
                    System.out.print(objects.get(key).size() + " " + key + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(objects.get(key).size() + " " + key + ".\n");
        }
    }
}
