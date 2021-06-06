import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


/*
    A portable object is an object that a player can take or drop such as:
        - keys;
        - currencies (coins, gold, diamond).
 */
public class PortableObjectDictionary {
    private final Dictionary<String, PortableObject> dictionary;

    public PortableObjectDictionary(boolean empty) {
        dictionary = new Hashtable<>();

        if (!empty) {
            long quantity = Math.round(Math.random() * 4);
            if (quantity > 0) {
                dictionary.put("key", new Key((int) quantity));
            }

            if (Math.random() < .5) {
                dictionary.put("coin", new Currency("coin", 1 + (int) Math.round(Math.random() * 2)));
            }

            if (Math.random() < .1) {
                dictionary.put("gold", new Currency("gold"));
            }
        }
    }

    public PortableObject get(String key) {
        return dictionary.get(key);
    }

    public PortableObject remove(String key) {
        return dictionary.remove(key);
    }

    /*
            Add a portable object to a Dictionary<String, PortableObject>.
         */
    public void add(PortableObject object) {
        if (dictionary.get(object.getType()) == null) {
            dictionary.put(object.getType(), object);
        } else {
            dictionary.get(object.getType()).add(object.getQuantity());
        }
    }

    /*
        Remove and return a portable object with the asked from a Dictionary<String, PortableObject>.
     */
    public PortableObject remove(String type, int quantity) {
        PortableObject have = dictionary.get(type);
        if (have == null) {
            throw new IllegalArgumentException("Object not found!\n");
        } else {
            if (have.getQuantity() - quantity == 0) {
                dictionary.remove(type);
                return have;
            } else {
                return have.remove(quantity);
            }
        }
    }

    /*
        Print the content of the dictionary.
     */
    public void print(boolean player) {
        if (player) {
            if (dictionary.isEmpty()) {
                System.out.println("You do not carry anything.\n");
            } else {
                System.out.print("You carry ");
                Enumeration<String> keys = dictionary.keys();
                String key;
                if (dictionary.size() > 1) {
                    for (int i = 0; i < dictionary.size() - 1; i++) {
                        key = keys.nextElement();
                        dictionary.get(key).print();
                        System.out.print(", ");
                    }
                    System.out.print("and ");
                }
                key = keys.nextElement();
                dictionary.get(key).print();
                System.out.println(".\n");
            }
        } else {
            if (dictionary.isEmpty()) {
                System.out.print("There is nothing ");
            } else {
                Enumeration<String> keys = dictionary.keys();
                String key;

                if (dictionary.size() > 1) {
                    System.out.print("There are ");
                    for (int i = 0; i < dictionary.size() - 1; i++) {
                        key = keys.nextElement();
                        dictionary.get(key).print();
                        System.out.print(", ");

                    }
                    System.out.print("and ");
                    key = keys.nextElement();
                    dictionary.get(key).print();
                } else {
                    key = keys.nextElement();
                    dictionary.get(key).print(true);
                }
                System.out.print(" ");
            }
        }
    }
}
