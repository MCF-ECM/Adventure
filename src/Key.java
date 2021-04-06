public class Key extends Object {
    public Key(int id) {
        super(id, true);
    }

    public void help() {
        System.out.println("Unlock <door> with <key> : Unlock door\nLock <door> with <key> : Lock door\n");
    }
}
