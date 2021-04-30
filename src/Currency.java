/*
    A currency is a portable object that a player can "update" (transform into
    a more valuable one).
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
        Update the currencies
     */
    public Currency transform(String transformed) {
        switch (type) {
            case "coin":
                switch (transformed) {
                    case "gold":
                        add(-5);
                        return new Currency("gold");
                    case "diamond":
                        add(-15);
                        return new Currency("diamond");
                    default:
                        throw new IllegalArgumentException(("You can only transform coin in gold or diamond!\n"));
                }
            case "gold":
                if (transformed.equals("diamond")) {
                   add(-3);
                    return new Currency("diamond");
                } else {
                    throw new IllegalArgumentException(("You can only transform gold in diamond!\n"));
                }
            default:
                throw new IllegalArgumentException(("You can only transform coin or gold!\n"));
        }
    }

    /*
        Print the commends involving currencies.
     */
    public static void help() {
        System.out.println("Transform coin in gold     : Transform 5 coins in 1 gold");
        System.out.println("Transform gold in diamond   : Transform 3 coins in 1 diamond");
        System.out.println("Transform coin in diamond  : Transform 15 coins in 1 diamond\n");
    }
}
