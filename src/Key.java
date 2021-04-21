public class Key extends Object {
    public Key(String type, int quantity) {
        super(type, quantity);
    }

    public static void help() {
        System.out.println("Unlock door | box with key : Unlock door");
        System.out.println("Lock door | box with key   : Lock door\n");
    }
}
