package JADevelopmentTeam.client;
import javax.swing.*;
import java.io.IOException;

public class Client {
    public static boolean userIsBlack = false;
    public static boolean yourTurn = false;
    public static void main(String[] args) {
        ServerConnector serverConnector = ServerConnector.getInstance();
        ClientGui gui = new ClientGui(9);
        while (true) {
            try {
                gui.handleInput(serverConnector.getData());

            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(gui, "Program will be shut down", "Connection with server lost", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
}


