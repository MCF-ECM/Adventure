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
