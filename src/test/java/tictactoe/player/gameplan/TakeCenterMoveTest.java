package tictactoe.player.gameplan;

/**
 * Created by Georgina on 27/05/2015.
 */
import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.CENTER;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public class TakeCenterMoveTest {
    private TakeCenterMove takeCenterMove = new TakeCenterMove();

    @Test
    public void takesCenterMove() {
        Grid grid = GridFactory.createEmptyGrid();
        assertThat(takeCenterMove.execute(grid, X), is(CENTER));
    }

    @Test
    public void noSuggestedMoveWhenCenterCellIsOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        grid.update(CENTER, O);
        assertThat(takeCenterMove.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }
}
