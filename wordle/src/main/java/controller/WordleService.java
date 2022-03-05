package controller;

import model.Player;
import model.Wordle;

import java.util.HashMap;
import java.util.Map;

public class WordleService {

    Map<String, Player> playerMap;
    WordDictionaryManager wordDictionaryManager;
    Wordle wordle;

    public WordleService() {
        this.playerMap = new HashMap<>();
        wordDictionaryManager = new WordDictionaryManager();
    }

    public Player registerPlayer(String name){
        Player newPlayer = new Player(name);
        playerMap.put(name, newPlayer);
        return newPlayer;
    }

    public Wordle startNewGame(){
        Wordle wordle = new Wordle(wordDictionaryManager.getWordOfTheDay());
        this.wordle = wordle;
        return wordle;
    }

    public void guessWord(String playerName, String guessedWord){
        Player player = playerMap.get(playerName);

        // check number of attempts left
        Integer difficulty = wordDictionaryManager.getDifficultyOfWord(wordle.getWordOfTheDay());
        if(player.getNumOfAttempts() >= difficulty){
            System.out.println("Out of chances");
            return;
        }
        player.incrementAttempts();

        // check if word is in dictonary
        if(difficulty == 0 || wordle.getWordOfTheDay().length() != guessedWord.length()){
            System.out.println("Incorrect word");
            return;
        }

        // hint
        String hint = getHint(wordle.getWordOfTheDay(), guessedWord);


        // score calculate

    }

    //a. C = Correct letter and correct position
    //b. P = Correct letter and incorrect position (Partially correct)
    //c. I = Incorrect letter
    private String getHint(String correctWord, String guessedWord){
        char[] hint = new char[correctWord.length()];
        for (int i = 0; i < correctWord.length(); i++) {
            if(correctWord.charAt(i) == guessedWord.charAt(i)){
                hint[i] = 'C'; // TODO :  can be optimized
            }
            else if(correctWord.indexOf(guessedWord.charAt(i)) != -1){
                hint[i] = 'P';
            }
            else{
                hint[i] = 'I';
            }
        }
        return new String(hint);
    }
}
