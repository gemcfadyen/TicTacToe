package tictactoe.player.gameplan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.grid.Row;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;
import static tictactoe.player.gameplan.GamePlan.NO_WINNING_MOVE;

/**
 * Created by Georgina on 25/05/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class TakeWinningMoveTest {
    private TakeWinningMove takeWinningMove = new TakeWinningMove();
    private Grid grid;

    @Before
    public void setup() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        grid = new Grid(topRow, middleRow, bottomRow);
    }

    @Test
    public void indexOfWinningMoveIsReturned() {
        assertThat(takeWinningMove.execute(grid, X), is(6));
    }

    @Test
    public void noWinningMoveAvailable() {
        assertThat(takeWinningMove.execute(grid, O), is(NO_WINNING_MOVE));
    }
}