package startup;

import service.ChessBoardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ChessBoardService chessBoardService = new ChessBoardService();

        String input = br.readLine();

        while (!input.equalsIgnoreCase("exit")) {
            String[] inputArr = input.split(" ");

            if(chessBoardService.play(inputArr[0], inputArr[1])) return;

            input = br.readLine();
        }

    }
}
