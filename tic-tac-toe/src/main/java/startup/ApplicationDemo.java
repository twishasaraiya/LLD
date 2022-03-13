package startup;

import service.GameService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        GameService gameService = new GameService();
        System.out.println("Input 2 player in following format <Piece> <PlayerName>");
        for (int i = 0; i < 2; i++) {
            String[] info = br.readLine().split(" ");
            gameService.addPlayer(info[1], info[0]);
        }

        String input = br.readLine();
        while(!input.equals("exit")){
            String[] cellPosition = input.split(" ");
            gameService.takeTurn(Integer.parseInt(cellPosition[0]), Integer.parseInt(cellPosition[1]));
            input = br.readLine();
        }

    }
}
