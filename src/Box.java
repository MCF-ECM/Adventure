import java.util.ArrayList;
import java.util.Dictionary;

public class Box extends Object {
    private boolean locked;
    private final Dictionary<String, ArrayList<Object>> objects;

    public Box() {
        super(-1, "box", false);
        locked = Math.random() < 0.6;
        objects = Object.getObjects(true);
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean unlock(Object key) {
        if (!locked) {
            System.err.println("Box alredy unlocked.\n");
            return false;
        }
        else if (key.getId() == -1) {
            locked = false;
            System.out.println("Box unlocked.\n");
            return true;
        }
        else {
            System.err.println("Wrong key.\n");
            return false;
        }
    }

    @Override
    public boolean lock(Object key) {
        if (locked) {
            System.err.println("Box alredy locked.\n");
            return false;
        }
        else if (key.getId() == -1) {
            locked = true;
            System.out.println("Box locked.\n");
            return true;
        } else {
            System.err.println("Wrong key.\n");
            return false;
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
            System.err.println("You can only look at the content of a box when it is unlocked!\n");
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
