package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Tile extends JPanel {
    private Stone stone;
    private int xCoordinate;
    private int yCoordinate;

    public Tile(Stone stone, int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.stone = stone;
        initialize();
    }

    private void initialize() {
        this.setLayout(new CardLayout());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendData();
            }
        });
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
        repaint();
    }

    public void removeStone() {
        this.stone = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (stone != null) {
            g2d.setPaint(Color.BLACK);
           if (stone.isBlack()) {
                g2d.setPaint(Color.BLACK);
            } else {
                g2d.setPaint(Color.WHITE);
            }
            Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, this.getWidth(), this.getHeight());
            g2d.fill(ellipse);
            g2d.setPaint(Color.BLACK);
            g2d.draw(ellipse);

        }
    }

    private void sendData() {
        ServerConnector.getInstance();
        if (stone == null) {
            System.out.println("klikniete w puste x:"+xCoordinate+" y:"+yCoordinate);
            //ServerConnector.getInstance().sendData(new DataPackage(new Stone(xCoordinate, yCoordinate), DataPackage.Info.Stone));
        } else {
            System.out.println("klikniete x:"+xCoordinate+" y:"+yCoordinate);
        }
    }
}
