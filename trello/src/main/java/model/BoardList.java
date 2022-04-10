package model;

import java.util.*;

public class BoardList {
    private final String id;
    private String name;
    private List<Card> cards;

    public BoardList(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        cards = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isCardExist(Card card) {
        return cards.contains(card);
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    @Override
    public String toString() {
        return "BoardList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }

    @Override
    public BoardList clone() {
        BoardList cloneList = new BoardList(this.name);
        for (Card card: cards) {
            cloneList.addCard(card.clone());
        }
        return cloneList;
    }
}
