public class Object {
    protected int id;
    protected boolean portable;

    public Object(int id, boolean portable) {
        this.id = id;
        this.portable = portable;
    }

    public static String quantity(int quantity, String object) {
        if (quantity == 1) {
            return "1 " + object;
        }
        else {
            return switch (object) {
                case "key" -> quantity + " keys";
                default -> throw new IllegalArgumentException("Not an object");
            };
        }
    }
}
