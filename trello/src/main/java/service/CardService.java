package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CardDoesNotExist;
import model.Card;
import model.User;
import utils.JsonWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardService {

    private Map<String, Card> cards;

    public CardService() {
        this.cards = new HashMap<>();
    }

    public Card createCard(String cardName) {
        Card card = new Card(cardName);
        cards.put(card.getId(), card);
        System.out.println("Created card: " + card.getId());
        return card;
    }

    public void deleteCard(String cardId) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.remove(cardId);
        System.out.println("Deleted card");
    }

    public void showCardWithId(String cardId) throws JsonProcessingException {
        Card card = getCardById(cardId);
        String jsonString = JsonWrapper.jsonString(card);
        System.out.println(jsonString);
    }

    public void updateCardName(String cardId, String cardName) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.get(cardId).updateName(cardName);
        System.out.println("Updated card name as " + cardName);
    }

    public void updateCardDescription(String cardId, String description) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.get(cardId).updateDescription(description);
        System.out.println("Updated card description as " + description);
    }

    public void assignUserToCard(String cardId, User user) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.get(cardId).assignUser(user);
        System.out.println("Assigned member " + user.getEmail() + " to card " + cardId);
    }

    public void unassignUser(String cardId) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.get(cardId).unAssignUser();
        System.out.println("Unassigned member from card " + cardId);
    }

    public void addTagToCard(String cardId, String tag) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        cards.get(cardId).setTag(tag);
        System.out.println("Updated tag " + tag + " on card " + cardId);
    }

    // helper
    public List<Card> getCardByTag(String tag) {
        List<Card> cardList = new ArrayList<>();
        for(Map.Entry<String, Card> entry: cards.entrySet()) {
            if(entry.getValue().getTag().equals(tag)) {
                cardList.add(entry.getValue());
            }
        }
        return cardList;
    }

    public List<Card> getCardByUser(String emailId) {
        List<Card> cardList = new ArrayList<>();
        for(Map.Entry<String, Card> entry: cards.entrySet()) {
            User member = entry.getValue().getAssignedUser();
            if(member.getEmail().equals(emailId)) {
                cardList.add(entry.getValue());
            }
        }
        return cardList;
    }

    public Card getCardById(String cardId) {
        if(!isCardExist(cardId)) {
            throw new CardDoesNotExist("Card " + cardId + " does not exist");
        }
        return cards.get(cardId);
    }

    public boolean isCardExist(String cardId) {
        return cards.containsKey(cardId);
    }

}
