import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class PortableObject {
    private final String type;
    private int quantity;

    public PortableObject(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public void add(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public PortableObject get(int quantity) {
        return new PortableObject(type, quantity);
    }

    public PortableObject remove(int quantity) {
        if (this.quantity - quantity >= 0) {
            this.quantity = this.quantity - quantity;
            return new PortableObject(type, quantity);
        } else {
            throw new IllegalArgumentException("Not enough " + type + "!\n");
        }
    }

    public void print() {
    }

    public static void print(Dictionary<String, PortableObject> objects) {
        if (objects.isEmpty()) {
            System.out.print("There is nothing ");
        } else {
            Enumeration<String> keys = objects.keys();
            String key;
            System.out.print("There is ");

            if (objects.size() > 1) {
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    System.out.print(PortableObject.quantity(objects.get(key).quantity, key) + ", ");
                }
                System.out.print("and ");
            }
            key = keys.nextElement();
            System.out.print(PortableObject.quantity(objects.get(key).quantity, key) + " ");
        }
    }

    public static void add(Dictionary<String, PortableObject> objects, PortableObject object) {
        if (objects.get(object.type) == null) {
            objects.put(object.type, object);
        } else {
            objects.get(object.type).add(object.quantity);
        }
    }

    public static PortableObject remove(Dictionary<String, PortableObject> objects, String type, int quantity) {
        PortableObject have = objects.get(type);
        if (have == null) {
            throw new IllegalArgumentException("Object not found!\n");
        } else {
            if (have.quantity - quantity == 0) {
                objects.remove(type);
                return have;
            } else {
                return have.remove(quantity);
            }
        }
    }

    public static Dictionary<String, PortableObject> getPortableObjects() {
        Dictionary<String, PortableObject> objects = new Hashtable<>();

        long quantity = Math.round(Math.random() * 4);
        if (quantity > 0) {
            objects.put("key", new Key("key", (int) quantity));
        }

        if (Math.random() < .5) {
            objects.put("coin", new Currency("coin", 1 + (int) Math.round(Math.random() * 2)));
        }

        if (Math.random() < .1) {
            objects.put("gold", new Currency("gold"));
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
