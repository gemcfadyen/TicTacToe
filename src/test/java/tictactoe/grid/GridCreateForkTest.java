package tictactoe.grid;

import org.junit.Test;
import tictactoe.grid.status.GameStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 25/05/2015.
 */
public class GridCreateForkTest {
    @Test
    public void takeOppositeCornerOfTopLeftToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForkFormations(X);

        assertThat(gameStatus.hasPotentialFork(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(8));
    }

    @Test
    public void takeOppositeCornerOfTopRightToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForkFormations(X);

        assertThat(gameStatus.hasPotentialFork(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(6));
    }

    @Test
    public void takeOppositeCornerOfBottomLeftToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForkFormations(X);

        assertThat(gameStatus.hasPotentialFork(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(2));
    }

    @Test
    public void takeOppositeCornerOfBottomRightToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForkFormations(X);

        assertThat(gameStatus.hasPotentialFork(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(0));
    }
}
