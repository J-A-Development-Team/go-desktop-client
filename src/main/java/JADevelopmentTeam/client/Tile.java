package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Stone;

public class Tile {
    private Stone stone;

    public Tile(Stone stone) {
        this.stone = stone;
    }

    public Tile(){
        this.stone = null;
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }
    public void removeStone(){
        this.stone = null;
    }
}
