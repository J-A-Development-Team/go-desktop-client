package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Stone;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Tile[][] tiles;
    private int size;
    private int space = 10;

    public Board(int size) {
        this.size = size;
        tiles = new Tile[size][size];
        this.setLayout(new GridLayout(size,size,space,space));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.add(new Tile(new Stone(j,i),j,i));
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        float xMove = 0;
        float yMove = 0;
        float xStart = this.getWidth()/2.0f/size;
        float yStart = this.getHeight()/2.0f/size;
        for (int i = 0; i < size; i++) {
            g2d.drawLine((int) (xMove+xStart), (int) yStart, (int) (xMove+xStart), (int) (this.getHeight()-yStart));
            g2d.drawLine((int) xStart, (int) (yMove+yStart), (int) (this.getWidth()-xStart), (int) (yMove+yStart));
            xMove +=  (float) this.getWidth()/size;
            yMove += (float) this.getHeight()/size;
        }
//        g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());
//        g2d.drawLine(0,this.getHeight()/2,this.getWidth(),this.getHeight()/2);
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getBoardSize() {
        return size;
    }

    public void setBoardSize(int size) {
        this.size = size;
    }

    public void repaintTable(){
        this.removeAll();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j].repaint();
            }
        }
    }

}
