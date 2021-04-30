/*
    A currency is a portable object that a player can create with less
    valuable ones.
 */
public class Currency extends PortableObject {
    private final String type;

    public Currency(String type) {
        super();
        this.type = type;
    }

    public Currency(String type, int quantity) {
        super(quantity);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    /*
        Buy portable object with coins
     */
    public PortableObject buy(String transformed) {
        if (type.equals("coin")) {
            switch (transformed) {
                case "key":
                    add(-2);
                    return new Key();
                case "gold":
                    add(-5);
                    return new Currency("gold");
                case "diamond":
                    add(-15);
                    return new Currency("diamond");
                default:
                    throw new IllegalArgumentException(("You can only buy keys, gold or diamond!\n"));
            }
        } else {
            throw new IllegalArgumentException(("You can only use coin to buy something!\n"));
        }
    }

    /*
        Print the commends involving currencies.
     */
    public static void help() {
        System.out.println("Buy (key | gold | diamond) with coin : Buy 1 key, gold or diamond with 2, 5 or 15 coins\n");
    }
}
