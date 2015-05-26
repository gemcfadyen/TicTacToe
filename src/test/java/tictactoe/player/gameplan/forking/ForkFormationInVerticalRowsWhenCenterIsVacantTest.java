package tictactoe.player.gameplan.forking;

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

/**
 * Created by Georgina on 26/05/2015.
 */
public class ForkFormationInVerticalRowsWhenCenterIsVacantTest {
   private ForkFormationInVerticalRowsWhenCenterIsVacant fork = new ForkFormationInVerticalRowsWhenCenterIsVacant();

    // X O -
    // -
    // * - -
    @Test
    public void whenCentreIsNotTakenAndBottomHorizontalIsVacantStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(X, O, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(6));
    }

    // * - O
    // - - -
    // X - -
    @Test
    public void whenCentreIsNotTakenAndRightVerticalCornerIsVacantStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(0));
    }

    // - - -
    // - O -
    // X - -
    @Test
    public void doesNotFindForkIfCenterCellIsTaken() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(-1));
    }
}
