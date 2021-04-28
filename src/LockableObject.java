public class LockableObject extends Object {
    protected boolean locked;

    public LockableObject(String type) {
        super(type, 1,false);
        locked = Math.random() < 0.6;
    }

    public LockableObject(String type, double lockedProbability) {
        super(type, 1,false);
        locked = Math.random() < lockedProbability;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean unlock(Object key) {
        if (locked) {
            if (key.getType().equals("key")) {
                locked = false;
                System.out.println(upper(type) + " unlocked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to lock a " + type + "!\n");
            }
        } else {
            throw new IllegalStateException(upper(type) + " already unlocked!\n");
        }
    }

    public boolean lock(Object key) {
        if (locked) {
            throw new IllegalStateException(upper(type) + " already locked!\n");
        } else {
            if (key.getType().equals("key")) {
                locked = true;
                System.out.println(upper(type) + " locked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to unlock a " + type + "!\n");
            }
        }
    }

    private String upper(String type) {
        switch (type) {
            case "box":
                return "Box";
            case "door":
                return "Door";
            default:
                throw new IllegalArgumentException("Not an lockable object!");
        }
    }
}
