public class Currency extends PortableObject {
    public Currency(String type) {
        super(type, 1);
    }

    public Currency(String type, int quantity) {
        super(type, quantity);
    }

    public static void help() {
        System.out.println("Transform coin in gold     : Transform 5 coins in 1 gold");
        System.out.println("Transform gold in diamond   : Transform 3 coins in 1 diamond");
        System.out.println("Transform coin in diamond  : Transform 15 coins in 1 diamond\n");
    }
}
