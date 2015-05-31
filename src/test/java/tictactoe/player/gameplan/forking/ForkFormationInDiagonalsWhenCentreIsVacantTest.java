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

public class ForkFormationInDiagonalsWhenCentreIsVacantTest {
    private ForkFormationInDiagonalsWhenCentreIsVacant fork = new ForkFormationInDiagonalsWhenCentreIsVacant();

    @Test
    public void forkFormationAroundLeftDiagonalOnBottomRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(8));
    }

    @Test
    public void forkFormationAroundLeftDiagonalOnTopRow() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(O, VACANT, X, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(0));
    }

    @Test
    public void forkFormationAroundRightDiagonalOnBottomRow() {
        Row topRow = aRowBuilder().withHorizontalRow(O, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(6));
    }

    @Test
    public void forkFormationAroundRightDiagonalOnTopRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(fork.execute(grid, X), is(2));
    }

}
