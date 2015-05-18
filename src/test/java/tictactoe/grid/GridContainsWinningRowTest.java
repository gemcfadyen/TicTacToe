package tictactoe.grid;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 17/05/2015.
 */
public class GridContainsWinningRowTest {

    @Test
    public void emptyGridContainsNoWinningRow() {
        Grid emptyGrid = new Grid(
                aRowBuilder().withEmptyTopHorizontalRow().build(),
                aRowBuilder().withEmptyMiddleHorizontalRow().build(),
                aRowBuilder().withEmptyBottomHorizontalRow().build());

        assertThat(emptyGrid.containsWinningRow(), is(false));
    }

    @Test
    public void gridWhereTopRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopHorizontalRow(X, X, X).build();
        Row middleRow = aRowBuilder().withEmptyMiddleHorizontalRow().build();
        Row bottomRow = aRowBuilder().withEmptyBottomHorizontalRow().build();
        Grid gridWithWinningTopRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningTopRow.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereMiddleRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withEmptyTopHorizontalRow().build();
        Row middleRow = aRowBuilder().withMiddleHorizontalRow(O, O, O).build();
        Row bottomRow = aRowBuilder().withEmptyBottomHorizontalRow().build();
        Grid gridWithWinningMiddleRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningMiddleRow.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereBottomRowContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withEmptyTopHorizontalRow().build();
        Row middleRow = aRowBuilder().withEmptyMiddleHorizontalRow().build();
        Row bottomRow = aRowBuilder().withBottomHorizontalRow(X, X, X).build();
        Grid gridWithWinningBottomRow = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningBottomRow.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereLeftColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopHorizontalRow(X, VACANT, VACANT).build();
        Row middleRow = aRowBuilder().withMiddleHorizontalRow(X, VACANT, VACANT).build();
        Row bottomRow = aRowBuilder().withBottomHorizontalRow(X, VACANT, VACANT).build();
        Grid gridWithWinningLeftColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningLeftColumn.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereMiddleColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopHorizontalRow(VACANT, X, VACANT).build();
        Row middleRow = aRowBuilder().withMiddleHorizontalRow(VACANT, X, VACANT).build();
        Row bottomRow = aRowBuilder().withBottomHorizontalRow(VACANT, X, VACANT).build();
        Grid gridWithWinningMiddleColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningMiddleColumn.containsWinningRow(), is(true));
    }


    @Test
    public void gridWhereRightColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopHorizontalRow(VACANT, VACANT, X).build();
        Row middleRow = aRowBuilder().withMiddleHorizontalRow(VACANT, VACANT, X).build();
        Row bottomRow = aRowBuilder().withBottomHorizontalRow(VACANT, VACANT, X).build();
        Grid gridWithWinningRightColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningRightColumn.containsWinningRow(), is(true));
    }

}
