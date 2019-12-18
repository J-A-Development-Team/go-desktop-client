package JADevelopmentTeam.client;

import JADevelopmentTeam.common.Intersection;
import JADevelopmentTeam.common.TerritoryStates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class BoardTest {
    Board board;
    Intersection[][] intersections;
    ClientGui clientGui;

    @Before
    public void start() {
        board = new Board(9);
        intersections = new Intersection[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                intersections[j][i] = new Intersection(j, i, false);
            }
        }
        clientGui = mock(ClientGui.class);
    }

    @Test
    public void eraseLastStoneInfo() {
        intersections[0][0] = new Intersection(0, 0, true);
        board.setIntersections(intersections, clientGui);
        board.eraseLastStoneInfo(clientGui);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assert.assertEquals(false, board.tiles[j][i].isLast());
            }
        }
    }

    @Test
    public void setLastStone() {
        Intersection[][] intersections1 = new Intersection[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                intersections1[j][i] = new Intersection(j, i, false);
            }
        }
        intersections1[0][0] = new Intersection(0, 0, true);
        board.setIntersections(intersections1, clientGui);
        board.setLastStone(intersections1[0][0],clientGui);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != 0 && j != 0) {
                    Assert.assertEquals(false, board.tiles[j][i].isLast());
                }
            }
        }
        Assert.assertEquals(true, board.tiles[0][0].isLast());

    }

    @Test
    public void getIntersections() {
        board.setIntersections(intersections,clientGui);
        Assert.assertSame(intersections,board.getIntersections());
    }

    @Test
    public void setTerritory() {
        TerritoryStates[][] territoryStates = new TerritoryStates[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                territoryStates[j][i] = TerritoryStates.Black;
            }
        }
        board.setTerritory(territoryStates, clientGui);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assert.assertEquals(TerritoryStates.Black, board.tiles[j][i].getTerritory());
            }
        }
    }

    @Test
    public void setIntersections() {
        intersections[0][0] = new Intersection(0, 0, true);
        board.setIntersections(intersections, clientGui);
        Assert.assertSame(intersections,board.getIntersections());
    }
}