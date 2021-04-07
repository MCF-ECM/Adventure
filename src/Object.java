import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Object {
    protected int id;
    protected final String type;
    protected boolean portable;

    public Object(int id, String type, boolean portable) {
        this.id = id;
        this.type = type;
        this.portable = portable;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isPortable() {
        return portable;
    }

    public boolean isLocked() {
        return false;
    }

    public boolean unlock(Object key) {
        return false;
    }

    public boolean lock(Object key) {
        return false;
    }

    public Object take(String type) {
        return null;
    }

    public void drop(Object object) {
    }

    public void print() {
    }

    public static void print(Dictionary<String, ArrayList<Object>> objects) {
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
    }

    public static void objectsAdd(Dictionary<String, ArrayList<Object>> objects, Object object) {
        if (object != null && object.portable) {
            ArrayList<Object> array = objects.get(object.getType());
            if (array == null) {
                array = new ArrayList<>();
            }
            array.add(object);
            objects.put(object.getType(), array);
        }
    }

    public static Object objectsRemove(Dictionary<String, ArrayList<Object>> objects, String type) {
        ArrayList<Object> array = objects.get(type);
        if (array == null) {
            System.err.println("Object not found.\n");
            return null;
        }
        else {
            Object object = array.remove(array.size() - 1);
            if (object.isPortable()) {
                if (array.size() == 0) {
                    objects.remove(type);
                }
                else {
                    objects.put(type, array);
                }
                return object;
            }
            else {
                return null;
            }
        }
    }

    public static Dictionary<String, ArrayList<Object>> getObjects(boolean isBox) {
        Dictionary<String, ArrayList<Object>> objects = new Hashtable<>();

        long quantity = Math.round(Math.random() * 4);
        if (quantity > 0) {
            ArrayList<Object> keys = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                keys.add(new Key());
            }
            objects.put("key", keys);
        }

        if (!isBox && Math.random() < .4) {
            ArrayList<Object> boxes = new ArrayList<>();
            boxes.add(new Box());
            objects.put("box", boxes);
        }

        return objects;
    }

    public static String quantity(int quantity, String object) {
        if (quantity == 1) {
            return "1 " + object;
        }
        else {
            return switch (object) {
                case "key" -> quantity + " keys";
                default -> throw new IllegalArgumentException("Not an object");
            };
        }
    }
}
