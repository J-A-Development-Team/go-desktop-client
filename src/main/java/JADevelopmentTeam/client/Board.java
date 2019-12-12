package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Intersection;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Intersection[][] intersections;
    private Tile[][] tiles;
    private int size;
    private int space = 10;

    public Board(int size) {
        this.size = size;
        intersections = new Intersection[size][size];
        this.setOpaque(true);
        tiles = new Tile[size][size];
        this.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                intersections[j][i] = new Intersection(j, i, false);
                tiles[j][i] = new Tile(intersections[j][i], j, i);
                this.add(tiles[j][i]);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        double xMove = 0;
        double yMove = 0;
        double xStart = this.getWidth() / 2.0f / size;
        double yStart = this.getHeight() / 2.0f / size;
        for (int i = 0; i < size; i++) {
            g2d.drawLine((int) (xMove + xStart), (int) yStart, (int) (xMove + xStart), (int) (this.getHeight() - yStart));
            g2d.drawLine((int) xStart, (int) (yMove + yStart), (int) (this.getWidth() - xStart), (int) (yMove + yStart));
            xMove += (double) this.getWidth() / size;
            yMove += (double) this.getHeight() / size;
            //            Funny
//            g2d.drawLine((int) (this.getWidth()/size*i+xStart), (int) yStart, (int) (this.getWidth()/size*i+xStart), (int) (this.getHeight()-yStart));
//            g2d.drawLine((int) xStart, (int) (this.getHeight()/size*i+yStart), (int) (this.getWidth()-xStart), (int) (this.getHeight()/size*i+yStart));
        }
    }

    public Intersection[][] getIntersections() {
        return intersections;
    }

    public void setIntersections(Intersection[][] intersections, ClientGui clientGui) {
        this.intersections = intersections;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Intersection old = tiles[j][i].getIntersection();
                if (!(old.exist() == intersections[j][i].exist() &&
                        old.isStoneBlack() == intersections[j][i].isStoneBlack() &&
                        old.isStoneDead() == intersections[j][i].isStoneDead())) {
                    tiles[j][i].setIntersection(intersections[j][i]);
                    tiles[j][i].repaint();
                    this.repaint();
                    clientGui.repaint();
                }
            }
        }

    }

    public int getBoardSize() {
        return size;
    }

    public void setBoardSize(int size) {
        this.size = size;
    }
}
