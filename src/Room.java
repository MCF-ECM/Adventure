import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class Room {
    private final int id;
    private static int nextId = 0;
    private final Door[] doors;
    private final Dictionary<String, ArrayList<Object>> objects;


    public Room(Door door, int orientation) {
        id = nextId++;
        doors = new Door[4];

        int back = (orientation + 2) % 4;
        for (int i = 0; i < 4; i++) {
            if (i == back) {
                doors[i] = door;
            }
            else {
                doors[i] = new Door(id, -2);
            }
        }

        Dictionary<String, ArrayList<Object>> objects = new Hashtable<>();
        ArrayList<Object> keys = new ArrayList<>();
        long quantity = Math.round(Math.random() * 5);
        if (quantity > 0) {
            for (int i = 0; i < quantity; i++) {
                keys.add(new Key());
            }
            objects.put("key", keys);
        }

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
        else if (doors[orientation].getId() == -2) {
            System.err.println("You cannot go through a wall.\n");
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
            default -> throw new IllegalArgumentException("Argument supposed to be between 0 and 4 but given: " + i);
        };
    }

    public void print(int orientation) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = orientation; i < 4; i++) {
            if (doors[i].id != -2) {
                positions.add((i - orientation) % 4);
            }
        }
        for (int i = 0; i < orientation; i++) {
            if (doors[i].id != -2) {
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
            System.out.print("There is ");

            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    System.out.print(Object.quantity(objects.get(key).size(), key) + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(Object.quantity(objects.get(key).size(), key) + " ");
        }
        System.out.println("on the floor.");
    }
}
