package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {
    private Board board;
    private JMenuBar menuBar;
    private JLabel colorLabel;
    private JLabel yourPointsLabel;
    private JButton passButton;

    public ClientGui(int size) {
        super();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menuBar.setLayout(new BorderLayout());
        colorLabel = new JLabel("Unknown color");

        colorLabel.setOpaque(true);
        yourPointsLabel = new JLabel("No Points yet");
        yourPointsLabel.setOpaque(true);
        passButton = new JButton("PASS");
        passButton.setBackground(Color.RED);
        passButton.addActionListener(e -> ServerConnector.getInstance().sendData(new DataPackage("Pass", DataPackage.Info.Pass)));
        menuBar.add(colorLabel,BorderLayout.LINE_START);
        menuBar.add(passButton,BorderLayout.LINE_END);
        menuBar.add(yourPointsLabel,BorderLayout.CENTER);
        passButton.setVisible(false);
        this.setJMenuBar(menuBar);
        this.setSize(500, 500);
        board = new Board(size);
        this.add(board);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(224, 172, 105));
    }

    public void handleInput(DataPackage dataPackage) {
        System.out.println("Handle");
        switch (dataPackage.getInfo()) {
            case StoneTable: {
                board.setIntersections((Intersection[][]) dataPackage.getData(), this);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.println(board.getIntersections()[j][i].exist() + " " + j + " " + i);
                    }
                }
                this.repaint();
                break;
            }
            case Info:
                JOptionPane.showMessageDialog(this, dataPackage.getData(), "WARNING", JOptionPane.ERROR_MESSAGE);
                break;
            case Turn:
                System.out.println("Ustawiam info na "+ (String) dataPackage.getData());
                this.setTitle((String) dataPackage.getData());
                break;
            case PlayerColor:
                String color = (String) dataPackage.getData();
                if (color.equals("black")) {
                    colorLabel.setText("You are playing black stones");
                    colorLabel.setForeground(Color.WHITE);
                    colorLabel.setBackground(Color.BLACK);
                } else {
                    colorLabel.setText("You are playing white stones");
                    colorLabel.setForeground(Color.BLACK);
                    colorLabel.setBackground(Color.WHITE);
                }
                passButton.setVisible(true);
                break;
            case Points:
                Integer pointsAsInt = (Integer) dataPackage.getData();
                String points = pointsAsInt.toString();
                yourPointsLabel.setText("Prisoners: "+points);
        }
        System.out.println(dataPackage.getInfo());
    }
}
