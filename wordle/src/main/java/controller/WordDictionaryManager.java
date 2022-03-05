package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDictionaryManager {
    Map<String, Integer> wordToDifficultyMap;
    private List<String> wordList;

    public WordDictionaryManager() {
        this.wordToDifficultyMap = new HashMap<>();
        wordList = new ArrayList<>();
    }

    public void addWord(String word, Integer difficulty){
        wordToDifficultyMap.putIfAbsent(word, difficulty);
        wordList.add(word);
    }

    public String getWordOfTheDay(){
        int randomIdx = (int) Math.random() * wordToDifficultyMap.size();
        return wordList.get(randomIdx);
    }

    public Integer getDifficultyOfWord(String word){
        return wordToDifficultyMap.getOrDefault(word, 0);
    }
}
