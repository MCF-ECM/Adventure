import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


/*
    A portable object is an object that a player can take or drop such as:
        - keys;
        - currencies (coins, gold, diamond).
 */
public class PortableObject {
    private int quantity;

    public PortableObject() {
        quantity = 1;
    }

    public PortableObject(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return null;
    }

    /*
    Change the quantity of an portable object by adding an integer
    to it.
     */
    public void add(int quantity) {
        this.quantity += quantity;
    }

    /*
        Return a copy of the portable object with the asked quantity.
     */
    public PortableObject copy(int quantity) {
        switch (getType()) {
            case "key":
                return new Key(quantity);
            case "coin": case "gold": case "diamond":
                return new Currency(getType(), quantity);
            default:
                throw new IllegalArgumentException("Your object type is not allowed!\n");
        }
    }

    /*
        Return a part of the portable object with the asked quantity
        changing the original portable object.
     */
    public PortableObject remove(int quantity) {
        if (this.quantity - quantity >= 0) {
            this.quantity -= quantity;
            return this.copy(quantity);
        } else {
            throw new IllegalArgumentException("Not enough " + getType() + "!\n");
        }
    }

    /*
        Print the content of a Dictionary<String, PortableObject>.
     */
    public static void print(Dictionary<String, PortableObject> objects) {
        if (objects.isEmpty()) {
            System.out.print("There is nothing ");
        } else {
            Enumeration<String> keys = objects.keys();
            String key;

            if (objects.size() > 1) {
                System.out.print("There are ");
                for (int i = 0; i < objects.size() - 1; i++) {
                    key = keys.nextElement();
                    objects.get(key).print();
                    System.out.print(", ");

                }
                System.out.print("and ");
                key = keys.nextElement();
                objects.get(key).print();
            } else {
                key = keys.nextElement();
                objects.get(key).print(true);
            }
            System.out.print(" ");
        }
    }

    /*
        Add a portable object to a Dictionary<String, PortableObject>.
     */
    public static void add(Dictionary<String, PortableObject> objects, PortableObject object) {
        if (objects.get(object.getType()) == null) {
            objects.put(object.getType(), object);
        } else {
            objects.get(object.getType()).add(object.quantity);
        }
    }

    /*
        Remove and return a portable object with the asked from a Dictionary<String, PortableObject>.
     */
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

    /*
        Return a Dictionary<String, PortableObject> with portable objects in it.
     */
        public static Dictionary<String, PortableObject> getPortableObjects() {
        Dictionary<String, PortableObject> objects = new Hashtable<>();

        long quantity = Math.round(Math.random() * 4);
        if (quantity > 0) {
            objects.put("key", new Key((int) quantity));
        }

        if (Math.random() < .5) {
            objects.put("coin", new Currency("coin", 1 + (int) Math.round(Math.random() * 2)));
        }

        if (Math.random() < .1) {
            objects.put("gold", new Currency("gold"));
        }

        return objects;
    }

    /*
        Print "There is" or "There are" in function of the quantity, the quantity and
        the type of portable object with the correct form (singular or plural).
     */
    public void print(boolean starting) {
        if (starting) {
            if (quantity == 1) {
                System.out.print("There is ");
            } else {
                System.out.print("There are ");
            }
        }
        this.print();
    }

    /*
        Print the quantity and the type of portable object with the correct form
        (singular or plural).
     */
    public void print() {
        System.out.print(quantity + " ");
        if (quantity == 1) {
            System.out.print(getType());
        } else {
            switch (getType()) {
                case "key":
                    System.out.print("keys");
                    break;
                case "coin":
                    System.out.print("coins");
                    break;
                case "gold":
                    System.out.print("gold");
                    break;
                case "diamond":
                    System.out.print("diamonds");
                    break;
                default:
                    throw new IllegalArgumentException("Not an object!");
            }
        }
    }
}
