package startup;

import enums.Command;
import service.TrelloService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TrelloService trelloService = new TrelloService();

        // created 10 dummy users
        for(int i=1; i<=10; i++) {
            trelloService.createMember("user"+i, "user"+i+"@gmail.com");
        }

        String input = br.readLine();

        while (!input.equalsIgnoreCase("exit")) {
            String[] inputArr = input.split(" ");
            switch (Command.getType(inputArr[0])) {
                case BOARD:
                    Command commandType = Command.getType(inputArr[1]);

                    if (commandType != null) {
                        if(Command.getType(inputArr[1]).equals(Command.CREATE)) {
                            // BOARD CREATE <board_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(2, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.createBoard(name);
                        }

                        else if(Command.getType(inputArr[1]).equals(Command.DELETE)) {
                            // BOARD DELETE <board_id>
                            trelloService.deleteBoard(inputArr[2]);
                        }
                    } else {
                         if(Command.getType(inputArr[2]).equals(Command.NAME)) {
                             // BOARD <board_id> name <update_board_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );

                            trelloService.updateBoardName(inputArr[1], name);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.PRIVACY)) {
                             // BOARD <board_id> privacy <privacy_type>
                            trelloService.updateBoardPrivacy(inputArr[1], inputArr[3]);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.ADD_MEMBER)) {
                            // BOARD <board_id> ADD_MEMBER
                            for (int i = 0; i < trelloService.getMembers().size(); i++) {
                                System.out.println("Enter " + (i+1) + " to add " + trelloService.getMembers().get(i).getName());
                            }
                            int idx = Integer.parseInt(br.readLine());
                            trelloService.addMemberInBoard(inputArr[1], trelloService.getMembers().get(idx-1).getUserId());
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.REMOVE_MEMBER)) {
                             // BOARD <board_id> REMOVE_MEMBER
                            for (int i = 0; i < trelloService.getMembers().size(); i++) {
                                System.out.println("Enter " + (i+1) + " to add " + trelloService.getMembers().get(i).getName());
                            }
                            int idx = Integer.parseInt(br.readLine());
                            trelloService.removeMemberFromBoard(inputArr[1], trelloService.getMembers().get(idx-1).getUserId());
                        }
                    }
                    break;

                case LIST:
                    commandType = Command.getType(inputArr[1]);
                    if (commandType != null) {
                        if(Command.getType(inputArr[1]).equals(Command.CREATE)) {
                            // LIST CREATE <board_id> <list_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.createList(inputArr[2], name);
                        }

                        else if(Command.getType(inputArr[1]).equals(Command.DELETE)) {
                            // LIST DELETE <list_id>
                            trelloService.deleteList(inputArr[2]);
                        }
                    } else {
                        if(Command.getType(inputArr[2]).equals(Command.NAME)) {
                            // LIST <list_id> name <updated_list_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.updateListName(inputArr[1], name);
                        }

                        else if (Command.getType(inputArr[2]).equals(Command.CARD) && Command.getType(inputArr[3]).equals(Command.DELETE)) {
                            // LIST LIST_ID CARD DELETE
                            trelloService.deleteAllCardFromList(inputArr[1]);
                        }

                        else if (Command.getType(inputArr[2]).equals(Command.CLONE)) {
                            // LIST <LIST_ID> CLONE
                            trelloService.cloneList(inputArr[1]);
                        }
                    }
                    break;

                case CARD:
                    commandType = Command.getType(inputArr[1]);

                    if (commandType != null) {
                        if(Command.getType(inputArr[1]).equals(Command.CREATE)) {
                            // CARD CREATE <list_id> <card_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.createCard(inputArr[2], name);
                        }

                        else if(Command.getType(inputArr[1]).equals(Command.DELETE)) {
                            // CARD DELETE <card_id>
                            trelloService.deleteCard(inputArr[2]);
                        }

                        else if (Command.getType(inputArr[1]).equals(Command.USER)) {
                            // CARD USER EMAIL_ID
                            trelloService.getCardByUser(inputArr[2]);
                        }

                        else if (Command.getType(inputArr[1]).equals(Command.TAG)) {
                            // CARD TAG tag
                            trelloService.getCardByTag(inputArr[2]);
                        }
                    } else {
                        if(Command.getType(inputArr[2]).equals(Command.NAME)) {
                            // CARD <card_id> NAME <updated_card_name>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.updateCardName(inputArr[1], name);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.DESCRIPTION)) {
                            // CARD <card_id> DESCRIPTION <updated_card_description>
                            String description = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.updateCardDescription(inputArr[1], description);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.TAG)) {
                            // CARD <card_id> TAG <tag>
                            String tag = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.addTagToCard(inputArr[1], tag);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.ASSIGN)) {
                            // CARD <card_id> ASSIGN <email>
                            String name = String.join(
                                    " ",
                                    IntStream.range(3, inputArr.length)
                                            .mapToObj(i -> inputArr[i])
                                            .toArray(String[]::new)
                            );
                            trelloService.assignUserToCard(inputArr[1], name);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.UNASSIGN)) {
                            // CARD <card_id> UNASSIGN
                            trelloService.unassignUser(inputArr[1]);
                        }

                        else if(Command.getType(inputArr[2]).equals(Command.MOVE)) {
                            // CARD <card_id> MOVE <list_id>
                            trelloService.moveCard(inputArr[1], inputArr[3]);
                        }

                    }

                    break;

                case SHOW:
                    if(inputArr.length > 1) {
                        if(Command.getType(inputArr[1]).equals(Command.BOARD)) {
                            // SHOW BOARD <board_id>
                            trelloService.showBoardWithId(inputArr[2]);
                        }

                        else if (Command.getType(inputArr[1]).equals(Command.LIST)) {
                            // SHOW LIST <list_id>
                            trelloService.showListWithId(inputArr[2]);
                        }

                        else if (Command.getType(inputArr[1]).equals(Command.CARD)) {
                            // SHOW CARD <card_id>
                            trelloService.showCardWithId(inputArr[2]);
                        }
                    } else {
                        // SHOW
                        trelloService.showBoard();
                    }
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }

            input = br.readLine();
        }
    }
}
