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
                System.out.println("Wrong key.\n");
            }
        }
        else {
            System.out.println("Door alredy unlocked.\n");
        }
    }

    public void help() {
        System.out.println("Unlock <door> with <key> : Unlock door\nLock <door> with <key> : Lock door\n");
    }
}
