package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        Board board = new Board(9);
        frame.add(board);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ServerConnector serverConnector = ServerConnector.getInstance();
        while (true) {
            try {
                handleInput(serverConnector.getData(),board);

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(25);
                System.exit(-1);
            }
        }
    }

    private static void handleInput(DataPackage dataPackage, Board board) {
        System.out.println("Handle");
        if (dataPackage.getInfo() == DataPackage.Info.StoneTable) {
            board.setIntersections((Intersection[][]) dataPackage.getData());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.println(board.getIntersections()[j][i].exist()+" "+j+" "+i);
                }
            }
//                System.out.println(board.getStones()[0][0].getXCoordinate());
        }
    }
}


