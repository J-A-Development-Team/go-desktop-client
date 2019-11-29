package JADevelopmentTeam.client;

import java.util.ArrayList;

public class Board {
    static ArrayList<Tile> tiles;
    static int size;

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    public static void setTiles(ArrayList<Tile> tiles) {
        Board.tiles = tiles;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Board.size = size;
    }
}
