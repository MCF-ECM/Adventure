import java.util.Dictionary;


public class Box extends LockableObject {
    private final Dictionary<String, Object> objects;

    public Box() {
        super("box", 1);
        objects = Object.getObjects(true);
    }

    public Object take(String type, int quantity) {
        if (locked) {
            throw new IllegalArgumentException("You cannot take objects in an unlocked box!\n");
        } else {
            return Object.objectsRemove(objects, type, quantity);
        }
    }

    public void drop(Object object) {
        if (locked) {
            throw new IllegalArgumentException("You cannot drop objects in an unlocked box!\n");
        } else {
            Object.objectsAdd(objects, object);
        }
    }

    @Override
    public void print() {
        if (locked) {
            throw new IllegalStateException("You can only look the content of an unlocked box!\n");
        } else {
            Object.print(objects);
            System.out.println("in the box.\n");
        }
    }

    public static void help() {
        System.out.println("Unlock box with key : Unlock box");
        System.out.println("Lock box with key   : Lock box");
        System.out.println("Content box         : Show the content of a box\n");
    }
}
