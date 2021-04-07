import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;


public class Room {
    private final int id;
    private final Door[] doors;
    private Dictionary<String, ArrayList<Object>> objects;

    public int getId() {
        return id;
    }

    public Room(int id, Door[] doors, Dictionary<String, ArrayList<Object>> objects) {
        this.id = id;
        this.doors = doors;
        this.objects = objects;
    }

    public Door getDoor(int orientation) {
        return doors[orientation];
    }

    public int move(int orientation) {
        if (doors[orientation].isLocked()) {
            System.err.println("Door locked.\n");
            return id;
        }
        else {
            return doors[orientation].pass(id);
        }
    }

    public Object take(String type) {
        ArrayList<Object> array = objects.get(type);
        if (array == null) {
            System.err.println("Object not on the floor.\n");
            return new Object(-2, false);
        }
        else if (array.size() > 1) {
            Object object = array.remove(array.size() - 1);
            objects.put(type, array);
            return object;
        }
        else {
            Object object = array.remove(array.size() - 1);
            objects.remove(type);
            return object;
        }
    }

    private String positionConversion(int i) {
        return switch (i) {
            case 0 -> "in front of you";
            case 1 -> "on your right";
            case 2 -> "in your back";
            case 3 -> "on your left";
            default -> "ERROR";
        };
    }

    public void print(int orientation) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = orientation; i < 4; i++) {
            if (doors[i].id != -1) {
                positions.add((i - orientation) % 4);
            }
        }
        for (int i = 0; i < orientation; i++) {
            if (doors[i].id != -1) {
                positions.add((4 + i - orientation) % 4);
            }
        }
        System.out.print("There is ");
        if (positions.size() > 1) {
            for (int i = 0; i < positions.size() - 1; i++) {
                System.out.print("one door " + positionConversion(positions.get(i)) + ", ");
            }
            System.out.print("and ");
        }
        System.out.println("one door " + positionConversion(positions.get(positions.size() - 1)) + ".");


        if (objects.isEmpty()) {
            System.out.print("There is nothing ");
        }
        else {
            Enumeration<String> keys = objects.keys();
            String key;
            System.out.print("You carry ");
            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    System.out.print(objects.get(key).size() + " " + key + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(objects.get(key).size() + " " + key + " ");
        }
        System.out.println("on the floor.");
    }
}
