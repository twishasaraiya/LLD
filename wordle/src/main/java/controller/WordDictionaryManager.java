package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordDictionaryManager {
    private Map<String, Integer> wordToDifficultyMap;
    private List<String> wordList;
    private Set<String> sortedWordsSet;

    public WordDictionaryManager() {
        this.wordToDifficultyMap = new HashMap<>();
        wordList = new ArrayList<>();
        sortedWordsSet = new TreeSet<>();
    }

    public void addWord(String word, Integer difficulty){
        wordToDifficultyMap.putIfAbsent(word, difficulty);
        wordList.add(word);
        sortedWordsSet.add(sortString(word));
    }

    public String getWordOfTheDay(){
        int randomIdx = (int) (Math.random() * wordToDifficultyMap.size());
        return wordList.get(randomIdx);
    }

    public Integer getDifficultyOfWord(String word){
        return wordToDifficultyMap.getOrDefault(word, 0);
    }

    public Boolean checkIfWordExists(String word){
        return sortedWordsSet.contains(sortString(word));
    }

    private String sortString(String input){
        char[] characters = input.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }
}
