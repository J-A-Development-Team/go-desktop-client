package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        ServerConnector serverConnector = ServerConnector.getInstance();
        ClientGui gui = new ClientGui(9);
        while (true) {
            try {
                gui.handleInput(serverConnector.getData());

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(25);
                System.exit(-1);
            }
        }
    }
}


