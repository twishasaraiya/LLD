package startup;

import controller.WordDictionaryManager;
import controller.WordleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        WordleService wordleService = new WordleService();
        WordDictionaryManager wordDictionaryManager = new WordDictionaryManager();
        while (true){
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]){
                case "REGISTER":
                    wordleService.registerPlayer(cmd[1]);
                    break;
                case "ADD_WORD_TO_DICTIONARY":
                    wordDictionaryManager.addWord(cmd[1], Integer.parseInt(cmd[2]));
                    break;
                case "START_NEW_WORDLE":
                    wordleService.startNewGame();
                    break;
                case "GUESS":

            }
        }
    }
}
