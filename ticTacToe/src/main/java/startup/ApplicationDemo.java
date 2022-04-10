package startup;

import service.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter grid size");
        GameService game = new GameService(Integer.parseInt(br.readLine()));

        System.out.println("Enter the number of players");
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Enter piece and player name");
            String[] cmd = br.readLine().split(" ");
            game.addPlayer(cmd[0].charAt(0), cmd[1]);
        }

        while(true) {
            System.out.println("Enter the coordinates");
            String[] cmd = br.readLine().split(" ");
            if(cmd[0].equals("exit")) return;

            if(!game.hasNextTurn(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]))) return;
        }
    }
}
