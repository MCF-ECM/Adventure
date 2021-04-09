import java.util.Scanner;
import java.util.ArrayList;


public class AdventureGame {
    public static void main(String[] args){
        System.out.println("Welcome to the game!\n");
        Scanner in = new Scanner(System.in);
        String input;
        String[] inputs;

        Player player = new Player(0, 0);

        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(new Door(0, -1, true), (player.getOrientation() + 2) % 4));
        Room room = rooms.get(0);

        boolean play = true;

        while (play) {
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
                        notUnderstanded();
                    }
                    break;
                case "Move":
                    if (inputs.length == 1) {
                        if (player.move(room) == -1) {
                            room.getDoor(player.getOrientation()).uploadDoor(rooms.size());
                            rooms.add(new Room(room.getDoor(player.getOrientation()), player.getOrientation()));
                            room = rooms.get(rooms.size() - 1);
                        }
                        else {
                            room = rooms.get(player.getRoom());
                        }
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Take":
                    if (inputs.length == 2) {
                        player.take(room, inputs[1]);
                    }
                    else if (inputs.length == 4 && inputs[2].equals("in")&& inputs[3].equals("box")) {
                        player.take(room.getBox(), inputs[1]);
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Drop":
                    if (inputs.length == 2) {
                        player.drop(room, inputs[1]);
                    }
                    else if (inputs.length == 4 && inputs[2].equals("in")&& inputs[3].equals("box")) {
                        player.drop(room.getBox(), inputs[1]);
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
                            case "door" -> Door.help();
                            case "key" -> Object.helpKey();
                            case "box" -> Box.help();
                            default -> notUnderstanded();
                        }
                    }
                    break;
                case "Quit":
                    if (inputs.length == 1) {
                        play = false;
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Unlock":
                    if (inputs.length == 4 && inputs[2].equals("with") && inputs[3].equals("key")) {
                        if (inputs[1].equals("door")) {
                            player.unlock(room);
                        }
                        else if (inputs[1].equals("box")) {
                            player.unlock(room.getBox());
                        }
                        else {
                            notUnderstanded();
                        }
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Lock":
                    if (inputs.length == 4 && inputs[2].equals("with") && inputs[3].equals("key")) {
                        if (inputs[1].equals("door")) {
                            player.lock(room);
                        }
                        else if (inputs[1].equals("box")) {
                            player.lock(room.getBox());
                        }
                        else {
                            notUnderstanded();
                        }
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                case "Content":
                    if (inputs.length == 2 && inputs[1].equals("box")) {
                        if (room.getBox() != null) {
                            room.getBox().print();
                        }
                    }
                    else {
                        notUnderstanded();
                    }
                    break;
                default:
                    notUnderstanded();
            }
        }

        System.out.println("You quit the game!");
    }

    static void help() {
        System.out.println("Turn ( right | left ) : Turn in the specified direction");
        System.out.println("Move                  : Move forward");
        System.out.println("Take <object>         : Pick up object <object>");
        System.out.println("Drop <object>         : Drop object <object>");
        System.out.println("Help [ <object> ]     : This command");
        System.out.println("Quit                  : Quit the game\n");
    }

    static void notUnderstanded() {
        System.err.println("Your instruction is not understanded\n");
    }
}
