package tictactoe.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 17/05/2015.
 */
@RunWith(Parameterized.class)
public class GridHasFreeCellAtIndexTest {

    private static final Row EMPTY_TOP_ROW = aRowBuilder().withEmptyTopRow().build();
    private static final Row EMPTY_MIDDLE_ROW = aRowBuilder().withEmptyMiddleRow().build();
    private static final Row EMPTY_BOTTOM_ROW = aRowBuilder().withEmptyBottomRow().build();
    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final int freeCellIndex;

    public GridHasFreeCellAtIndexTest(Row top, Row middle, Row bottom, int index) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.freeCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        EMPTY_TOP_ROW,
                        EMPTY_MIDDLE_ROW,
                        EMPTY_BOTTOM_ROW,
                        0
                },
                {
                        aRowBuilder().withTopRow(VACANT, X, O).build(),
                        EMPTY_MIDDLE_ROW,
                        EMPTY_BOTTOM_ROW,
                        0
                },

                {
                        EMPTY_TOP_ROW,
                        aRowBuilder().withMiddleRow(O, O, VACANT).build(),
                        EMPTY_BOTTOM_ROW,
                        5
                },

                {
                        EMPTY_TOP_ROW,
                        EMPTY_MIDDLE_ROW,
                        aRowBuilder().withBottomRow(X, VACANT, X).build(),
                        7
                }

        });
    }

    @Test
    public void indicatesAGridHasFreeCellAtGivenIndex() {
        Grid gridWithFreeCell = new Grid(topRow, middleRow, bottomRow);
        assertThat(gridWithFreeCell.isEmptyAt(freeCellIndex), is(true));
    }
}
