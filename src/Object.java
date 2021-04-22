import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Object {
    protected final String type;
    protected int quantity;
    protected boolean portable;

    public Object(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        this.portable = true;
    }

    public Object(String type, int quantity, boolean portable) {
        this.type = type;
        this.quantity = quantity;
        this.portable = portable;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void add(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public Object get(int quantity) {
        return new Object(type, quantity, portable);
    }

    public Object remove(int quantity) {
        if (this.quantity - quantity >= 0) {
            this.quantity = this.quantity - quantity;
            return new Object(type, quantity, portable);
        } else {
            throw new IllegalArgumentException("Not enough " + type + "!\n");
        }
    }

    public boolean isPortable() {
        return portable;
    }

    public void print() {
    }

    public static void print(Dictionary<String, Object> objects) {
        if (objects.isEmpty()) {
            System.out.print("There is nothing ");
        } else {
            Enumeration<String> keys = objects.keys();
            String key;
            System.out.print("There is ");

            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    System.out.print(Object.quantity(objects.get(key).quantity, key) + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(Object.quantity(objects.get(key).quantity, key) + " ");
        }
    }

    public static void objectsAdd(Dictionary<String, Object> objects, Object object) {
        if (object.portable) {
            if (objects.get(object.type) == null) {
                objects.put(object.type, object);
            } else {
                objects.get(object.type).add(object.quantity);
            }
        }
    }

    public static Object objectsRemove(Dictionary<String, Object> objects, String type, int quantity) {
        Object have = objects.get(type);
        if (have == null) {
            throw new IllegalArgumentException("Object not found!\n");
        } else {
            if (have.isPortable()) {
                if (have.quantity - quantity == 0) {
                    objects.remove(type);
                    return have;
                } else {
                    return have.remove(quantity);
                }
            } else {
                throw new IllegalArgumentException("You cannot take or drop " + type + "!\n");
            }
        }
    }

    public static Dictionary<String, Object> getObjects(boolean isBox) {
        Dictionary<String, Object> objects = new Hashtable<>();

        long quantity = Math.round(Math.random() * 4);
        if (quantity > 0) {
            objects.put("key", new Object("key", (int) quantity));
        }

        if (Math.random() < .5) {
            objects.put("coin", new Object("coin", 1 + (int) Math.round(Math.random() * 2)));
        }

        if (Math.random() < .1) {
            objects.put("gold", new Object("gold", 1));
        }

        if (!isBox && Math.random() < .5) {
            objects.put("box", new Box());
        }

        return objects;
    }

    public static String quantity(int quantity, String object) {
        if (quantity == 1) {
            return "1 " + object;
        } else {
            switch (object) {
                case "key":
                    return quantity + " keys";
                case "coin":
                    return quantity + " coins";
                case "gold":
                    return quantity + " gold";
                case "diamond":
                    return quantity + " diamonds";
                default:
                    throw new IllegalArgumentException("Not an object!");
            }
        }
    }
}
