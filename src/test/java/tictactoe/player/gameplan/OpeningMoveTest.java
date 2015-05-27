package tictactoe.player.gameplan;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
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

    @Test
    public void noSuggestedMoveIfCornerCellsAreOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        updateGridCornersWithSymbols(grid);
        assertThat(topLeftCornerMove.execute(grid, X), is(GamePlan.NO_SUGGESTED_MOVE));
    }

    private void updateGridCornersWithSymbols(Grid grid) {
        int cornerIndex = 0;
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex += 2, O);
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex, O);
    }


}
