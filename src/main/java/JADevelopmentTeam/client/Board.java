package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Intersection;
import JADevelopmentTeam.common.TerritoryStates;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Intersection[][] intersections;
    Tile[][] tiles;
    private int size;

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

    public void eraseLastStoneInfo(ClientGui clientGui) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[j][i].isLast()) {
                    tiles[j][i].setLast(false);
                    tiles[j][i].repaint();
                    this.repaint();
                    clientGui.repaint();
                }
            }
        }
    }

    public void setLastStone(Intersection intersection, ClientGui clientGui) {
        int x = intersection.getXCoordinate();
        int y = intersection.getYCoordinate();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (x == j && y == i) {
                    tiles[j][i].setLast(true);
                    tiles[j][i].repaint();
                    this.repaint();
                    clientGui.repaint();
                } else if (tiles[j][i].isLast()) {
                    tiles[j][i].setLast(false);
                    tiles[j][i].repaint();
                    this.repaint();
                    clientGui.repaint();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int xCorrection = (int) ((this.getWidth()-(tiles[0][0].getWidth()*size))/2.0);
        int yCorrection= (int) ((this.getHeight()-(tiles[0][0].getHeight()*size))/2.0);
        double tilesWidth = tiles[0][0].getWidth()*size;
        double tilesHeight = tiles[0][0].getHeight()*size;
        g2d.setPaint(Color.BLACK);
        double xMove = 0;
        double yMove = 0;
        double xStart = this.getWidth() / 2.0f / size + xCorrection;
        double yStart = this.getHeight() / 2.0f / size + yCorrection;
        for (int i = 0; i < size-1; i++) {
            g2d.drawLine((int) (xMove + xStart), (int) yStart, (int) (xMove + xStart), (int) (this.getHeight() - yStart));
            g2d.drawLine((int) xStart, (int) (yMove + yStart), (int) (this.getWidth() - xStart), (int) (yMove + yStart));
            xMove += (double) tiles[0][0].getWidth();
            yMove += (double) tiles[0][0].getHeight();
        }
        g2d.drawLine((int) (this.getWidth() - xStart), (int) yStart, (int) (this.getWidth() - xStart), (int) (this.getHeight() - yStart));
        g2d.drawLine((int) xStart, (int) (this.getHeight() - yStart), (int) (this.getWidth() - xStart), (int) (this.getHeight() - yStart));
    }

    public Intersection[][] getIntersections() {
        return intersections;
    }

    public void setTerritory(TerritoryStates[][] territory, ClientGui clientGui){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[j][i].getTerritory()!=territory[j][i]){
                    tiles[j][i].setTerritory(territory[j][i]);
                    tiles[j][i].repaint();
                    this.repaint();
                    clientGui.repaint();
                }
            }
        }
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
}
