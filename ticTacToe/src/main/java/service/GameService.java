package service;

import enums.GameStatus;
import model.Board;
import model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class GameService {
    private Board board;
    private Queue<Player> players;
    BufferedReader br;

    public GameService()  throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter grid size");
        this.board = new Board(Integer.parseInt(br.readLine()));
        this.players = new LinkedList<>();
    }

    public void addPlayers(int n) throws IOException {
        for(int i=0; i<n; i++) {
            System.out.println("Enter piece and player name");
            String cmd[] = br.readLine().split(" ");
            players.add(new Player(cmd[1], cmd[0].charAt(0)));
        }

        board.printBoard();
    }

    public void play() throws IOException {
        while (players.size() > 1) {
            Player player = players.peek();
            System.out.print(player.getName() + " ");
            int[] coordinates = getUserInput();
            if(coordinates[0] == -1 && coordinates[1] == -1) break;
            GameStatus status = board.playGame(coordinates[0], coordinates[1], player);
            if(status.equals(GameStatus.valueOf("VALID"))) {
                player = players.poll();
                players.offer(player);
            }
            else if(status.equals(GameStatus.valueOf("WON"))) {
                players.poll();
            } else if (status.equals(GameStatus.valueOf("DRAW"))) {
                players.clear();
            }
        }
    }

    private int[] getUserInput() throws IOException {
        System.out.println("Enter the coordinates");
        String cmd = br.readLine();
        if(cmd.equals("exit")) {
            return new int[]{-1, -1};
        }
        String[] coordinates = br.readLine().split(" ");
        return new int[] {Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])};
    }

}
