public class LockableObject {
    private final String type;
    private boolean locked;

    public LockableObject(String type) {
        this.type = type;
        this.locked = Math.random() < 0.6;
    }

    public LockableObject(String type, double lockedProbability) {
        this.type = type;
        this.locked = Math.random() < lockedProbability;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean unlock(PortableObject key) {
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

    public boolean lock(PortableObject key) {
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
