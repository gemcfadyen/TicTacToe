package tictactoe.player.gameplan;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 26/05/2015.
 */
public class OpeningMoveTest {
    private TopLeftCornerMove topLeftCornerMove = new TopLeftCornerMove();

    @Test
    public void takeCornerOnEmptyGrid() {
        Grid grid = GridFactory.createEmptyGrid();
        assertThat(topLeftCornerMove.execute(grid, X), is(0));
    }

}
