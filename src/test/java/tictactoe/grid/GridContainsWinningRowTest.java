package tictactoe.grid;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 17/05/2015.
 */
public class GridContainsWinningRowTest {

    @Test
    public void emptyGridContainsNoWinningRow() {
        Grid emptyGrid = new Grid();
        assertThat(emptyGrid.containsWinningRow(), is(false));
    }

    @Test
    public void gridWhereTopRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopRow(X, X, X).build();
        Row middleRow = aRowBuilder().withEmptyMiddleRow().build();
        Row bottomRow = aRowBuilder().withEmptyBottomRow().build();
        Grid gridWithWinningTopRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningTopRow.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereMiddleRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withEmptyTopRow().build();
        Row middleRow = aRowBuilder().withMiddleRow(O, O, O).build();
        Row bottomRow = aRowBuilder().withEmptyBottomRow().build();
        Grid gridWithWinningMiddleRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningMiddleRow.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereBottomRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withEmptyTopRow().build();
        Row middleRow = aRowBuilder().withEmptyMiddleRow().build();
        Row bottomRow = aRowBuilder().withBottomRow(X, X, X).build();
        Grid gridWithWinningBottomRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningBottomRow.containsWinningRow(), is(true));
    }

}
