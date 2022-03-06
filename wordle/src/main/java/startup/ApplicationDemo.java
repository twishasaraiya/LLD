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
        while (true){
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]){
                case "REGISTER":
                    wordleService.registerPlayer(cmd[1]);
                    break;
                case "ADD_WORD_TO_DICTIONARY":
                    wordleService.addWord(cmd[1], Integer.parseInt(cmd[2]));
                    break;
                case "START_NEW_WORDLE":
                    wordleService.startNewWordle();
                    break;
                case "GUESS":
                    wordleService.guessWord(cmd[1], cmd[2]);
                    break;
                case "SCORECARD":
                    wordleService.displayScoreboard(cmd[1]);
                    break;
            }
        }
    }
}
