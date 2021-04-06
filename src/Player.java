import java.util.ArrayList;

public class Player {
    private int orientation;
    private ArrayList<Integer> quantities;
    private ArrayList<String> objectsType;
    private ArrayList<Object> objects;

    public Player(int orientation) {
        this.orientation = orientation;
        this.quantities = new ArrayList<>();
        this.objectsType = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public int getOrientation() {
        return orientation;
    }

    public void turn(String direction) {
        switch (direction) {
            case "left" -> orientation = (orientation + 3) % 4;
            case "right" -> orientation = (orientation + 1) % 4;
            default -> System.out.println("Direction not understanded.\n");
        }
    }

    public void take(Room room, String object) {
        Object taken = room.take(object);
        if (taken.portable) {
            int index = objectsType.indexOf(object);
            if (index == -1) {
                quantities.add(1);
                objectsType.add(object);
            }
            else {
                quantities.set(index, quantities.get(index) + 1);
            }
            objects.add(taken);
        }
    }

    public void print() {
        if (quantities.size() == 0) {
            System.out.print("You do not carry anything.\n");
        }
        else {
            System.out.print("You carry ");
            if (quantities.size() > 1) {
                for (int i = 0; i < quantities.size() - 1; i++) {
                    if (quantities.get(i) != 0) {
                        System.out.print(quantities.get(i) + " " + objectsType.get(i) + ", ");
                    }
                }
                System.out.print("and ");
            }
            System.out.println(quantities.get(quantities.size() - 1) + " " + objectsType.get(quantities.size() - 1) + ".\n");
        }
    }
}
