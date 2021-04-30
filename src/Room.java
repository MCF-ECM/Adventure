import java.util.ArrayList;
import java.util.Dictionary;


/*
    A room is a place with doors and portable objects (and pote ntially a box) on the ground.
 */
public class Room {
    // The id is used to identify and get room when they are needed.
    private final int id;
    private static int nextId = 0;
    // doors store the doors and walls (which are door with isDoor = False).
    private final Door[] doors;
    // objects store the objects on the ground that a player can take or drop in.
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

    /*
        Return the door before the player given his/her orientation.
     */
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

    /*
        Return the id of the room at the other side of a door given it position
        (orientation of the player for the door before him/her) when the door is
        unlocked.
     */
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

    /*
        Return the asked portable object in the asked quantity that is/are remove
        from the ground of a room.
     */
    public PortableObject take(String type, int quantity) {
        return PortableObject.remove(objects, type, quantity);
    }

    /*
        Drop an portable object on the ground of a room.
     */
    public void drop(PortableObject object) {
        PortableObject.add(objects, object);
    }

    /*
        Converter the orientation/position of a door (int) into an understanding
        string.
     */
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
                throw new IllegalArgumentException("Argument supposed to be between 0 and 3 but given: " + i);
        }
    }

    /*
        Print the description of the room composed of:
            - the localisation of the doors;
            - the list of portable objects on the ground;
            - the presence of a box (when there is one).
     */
    public void print(int orientation) {
        // Localize the door.
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

        // Print the localization of the doors.
        if (positions.size() > 1) {
            System.out.print("There are ");
            for (int i = 0; i < positions.size() - 1; i++) {
                System.out.print("one door " + positionConversion(positions.get(i)) + ", ");
            }
            System.out.print("and ");
        } else {
            System.out.print("There is ");
        }
        System.out.println("one door " + positionConversion(positions.get(positions.size() - 1)) + ".");

        // Print the list of portable obejcts on the ground.
        PortableObject.print(objects);
        System.out.println("on the floor.");

        // Print the presence of a box when there is one.
        if (haveBox) {
            System.out.println("There is a box in this room.");
        }
    }
}
