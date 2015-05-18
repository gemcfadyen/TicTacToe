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
                aRowBuilder().withEmptyTopRow().build(),
                aRowBuilder().withEmptyMiddleRow().build(),
                aRowBuilder().withEmptyBottomRow().build());

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

    @Test
    public void gridWhereLeftColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopRow(X, VACANT, VACANT).build();
        Row middleRow = aRowBuilder().withMiddleRow(X, VACANT, VACANT).build();
        Row bottomRow = aRowBuilder().withBottomRow(X, VACANT, VACANT).build();
        Grid gridWithWinningLeftColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningLeftColumn.containsWinningRow(), is(true));
    }

    @Test
    public void gridWhereMiddleColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopRow(VACANT, X, VACANT).build();
        Row middleRow = aRowBuilder().withMiddleRow(VACANT, X, VACANT).build();
        Row bottomRow = aRowBuilder().withBottomRow(VACANT, X, VACANT).build();
        Grid gridWithWinningMiddleColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningMiddleColumn.containsWinningRow(), is(true));
    }


    @Test
    public void gridWhereRightColumnContainsThreeMatchingSymbolsHasWinningRow() {
        Row topRow = aRowBuilder().withTopRow(VACANT, VACANT, X).build();
        Row middleRow = aRowBuilder().withMiddleRow(VACANT, VACANT, X).build();
        Row bottomRow = aRowBuilder().withBottomRow(VACANT, VACANT, X).build();
        Grid gridWithWinningRightColumn = new Grid(topRow, middleRow, bottomRow);

        assertThat(gridWithWinningRightColumn.containsWinningRow(), is(true));
    }

}
