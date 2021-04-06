public class Door extends Object {
    private boolean locked;

    public Door(int id, boolean locked) {
        super(id, false);
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock(Key key) {
        if (locked) {
            if (id == key.id) {
                locked = false;
                System.out.println("Door unlocked.\n");
            } else {
                System.err.println("Wrong key.\n");
            }
        }
        else {
            System.err.println("Door alredy unlocked.\n");
        }
    }

    public void lock(Key key) {
        if (locked) {
            System.err.println("Door alredy locked.\n");
        }
        else {
            if (id == key.id) {
                locked = true;
                System.out.println("Door locked.\n");
            } else {
                System.err.println("Wrong key.\n");
            }
        }
    }

    public static void help() {
        System.out.println("Unlock <door> with <key> : Unlock door");
        System.out.println("Lock <door> with <key>   : Lock door\n");
    }
}
