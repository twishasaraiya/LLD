package model;

import java.util.ArrayList;
import java.util.List;

public class Wordle {
    List<Round> roundList;
    String wordOfTheDay;

    public Wordle(String wordOfTheDay) {
        roundList = new ArrayList<>();
        this.wordOfTheDay = wordOfTheDay;
    }

    public String getWordOfTheDay() {
        return wordOfTheDay;
    }

}