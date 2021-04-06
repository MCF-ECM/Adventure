import java.util.ArrayList;

public class Room {
    private final int id;
    private final Door[] doors;
    private ArrayList<Integer> quantities;
    private ArrayList<String> objectsType;

    public int getId() {
        return id;
    }

    public Room(int id, Door[] doors, ArrayList<Integer> quantities, ArrayList<String> objectsType) {
        this.id = id;
        this.doors = doors;
        this.quantities = quantities;
        this.objectsType = objectsType;
    }

    public Object take(String type) {
        int index = objectsType.indexOf(type);

        if (index == -1) {
            System.err.println("Object not on the floor.\n");
            return new Object(-2, false);
        }

        if (quantities.get(index) > 2) {
            quantities.set(index, quantities.get(index) - 1);
        }
        else {
            quantities.remove(index);
            objectsType.remove(index);
        }

        return switch (type) {
            case "key" -> new Key(-1);
            default -> new Object(-2, false);
        };
    }

    private String positionConversion(int i) {
        return switch (i) {
            case 0 -> "in front of you";
            case 1 -> "on your right";
            case 2 -> "in your back";
            case 3 -> "on your left";
            default -> "ERROR";
        };
    }

    public void print(int orientation) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = orientation; i < 4; i++) {
            if (doors[i].id != -1) {
                positions.add((i - orientation) % 4);
            }
        }
        for (int i = 0; i < orientation; i++) {
            if (doors[i].id != -1) {
                positions.add((4 + i - orientation) % 4);
            }
        }
        System.out.print("There is ");
        if (positions.size() > 1) {
            for (int i = 0; i < positions.size() - 1; i++) {
                System.out.print("one door " + positionConversion(positions.get(i)) + ", ");
            }
            System.out.print("and ");
        }
        System.out.println("one door " + positionConversion(positions.get(positions.size() - 1)) + ".");


        if (quantities.size() == 0) {
            System.out.print("There is nothing ");
        }
        else {
            if (quantities.size() > 1) {
                System.out.print("There are ");
                for (int i = 0; i < quantities.size() - 1; i++) {
                    System.out.print(quantities.get(i) + " " + objectsType.get(i) + ", ");
                }
                System.out.print("and ");
            }
            else {
                if (quantities.get(0) == 1) {
                    System.out.print("There is ");
                }
                else {
                    System.out.print("There are ");
                }
            }
            System.out.print(quantities.get(quantities.size() - 1) + " " + objectsType.get(quantities.size() - 1) + " ");
        }
        System.out.println("on the floor.");
    }
}
