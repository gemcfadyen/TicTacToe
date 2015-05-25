package tictactoe.player.gameplan;

import org.junit.Before;
import org.junit.Test;
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
public class BlockOpponentsWinningMoveTest {
    private BlockOpponentsWinningMove blockOpponentsWinningMove = new BlockOpponentsWinningMove();
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
        assertThat(blockOpponentsWinningMove.execute(grid, X), is(NO_WINNING_MOVE));
    }

    @Test
    public void noWinningMoveAvailable() {
        assertThat(blockOpponentsWinningMove.execute(grid, O), is(6));
    }

}