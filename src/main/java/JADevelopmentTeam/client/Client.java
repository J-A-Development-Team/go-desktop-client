package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Stone;

import javax.swing.*;
import java.awt.*;

public class Client {
    private static ServerConnector  serverConnector;

    public static void main(String[] args) {
        Frame frame = new JFrame();
        frame.setSize(400,400);
        frame.add(new Board(13));
        frame.setVisible(true);
    }
}


