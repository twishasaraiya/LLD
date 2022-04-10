package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BoardListDoesNotExist;
import model.BoardList;
import model.Card;
import utils.JsonWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardListService {
    private Map<String, BoardList> lists;

    public BoardListService() {
        lists = new HashMap<>();
    }

    public BoardList createList(String name) {
        BoardList list = new BoardList(name);
        lists.put(list.getId(), list);
        System.out.println("Created list: " + list.getId());
        return list;
    }

    public void deleteList(String listId) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        lists.remove(listId);
        System.out.println("Removed list");
    }

    public void showListWithId(String listId) throws JsonProcessingException {
        BoardList list = getListById(listId);
        String jsonString = JsonWrapper.jsonString(list);
        System.out.println(jsonString);
    }

    public void updateListName(String listId, String listName) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        lists.get(listId).updateName(listName);
        System.out.println("Updated list name as " + listName);
    }

    public void deleteAllCardFromList(String listId) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        lists.get(listId).getCards().clear();
        System.out.println("Deleted all card from the list");
    }

    public void addCardToList(String listId, Card card) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        lists.get(listId).addCard(card);
        System.out.println("Added card to list");
    }

    public void removeCardFromList(String listId, Card card) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        lists.get(listId).removeCard(card);
        System.out.println("Removed card from list");
    }

    // helper
    public BoardList getListById(String listId) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        return lists.get(listId);
    }

    public BoardList getListToWhichCardBelongs(Card card) {
        for(String id: lists.keySet()) {
            if(lists.get(id).isCardExist(card)) {
                return lists.get(id);
            }
        }
        return null;
    }

    public List<Card> getAllCardsByListId(String listId) {
        if(!isBoardListExist(listId)) {
            throw new BoardListDoesNotExist("List " + listId + " does not exist");
        }
        return lists.get(listId).getCards();
    }

    public boolean isBoardListExist(String listId) {
        return lists.containsKey(listId);
    }

}
