package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Stone;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        Board board = new Board(9);
        frame.add(board);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ServerConnector serverConnector = ServerConnector.getInstance();
        Scanner scanner = null;
        try {
            scanner = serverConnector.getInputAsScanner();
        } catch (IOException e) {
            System.exit(-1);
        }
        while (scanner.hasNextLine()) {
            try {
                handleInput(serverConnector.getData(),board);

            } catch (IOException | ClassNotFoundException e) {
                System.exit(-1);
            }
        }
    }

    private static void handleInput(DataPackage dataPackage, Board board) {
        switch (dataPackage.getInfo()) {
            case StoneTable:
                board.setStones((Stone[][]) dataPackage.getData());
        }
    }
}


