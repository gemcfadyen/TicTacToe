package tictactoe.player.gameplan.cornermoves;

import org.hamcrest.Matchers;
import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.cornermoves.TopLeftCornerMove;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

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
        assertThat(topLeftCornerMove.execute(grid, X), Matchers.is(GamePlan.NO_SUGGESTED_MOVE));
    }

    private void updateGridCornersWithSymbols(Grid grid) {
        int cornerIndex = 0;
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex += 2, O);
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex, O);
    }


}
