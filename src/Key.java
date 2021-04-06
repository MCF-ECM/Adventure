public class Key extends Object {
    public Key(int id) {
        super(id, true);
    }

    public static void help() {
        System.out.println("Unlock <door> with <key> : Unlock door");
        System.out.println("Lock <door> with <key>   : Lock door\n");
    }
}
