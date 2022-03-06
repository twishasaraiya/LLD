package controller;

import model.HintCodes;
import model.Wordle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordleService {

    private final WordDictionaryManager wordDictionaryManager;
    private final PlayerService playerService;
    private Wordle wordle;
    private Map<String, Set<String>> playerToWordsPlayedMap;

    public WordleService() {
        playerService = new PlayerService();
        wordDictionaryManager = new WordDictionaryManager();
    }

    public void registerPlayer(String name){
        playerService.newPlayer(name);
    }

    public void addWord(String word, Integer difficulty){
        wordDictionaryManager.addWord(word, difficulty);
    }

    public void startNewWordle(){
        Wordle wordle = new Wordle(wordDictionaryManager.getWordOfTheDay());
        playerService.resetRound();
        resetPlayerToWordsPlayedMap();
        setCurrentWordle(wordle);
        System.out.println("Number of letters = " + wordle.getWordOfTheDay().length());
        System.out.println("Number of attempts = " + wordDictionaryManager.getDifficultyOfWord(wordle.getWordOfTheDay()));
    }

    private void setCurrentWordle(Wordle wordle){
        this.wordle = wordle;
    }

    public void resetPlayerToWordsPlayedMap(){
        playerToWordsPlayedMap = new HashMap<>();
    }

    private Set<String> getWordsPlayedSoFarByPlayer(String playerName){
        return playerToWordsPlayedMap.getOrDefault(playerName, new TreeSet<>());
    }
    private Boolean hasPlayerAlreadyPlayedForWord(String playerName, String wordOfDay){
        Set<String> wordsPlayed = getWordsPlayedSoFarByPlayer(playerName);
        if(wordsPlayed.size() == 0) return Boolean.FALSE;
        if(!wordsPlayed.contains(wordOfDay)) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    private void updatePlayerHasPlayedTheRound(String playerName, String wordOfDay){
        Set<String> wordsPlayedList = getWordsPlayedSoFarByPlayer(playerName);
        wordsPlayedList.add(wordOfDay);
        playerToWordsPlayedMap.put(playerName, wordsPlayedList);
    }

    public void guessWord(String playerName, String guessedWord){
        if(wordle == null){
            System.out.println("Please start a wordle first before guessing");
            return;
        }

        // check number of attempts left
        String wordOfTheDay = wordle.getWordOfTheDay();
        Integer difficulty = wordDictionaryManager.getDifficultyOfWord(wordOfTheDay);

        // check if the player has already played the round
        if(hasPlayerAlreadyPlayedForWord(playerName,wordOfTheDay)){
            System.out.println("Already played this round");
            return;
        }

        playerService.increaseAttemptsForPlayer(playerName);
        if(!checkIfAttemptsLeftForPlayer(playerName, difficulty)){
            System.out.println("Out of chances");
            updatePlayerHasPlayedTheRound(playerName, wordOfTheDay);
            return;
        }


        // check if word is in dictonary
        if(!wordDictionaryManager.checkIfWordExists(guessedWord) || wordOfTheDay.length() != guessedWord.length()){
            System.out.println("Incorrect word");
            return;
        }

        // generate hint
        HintCodes[] hintCodes = getHint(wordOfTheDay, guessedWord);
        String hint = mapHintCodesToString(hintCodes);
        System.out.println("Hint = " + hint);

        // store hint for player
        playerService.addHintForPlayer(playerName, hint);
        // display hint list for player
        playerService.displayHintForPlayer(playerName);

        // score calculate
        if(isGuessCorrect(hintCodes)){
            Integer score = calculateScoreForPlayerAndWord(playerName, difficulty);
            playerService.incrementCurrentScoreForPlayer(playerName, score);
            updatePlayerHasPlayedTheRound(playerName, wordOfTheDay);
        }
    }

    private String mapHintCodesToString(HintCodes[] hintCodes){
        String hintString = "";
        for (HintCodes hintCode:
                hintCodes)
            hintString = hintString + hintCode + " ";
        return hintString;
    }

    private Integer calculateScoreForPlayerAndWord(String playerName, Integer difficulty){
        return Math.round(difficulty / playerService.getNumberOfAttemptsForPlayer(playerName));
    }

    private Boolean isGuessCorrect(HintCodes[] hint){
        return Arrays.stream(hint).allMatch(s -> s.equals(HintCodes.C));
    }

    private Boolean checkIfAttemptsLeftForPlayer(String playerName, Integer totalAttempts){
        if(playerService.getNumberOfAttemptsForPlayer(playerName) < totalAttempts) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    //a. C = Correct letter and correct position
    //b. P = Correct letter and incorrect position (Partially correct)
    //c. I = Incorrect letter
    private HintCodes[] getHint(String correctWord, String guessedWord){
        HintCodes[] hintCodes = new HintCodes[correctWord.length()];
        for (int i = 0; i < correctWord.length(); i++) {
            if(correctWord.charAt(i) == guessedWord.charAt(i)){
                hintCodes[i] = HintCodes.C;
            }
            else if(correctWord.indexOf(guessedWord.charAt(i)) != -1){
                hintCodes[i] = HintCodes.P;
            }
            else{
                hintCodes[i] = HintCodes.I;
            }
        }
        return hintCodes;
    }

    public void displayScoreboard(String type){
        playerService.displayScoreboard(type);
    }
}
