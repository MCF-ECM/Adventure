import java.util.Scanner;
import java.util.ArrayList;


public class AdventureGame {
    public static void main(String[] args){
        // Print the welcome message.
        System.out.println("Welcome to the game!\n");

        // Initialize a scanner for the inputs.
        Scanner in = new Scanner(System.in);
        String input;
        String[] inputs;

        // Initialise a player
        Player player = new Player(0, 0);

        // Initialise a room and a ArrayList<Room> to store them
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(new Door(0, -1, true), (player.getOrientation() + 2) % 4));
        Room room = rooms.get(0);

        boolean play = true;

        while (play) {
            // Print the state of the room where the player is and his/her belongs.
            room.print(player.getOrientation());
            player.print();

            // Get the inputs.
            input = in.nextLine();
            inputs = input.trim().split("\\s+");

            try {
                switch (inputs[0]) {
                    case "Turn":
                        if (inputs.length == 2) {
                            player.turn(inputs[1]);
                        } else {
                            throw new IllegalArgumentException(("You need ONE direction (left or right) to turn!\n"));
                        }
                        break;
                    case "Move":
                        if (inputs.length == 1) {
                            if (player.move(room) == -1) {
                                room.getDoor(player.getOrientation()).uploadDoor(rooms.size());
                                rooms.add(new Room(room.getDoor(player.getOrientation()), player.getOrientation()));
                                room = rooms.get(rooms.size() - 1);
                            } else {
                                room = rooms.get(player.getRoom());
                            }
                        } else {
                            throw new IllegalArgumentException(("Your instruction is not understood!\n"));
                        }
                        break;
                    case "Take":
                        if (inputs.length == 2) {
                            player.take(room, inputs[1], 1);
                        } else if (inputs.length == 4 && inputs[2].equals("in") && inputs[3].equals("box")) {
                            player.take(room.getBox(), inputs[1], 1);
                        } else {
                            throw new IllegalArgumentException(("You can only take an object on the ground or in a box!\n"));
                        }
                        break;
                    case "Drop":
                        if (inputs.length == 2) {
                            player.drop(room, inputs[1], 1);
                        } else if (inputs.length == 4 && inputs[2].equals("in") && inputs[3].equals("box")) {
                            player.drop(room.getBox(), inputs[1], 1);
                        } else {
                            throw new IllegalArgumentException(("You can only drop an object on the ground or in a box!\n"));
                        }
                        break;
                    case "Help":
                        if (inputs.length == 1) {
                            help();
                        } else {
                            switch (inputs[1]) {
                                case "door":
                                    Door.help();
                                    break;
                                case "key":
                                    Key.help();
                                    break;
                                case "coin": case "gold": case "diamond":
                                    Currency.help();
                                    break;
                                case "box":
                                    Box.help();
                                    break;
                                default:
                                    throw new IllegalArgumentException(("You can only ask help for object!\n"));
                            }
                        }
                        break;
                    case "Quit":
                        if (inputs.length == 1) {
                            play = false;
                        } else {
                            throw new IllegalArgumentException(("Are you trying to quit the game?\n"));
                        }
                        break;
                    case "Unlock":
                        if (inputs.length == 4 && inputs[2].equals("with") && inputs[3].equals("key")) {
                            switch (inputs[1]) {
                                case "door":
                                    player.unlock(room.getDoor(player.getOrientation()));
                                    break;
                                case "box":
                                    player.unlock(room.getBox());
                                    break;
                                default:
                                    throw new IllegalArgumentException(("You can only unlock a door or a box!\n"));
                            }
                        } else {
                            throw new IllegalArgumentException(("You need a key to unlock!\n"));
                        }
                        break;
                    case "Lock":
                        if (inputs.length == 4 && inputs[2].equals("with") && inputs[3].equals("key")) {
                            switch (inputs[1]) {
                                case "door":
                                    player.lock(room.getDoor(player.getOrientation()));
                                    break;
                                case "box":
                                    player.lock(room.getBox());
                                    break;
                                default:
                                    throw new IllegalArgumentException(("You can only lock a door or a box!\n"));
                            }
                        } else {
                            throw new IllegalArgumentException(("You need a key to lock!\n"));
                        }
                        break;
                    case "Content":
                        if (inputs.length == 2 && inputs[1].equals("box")) {
                            if (room.getBox() != null) {
                                room.getBox().print();
                            }
                        } else {
                            throw new IllegalArgumentException(("You can only look at the content of a box!\n"));
                        }
                        break;
                    case "Transform":
                        if (inputs.length == 4 && inputs[2].equals("in")) {
                            player.transform(inputs[1], inputs[3]);
                        } else {
                            throw new IllegalArgumentException(("Your transformation is not understood!\n"));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException(("Your action is not understood!\n"));
                }
            }
            catch (IllegalArgumentException | IllegalStateException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("You quit the game!");
    }

    /*
       Print the default commends.
    */
    static void help() {
        System.out.println("Turn ( right | left )               : Turn in the specified direction");
        System.out.println("Move                                : Move forward");
        System.out.println("Take <object>                       : Pick up object object");
        System.out.println("Drop <object>                       : Drop object object");
        System.out.println("Action <object1> [ with <object2> ] : Perform action action on object object1, possibly with the help of object object2.");
        System.out.println("Help [ <object> ]                   : This command");
        System.out.println("Quit                                : Quit the game\n");
    }
}
