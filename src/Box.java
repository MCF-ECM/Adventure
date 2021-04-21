import java.util.Dictionary;


public class Box extends Object {
    private boolean locked;
    private final Dictionary<String, Object> objects;

    public Box() {
        super("box", 1,false);
        locked = Math.random() < 0.6;
        objects = Object.getObjects(true);
    }

    public boolean unlock(Object key) {
        if (locked) {
            if (key.getType().equals("key")) {
                locked = false;
                System.out.println("Box unlocked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to lock a box!\n");
            }
        } else {
            throw new IllegalStateException("Box already unlocked.\n");
        }
    }

    public boolean lock(Object key) {
        if (locked) {
            throw new IllegalStateException("Box already locked.\n");
        } else {
            if (key.getType().equals("key")) {
                locked = true;
                System.out.println("Box locked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to unlock a box!\n");
            }
        }
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
            throw new IllegalStateException("You can only look at the content of a box when it is unlocked!\n");
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
