package startup;

import service.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GameService game = new GameService();
        System.out.println("Enter the number of players");
        int n = Integer.parseInt(br.readLine());

        game.addPlayers(n);

        game.play();
    }
}
