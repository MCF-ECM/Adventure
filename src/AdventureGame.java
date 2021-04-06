import java.util.Scanner;
import java.util.ArrayList;


public class AdventureGame {
    public static void main(String args[]){
        System.out.println("Welcome to the game!\n");
        Scanner in = new Scanner(System.in);
        String input;
        String[] inputs;

        Player player = new Player(0);

        ArrayList<Room> roms = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();

        doors.add(new Door(0, true));
        doors.add(new Door(1, true));
        Door no = new Door(-1, true);
        Door[] array ={doors.get(1), doors.get(0), no, no};

        ArrayList<Integer> quantites = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        quantites.add(1);
        quantites.add(4);
        types.add("key");
        types.add("orbe");

        Room room = new Room(2, array, quantites, types);


        while (true) {
            room.print(player.getOrientation());
            player.print();

            input = in.nextLine();
            inputs = input.trim().split("\\s+");

            switch (inputs[0]) {
                case "Turn":
                    if (inputs.length == 2) {
                        player.turn(inputs[1]);
                    }
                    else {
                        notUnderstanded();notUnderstanded();
                    }
                    break;
                case "Take":
                    if (inputs.length == 2) {
                        player.take(room, inputs[1]);
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Help":
                    if (inputs.length == 1) {
                        help();
                    }
                    else {
                        switch (inputs[1]) {
                            case "door":
                                Door.help();
                                break;
                            case "key":
                                Key.help();
                                break;
                            default:
                                notUnderstanded();
                        }
                    }
                    break;
                default:
                    notUnderstanded();
            }
        }
    }

    static void help() {
        System.out.println("Turn ( right | left ) : Turn in the specified direction");
        System.out.println("Move                  : Move forward");
        System.out.println("Take <object>         : Pick up object <object>");
        System.out.println("Drop <object>         : Drop object <object>");
        System.out.println("Help [ <object> ]     : This command\n");
    }

    static void notUnderstanded() {
        System.err.println("Your instruction is not understanded\n");
    }
}
