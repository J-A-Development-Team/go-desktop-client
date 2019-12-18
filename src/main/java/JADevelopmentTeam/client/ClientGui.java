package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;
import JADevelopmentTeam.common.TerritoryStates;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    private static Board board;
    private JMenuBar menuBar;
    private JLabel colorLabel;
    private JLabel yourPointsLabel;
    private JButton passButton;
    private static ClientGui clientGui;
    private ConfigurationScreen configurationScreen;

    public ClientGui(int size) {
        super();
        clientGui = this;
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menuBar.setLayout(new BorderLayout());
        colorLabel = new JLabel("Unknown color");

        colorLabel.setOpaque(true);
        yourPointsLabel = new JLabel("No Points yet");
        yourPointsLabel.setHorizontalAlignment(JLabel.CENTER);
        yourPointsLabel.setOpaque(true);
        passButton = new JButton("PASS");
        passButton.setBackground(Color.RED);
        passButton.setEnabled(false);
        passButton.addActionListener(e -> ServerConnector.getInstance().sendData(new DataPackage("Pass", DataPackage.Info.Pass)));
        menuBar.add(colorLabel,BorderLayout.WEST);
        menuBar.add(passButton,BorderLayout.EAST);
        menuBar.add(yourPointsLabel,BorderLayout.CENTER);
        passButton.setVisible(false);
        this.setSize(960, 600);
        this.configurationScreen = new ConfigurationScreen(this);
        this.add(configurationScreen);
        board = new Board(size);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(224, 172, 105));

    }
    public void handleInput(DataPackage dataPackage) {
        System.out.println("Handle");
        switch (dataPackage.getInfo()) {
            case StoneTable: {
                board.setIntersections((Intersection[][]) dataPackage.getData(), this);
                this.repaint();
                break;
            }
            case Info:
                String i = (String) dataPackage.getData();
                JOptionPane.showMessageDialog(this, i, "WARNING", JOptionPane.ERROR_MESSAGE);
                if (i.equals("Connection to opponent lost"))
                    System.exit(0);
                break;
            case Turn:
                String info = (String) dataPackage.getData();
                this.setTitle(info);
                Client.yourTurn = info.equals("Your turn");
                if (info.equals("Your turn")){
                    passButton.setEnabled(true);
                    passButton.setBackground(Color.RED);
                } else if (info.equals("Remove Dead Stones")) {
                    board.eraseLastStoneInfo(this);
                    passButton.setText("Accept");
                    passButton.setBackground(Color.GREEN);
                    passButton.setEnabled(true);
                    menuBar.repaint();
                } else {
                    passButton.setBackground(Color.GRAY);
                    passButton.setEnabled(false);
                }
                break;
            case PlayerColor:
                String color = (String) dataPackage.getData();
                if (color.equals("black")) {
                    colorLabel.setText("You are playing black stones");
                    colorLabel.setForeground(Color.WHITE);
                    colorLabel.setBackground(Color.BLACK);
                    Client.userIsBlack = true;
                } else {
                    colorLabel.setText("You are playing white stones");
                    colorLabel.setForeground(Color.BLACK);
                    colorLabel.setBackground(Color.WHITE);
                    Client.userIsBlack = false;
                }
                passButton.setVisible(true);
                break;
            case Points:
                Integer pointsAsInt = (Integer) dataPackage.getData();
                String points = pointsAsInt.toString();
                yourPointsLabel.setText("Prisoners: "+points);
                break;
            case Stone:
                board.setLastStone((Intersection) dataPackage.getData(),this);
                break;
            case Pass:
                JOptionPane.showMessageDialog(this, dataPackage.getData(), "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
            case TerritoryTable:
                board.setTerritory((TerritoryStates[][]) dataPackage.getData(),this);
                break;
            case GameResult:
                JOptionPane.showMessageDialog(this,(String) dataPackage.getData(), "Game End", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
        }
        System.out.println(dataPackage.getInfo());
    }
    void startBoard(int size){
        board = new Board(size);
        this.getContentPane().removeAll();
        this.getContentPane().add(board);
        this.setJMenuBar(menuBar);
        this.setSize(500, 500);
        this.revalidate();
        repaintBoardAndClientGui();
    }


    static void repaintBoardAndClientGui(){
        board.repaint();
        clientGui.repaint();
    }
}
