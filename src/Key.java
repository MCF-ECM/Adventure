public class Key extends Object {
    /*
        id = -1 : Commun key to lock and unlock a commun door
        id >= 0 : Specific key to lock and unlock an specific door
    */
    private static int previousId = -1;

    public Key() {
        super(nextId(), "key", true);
    }

    private static int nextId() {
        if (Math.random() < 0.1) {
            return ++previousId;
        }
        else {
            return -1;
        }
    }

    public static void help() {
        System.out.println("Unlock <door> with <key> : Unlock door");
        System.out.println("Lock <door> with <key>   : Lock door\n");
    }
}
