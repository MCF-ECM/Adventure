import java.util.ArrayList;
import java.util.Dictionary;


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
                doors[i] = new Door(id, -1);
            }
        }

        this.objects = Object.getObjects(false);
    }

    public Door getDoor(int orientation) {
        return doors[orientation];
    }

    public Object getBox() {
        return objects.get("box").get(0);
    }

    public int move(int orientation) {
        if (doors[orientation].isDoor()) {
            if(doors[orientation].isLocked()) {
                System.err.println("Door locked.\n");
                return id;
            }
            else {
                return doors[orientation].pass(id);
            }
        }
        else {
            System.err.println("You cannot go through a wall.\n");
            return id;
        }
    }

    public Object take(String type) {
        return Object.objectsRemove(objects, type);
    }

    public void drop(Object object) {
        Object.objectsAdd(objects, object);
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
            if (doors[i].isDoor()) {
                positions.add((i - orientation) % 4);
            }
        }
        for (int i = 0; i < orientation; i++) {
            if (doors[i].isDoor()) {
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


        Object.print(objects);
        System.out.println("on the floor.");
    }
}
