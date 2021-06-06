/*
    A box is an lockable object that store portable objects.
 */
public class Box extends LockableObject {
    private final PortableObjectDictionary objects;

    public Box() {
        super(.5);
        objects = new PortableObjectDictionary(false);
    }

    @Override
    public String getType() {
        return "box";
    }

    /*
        Remove a portable object from a box.
     */
    public PortableObject remove(String type, int quantity) {
        if (isLocked()) {
            throw new IllegalArgumentException("You cannot take objects in an unlocked box!\n");
        } else {
            return objects.remove(type, quantity);
        }
    }

    /*
        Add an portable object in a box.
     */
    public void add(PortableObject object) {
        if (isLocked()) {
            throw new IllegalArgumentException("You cannot drop objects in an unlocked box!\n");
        } else {
            objects.add(object);
        }
    }

    /*
        Print the content of a box.
     */
    public void print() {
        if (isLocked()) {
            throw new IllegalStateException("You can only look the content of an unlocked box!\n");
        } else {
            objects.print(false);
            System.out.println("in the box.\n");
        }
    }

    /*
        Print the commends involving boxes.
     */
    public static void help() {
        System.out.println("Take <object> in box    : Pick up object object in a box");
        System.out.println("Drop <object> in box    : Drop object object in a box");
        System.out.println("Unlock box with key     : Unlock box");
        System.out.println("Lock box with key       : Lock box");
        System.out.println("Content box             : Show the content of a box\n");
    }
}
