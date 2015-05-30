package tictactoe.player.gameplan;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.X;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

/**
 * Created by Georgina on 30/05/2015.
 */
public class TakeEmptyCornerMoveTest {
    private TakeEmptyCornerMove takeEmptyCornerMove = new TakeEmptyCornerMove();

    @Test
    public void returnsIndexOfEmptyTopLeftCorner() {
        Grid grid = GridFactory.createEmptyGrid();
        assertThat(takeEmptyCornerMove.execute(grid, X), is(0));
    }

    @Test
    public void returnsIndexOfEmptyTopRightCorner() {
        Grid grid = GridFactory.createEmptyGrid();
        occupyCorners(grid, 0);
        assertThat(takeEmptyCornerMove.execute(grid, X), is(2));
    }

    @Test
    public void returnsIndexOfEmptyBottomLeftCorner() {
        Grid grid = GridFactory.createEmptyGrid();
        occupyCorners(grid, 0, 2);
        assertThat(takeEmptyCornerMove.execute(grid, X), is(6));
    }

    @Test
    public void returnsIndexOfEmptyBottomRightCorner() {
        Grid grid = GridFactory.createEmptyGrid();
        occupyCorners(grid, 0, 2, 6);
        assertThat(takeEmptyCornerMove.execute(grid, X), is(8));
    }

    @Test
    public void noSuggestedMoveWhenAllCornersOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        occupyCorners(grid, 0, 2, 6, 8);
        assertThat(takeEmptyCornerMove.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }

    private void occupyCorners(Grid grid, int... cornerIndices) {
        for (int cornerIndex : cornerIndices) {
            grid.update(cornerIndex, X);
        }
    }
}
