package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Board;
import model.BoardList;
import model.Card;
import model.User;
import java.util.List;

public class TrelloService {
    private UserService userService;
    private BoardService boardService;
    private BoardListService boardListService;
    private CardService cardService;

    public TrelloService() {
        this.userService = new UserService();
        this.boardService = new BoardService();
        this.boardListService = new BoardListService();
        this.cardService = new CardService();
    }

    /*************************  USER  *******************************/
    public List<User> getMembers() {
        return userService.getMembers();
    }

    public void createMember(String name, String email) {
        userService.createMember(name, email);
    }

    /*************************  BOARD  *******************************/
    public void createBoard(String name) {
        boardService.createBoard(name);
    }

    public void deleteBoard(String boardId) {
        try  {
            boardService.deleteBoard(boardId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showBoard() throws JsonProcessingException {
        try {
            boardService.showBoard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showBoardWithId(String boardId) throws JsonProcessingException {
        try {
            boardService.showBoardWithId(boardId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBoardName(String boardId, String boardName) {
        try  {
            boardService.updateBoardName(boardId, boardName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBoardPrivacy(String boardId, String privacy) {
        try  {
            boardService.updateBoardPrivacy(boardId, privacy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMemberInBoard(String boardId, String userId) {
        try {
            User member = userService.getUserById(userId);
            boardService.addMemberInBoard(boardId, member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeMemberFromBoard(String boardId, String userId) {
        try {
            User member = userService.getUserById(userId);
            boardService.removeMemberFromBoard(boardId, member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*************************  LIST  *******************************/
    public void createList(String boardId, String name) {
        try {
            Board board = boardService.getBoardById(boardId);
            BoardList list = boardListService.createList(name);
            boardService.addListToBoard(boardId, list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteList(String listId) {
        try {
            BoardList list = boardListService.getListById(listId);
            Board board = boardService.getBoardToWhichListBelongs(list);
            boardListService.deleteList(list.getId());
            boardService.removeListFromBoard(board.getId(), list);
            System.out.println("Deleted list " + list.getId() + " from board " + board.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void showListWithId(String listId) throws JsonProcessingException {
        try {
            boardListService.showListWithId(listId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateListName(String listId, String listName) {
        try {
            boardListService.updateListName(listId, listName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllCardFromList(String listId) {
        try {
            List<Card> cards = boardListService.getAllCardsByListId(listId);
            for (Card card: cards) {
                cardService.deleteCard(card.getId());
                System.out.println("Removed card " + card.getId() + " from list " + listId);
            }
            boardListService.deleteAllCardFromList(listId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cloneList(String listId) {
        try{
            BoardList list = boardListService.getListById(listId);
            BoardList cloneList = list.clone();
            System.out.println(cloneList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*************************  CARD  *******************************/
    public void createCard(String listId, String cardName) {
        try {
            Card card = cardService.createCard(cardName);
            boardListService.addCardToList(listId, card);
            System.out.println("Created card: " + card.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCard(String cardId) {
        try {
            Card card = cardService.getCardById(cardId);
            BoardList list = boardListService.getListToWhichCardBelongs(card);
            cardService.deleteCard(cardId);
            boardListService.removeCardFromList(list.getId(), card);
            System.out.println("Deleted card " + cardId + " from list " + list.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCardWithId(String cardId) throws JsonProcessingException {
        try {
            cardService.showCardWithId(cardId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCardName(String cardId, String cardName) {
        try {
            cardService.updateCardName(cardId, cardName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCardDescription(String cardId, String description) {
        try {
            cardService.updateCardDescription(cardId, description);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void assignUserToCard(String cardId, String email) {
        try {
            User member = userService.getUserByEmail(email);
            cardService.assignUserToCard(cardId, member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unassignUser(String cardId) {
        try {
            cardService.unassignUser(cardId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveCard(String cardId, String listId) {
        try {
            Card card = cardService.getCardById(cardId);
            BoardList newList = boardListService.getListById(listId);
            Board newListBoard = boardService.getBoardToWhichListBelongs(newList);
            BoardList orgList = boardListService.getListToWhichCardBelongs(card);
            Board orgListBoard = boardService.getBoardToWhichListBelongs(orgList);
            if(!newListBoard.equals(orgListBoard)) {
                System.out.println("Card belongs to two different board");
                return;
            }
            // remove card from original list
            boardListService.removeCardFromList(orgList.getId(), card);
            // move to new list
            boardListService.addCardToList(listId, card);
            System.out.println("Move card " + cardId + " from " + orgList.getId() + " to " + listId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTagToCard(String cardId, String tag) {
        try {
            cardService.addTagToCard(cardId, tag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getCardByTag(String tag) throws JsonProcessingException {
       try {
           List<Card> cards = cardService.getCardByTag(tag);
           for(Card card: cards) {
               showCardWithId(card.getId());
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

    public void getCardByUser(String emailId) throws JsonProcessingException {
        try {
            List<Card> cards = cardService.getCardByUser(emailId);
            for(Card card: cards) {
                System.out.println("card " + card.getName());
                showCardWithId(card.getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}