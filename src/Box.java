import java.util.ArrayList;
import java.util.Dictionary;

public class Box extends Object {
    private boolean locked;
    private final Dictionary<String, ArrayList<Object>> objects;

    public Box() {
        super("box", false);
        locked = Math.random() < 0.6;
        objects = Object.getObjects(true);
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean unlock(Object key) {
        if (locked) {
            if (key.getType().equals("key")) {
                locked = false;
                System.out.println("Box unlocked.\n");
                return true;
            }
            else {
                throw new IllegalArgumentException("You need a key to lock a box!");
            }
        }
        else {
            throw new IllegalStateException("Box alredy unlocked.\n");
        }
    }

    @Override
    public boolean lock(Object key) {
        if (locked) {
            throw new IllegalStateException("Box alredy locked.\n");
        }
        else {
            if (key.getType().equals("key")) {
                locked = true;
                System.out.println("Box locked.\n");
                return true;
            }
            else {
                throw new IllegalArgumentException("You need a key to unlock a box!");
            }
        }
    }

    @Override
    public Object take(String type) {
        return Object.objectsRemove(objects, type);
    }

    @Override
    public void drop(Object object) {
        Object.objectsAdd(objects, object);
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
        System.out.println("Unlock <box> with <key> : Unlock box");
        System.out.println("Lock <box> with <key>   : Lock box");
        System.out.println("Content <box>           : Box content\n");
    }
}
