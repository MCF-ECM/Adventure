import java.util.ArrayList;
import java.util.Dictionary;


public class Room {
    private final int id;
    private static int nextId = 0;
    private final Door[] doors;
    private final Dictionary<String, PortableObject> objects;
    private final boolean haveBox;
    private final Box box;


    public Room(Door door, int orientation) {
        id = nextId++;
        doors = new Door[4];

        int back = (orientation + 2) % 4;
        for (int i = 0; i < 4; i++) {
            if (i == back) {
                doors[i] = door;
            } else {
                doors[i] = new Door(id, -1);
            }
        }

        objects = PortableObject.getPortableObjects();
        haveBox = Math.random() < .5;
        if (haveBox) {
            box = new Box();
        } else {
            box = null;
        }
    }

    public Door getDoor(int orientation) {
        return doors[orientation];
    }

    public Box getBox() {
        if (haveBox) {
            return box;
        } else {
            throw new IllegalStateException("There is no box in this room!");
        }
    }

    public int move(int orientation) {
        if (doors[orientation].isDoor()) {
            if(doors[orientation].isLocked()) {
                throw new IllegalStateException("Door locked.\n");
            } else {
                return doors[orientation].pass(id);
            }
        } else {
            throw new IllegalArgumentException("You cannot go through a wall!\n");
        }
    }

    public PortableObject take(String type, int quantity) {
        return PortableObject.remove(objects, type, quantity);
    }

    public void drop(PortableObject object) {
        PortableObject.add(objects, object);
    }

    private String positionConversion(int i) {
        switch (i) {
            case 0:
                return "in front of you";
            case 1:
                return "on your right";
            case 2:
                return"in your back";
            case 3:
                return "on your left";
            default:
                throw new IllegalArgumentException("Argument supposed to be between 0 and 4 but given: " + i);
        }
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


        PortableObject.print(objects);
        System.out.println("on the floor.");
    }
}
