package tictactoe.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 17/05/2015.
 */
@RunWith(Parameterized.class)
public class GridHasNoFreeCellAtIndexTest {

    private static final Row EMPTY_TOP_ROW = aRowBuilder().withEmptyTopHorizontalRow().build();
    private static final Row EMPTY_MIDDLE_ROW = aRowBuilder().withEmptyMiddleHorizontalRow().build();
    private static final Row EMPTY_BOTTOM_ROW = aRowBuilder().withEmptyBottomHorizontalRow().build();
    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final int occupiedCellIndex;

    public GridHasNoFreeCellAtIndexTest(Row top, Row middle, Row bottom, int index) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.occupiedCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withTopHorizontalRow(X, O, X).build(),
                        EMPTY_MIDDLE_ROW,
                        EMPTY_BOTTOM_ROW,
                        2
                },
                {
                        EMPTY_TOP_ROW,
                        aRowBuilder().withMiddleHorizontalRow(X, O, X).build(),
                        EMPTY_BOTTOM_ROW,
                        4
                },
                {
                        EMPTY_TOP_ROW,
                        EMPTY_MIDDLE_ROW,
                        aRowBuilder().withBottomHorizontalRow(X, O, X).build(),
                        8
                }

        });
    }

    @Test
    public void indicatesAGridHasNoFreeCellAtSpecifiedIndex() {
        Grid gridWithOccupiedCell = new Grid(topRow, middleRow, bottomRow);
        assertThat(gridWithOccupiedCell.isEmptyAt(occupiedCellIndex), is(false));
    }
}
