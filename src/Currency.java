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

    public static void help() {
        System.out.println("Transform coin in gold     : Transform 5 coins in 1 gold");
        System.out.println("Transform gold in diamond   : Transform 3 coins in 1 diamond");
        System.out.println("Transform coin in diamond  : Transform 15 coins in 1 diamond\n");
    }
}
