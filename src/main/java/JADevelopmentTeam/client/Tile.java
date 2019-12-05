package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

public class Tile extends JPanel {
    private Intersection intersection;
    private int xCoordinate;
    private int yCoordinate;

    public Tile(Intersection intersection, int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.intersection = intersection;
        initialize();
    }

    private void initialize() {
        this.setOpaque(true);
        this.setLayout(new CardLayout());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendData();
            }
        });
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
        repaint();
    }

    public void removeStone() {
        this.intersection = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(new Color(224,172,105));
        if (intersection.exist()) {
            g2d.setPaint(Color.BLACK);
           if (intersection.isStoneBlack()) {
                g2d.setPaint(Color.BLACK);
            } else {
                g2d.setPaint(Color.WHITE);
            }
           int width =this.getWidth();
           int height = this.getHeight();

            Ellipse2D.Double ellipse = new Ellipse2D.Double(width*0.05, height*0.05, width*0.90,  height*0.90);
            g2d.fill(ellipse);
            g2d.setPaint(Color.BLACK);
            g2d.draw(ellipse);
            System.out.println(this.getHeight());
        }
    }

    private void sendData() {
        ServerConnector.getInstance();
        if (!intersection.exist()) {
            System.out.println("klikniete w puste x:"+xCoordinate+" y:"+yCoordinate);
            ServerConnector.getInstance().sendData(new DataPackage(new Intersection(xCoordinate, yCoordinate), DataPackage.Info.Stone));
        } else {
            System.out.println("klikniete x:"+xCoordinate+" y:"+yCoordinate);
        }
    }
}
