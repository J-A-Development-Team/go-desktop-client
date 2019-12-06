package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {
    private Board board;
    private JMenuBar menuBar;
    private JLabel label;
    public ClientGui(int size){
        super();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        label = new JLabel("Unknown color");
        label.setOpaque(true);
        menuBar.add(label);
        this.setJMenuBar(menuBar);
        this.setSize(500, 500);
        board = new Board(size);
        this.add(board);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(224,172,105));
    }
    public void handleInput(DataPackage dataPackage) {
        System.out.println("Handle");
        switch (dataPackage.getInfo()) {
            case StoneTable: {
                board.setIntersections((Intersection[][]) dataPackage.getData());
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.println(board.getIntersections()[j][i].exist() + " " + j + " " + i);
                    }
                }
                this.repaint();
                break;
            }
            case Info:
                JOptionPane.showMessageDialog(this, dataPackage.getData(),"WARNING",JOptionPane.ERROR_MESSAGE);
                break;
            case Turn:
                this.setTitle((String) dataPackage.getData());
                break;
            case PlayerColor:
                String color = (String) dataPackage.getData();
                if (color.equals("black")){
                    label.setText("You are playing black stones");
                    label.setForeground(Color.WHITE);
                    label.setBackground(Color.BLACK);
                } else {
                    label.setText("You are playing white stones");
                    label.setForeground(Color.BLACK);
                    label.setBackground(Color.WHITE);
                }
        }
    }
}
