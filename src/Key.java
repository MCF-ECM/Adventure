/*
    A key is a portable object that can be used to unlock or lock a
    lockable object.
 */
public class Key extends PortableObject {
    public Key() {
        super(1);
    }

    public Key(int quantity) {
        super(quantity);
    }

    @Override
    public String getType() {
        return "key";
    }

    /*
        Print the commends involving keys.
     */
    public static void help() {
        System.out.println("Unlock door | box with key : Unlock door");
        System.out.println("Lock door | box with key   : Lock door\n");
    }
}
