package tictactoe.grid;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 18/05/2015.
 */
public class GridTest {
    private static final int NO_OFFSET = 0;
    private Grid grid;

    @Before
    public void setup() {
        Row top = aRowBuilder().withHorizontalRow(X, VACANT, X, NO_OFFSET).build();
        Row middle = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottom = aRowBuilder().withHorizontalRow(VACANT, O, X, BOTTOM_ROW_OFFSET).build();

        grid = new Grid(top, middle, bottom);
    }

    @Test
    public void updateGridAtGivenIndex() {
        Grid updatedGrid = grid.update(6, X);

        StringBuffer expectedGrid = new StringBuffer(" | X | 1 | X | \n ");
        expectedGrid.append("| X | 4 | 5 | \n ");
        expectedGrid.append("| X | O | X | \n");

        assertThat(updatedGrid.isEmptyAt(6), is(false));
        assertThat(updatedGrid.display(), is(equalTo(expectedGrid.toString())));
    }

    @Test (expected = IllegalArgumentException.class)
    public void exceptionThrownIfOffsetIsOutOfRange() {
        grid.update(10, X);
    }

    @Test
    public void noUpdateToGridIsMadeWhenCellIsNotVacant() {
        Grid updatedGrid = grid.update(3, X);

        StringBuffer expectedGrid = new StringBuffer(" | X | 1 | X | \n ");
        expectedGrid.append("| X | 4 | 5 | \n ");
        expectedGrid.append("| 6 | O | X | \n");

        assertThat(updatedGrid.display(), is(equalTo(expectedGrid.toString())));
    }

}
