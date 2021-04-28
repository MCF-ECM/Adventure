import java.util.Dictionary;


public class Box extends LockableObject {
    private final Dictionary<String, PortableObject> objects;

    public Box() {
        super(.5);
        objects = PortableObject.getPortableObjects();
    }

    @Override
    public String getType() {
        return "box";
    }

    public PortableObject take(String type, int quantity) {
        if (isLocked()) {
            throw new IllegalArgumentException("You cannot take objects in an unlocked box!\n");
        } else {
            return PortableObject.remove(objects, type, quantity);
        }
    }

    public void drop(PortableObject object) {
        if (isLocked()) {
            throw new IllegalArgumentException("You cannot drop objects in an unlocked box!\n");
        } else {
            PortableObject.add(objects, object);
        }
    }

    public void print() {
        if (isLocked()) {
            throw new IllegalStateException("You can only look the content of an unlocked box!\n");
        } else {
            PortableObject.print(objects);
            System.out.println("in the box.\n");
        }
    }

    public static void help() {
        System.out.println("Take <object> in box    : Pick up object object in a box");
        System.out.println("Drop <object> in box    : Drop object object in a box");
        System.out.println("Unlock box with key     : Unlock box");
        System.out.println("Lock box with key       : Lock box");
        System.out.println("Content box             : Show the content of a box\n");
    }
}
