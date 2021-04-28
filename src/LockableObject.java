public class LockableObject {
    private boolean locked;

    public LockableObject() {
        this.locked = Math.random() < 0.6;
    }

    public LockableObject(double lockedProbability) {
        this.locked = Math.random() < lockedProbability;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getType() {
        return null;
    }

    public boolean unlock(Key key) {
        if (locked) {
            if (key.getQuantity() > 0) {
                locked = false;
                System.out.println(upper(getType()) + " unlocked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to lock a " + getType() + "!\n");
            }
        } else {
            throw new IllegalStateException(upper(getType()) + " already unlocked!\n");
        }
    }

    public boolean lock(Key key) {
        if (locked) {
            throw new IllegalStateException(upper(getType()) + " already locked!\n");
        } else {
            if (key.getQuantity() > 0) {
                locked = true;
                System.out.println(upper(getType()) + " locked.\n");
                return true;
            } else {
                throw new IllegalArgumentException("You need a key to unlock a " + getType() + "!\n");
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
