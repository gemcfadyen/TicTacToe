package tictactoe.grid;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;

public class RowTest {
    private static final int NO_OFFSET = 0;

    @Test
    public void identifiesAWinningRow() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, O, O, NO_OFFSET).build();
        assertThat(row.isWinningRow(), is(true));
    }

    @Test
    public void identifiesARowIsNotAWinningRow() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isWinningRow(), is(false));
    }

    @Test
    public void identifiesARowIsAllVacant() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.isVacant(), is(true));
    }

    @Test
    public void identifiesARowIsNotAllVacant() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.isVacant(), is(false));
    }

    @Test
    public void identifiesIfPositionIsVacant() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isVacantAt(1), is(true));
    }

    @Test
    public void identifiesTheChosenPositionIsNotVacant() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isVacantAt(0), is(false));
    }

    @Test
    public void identifiesRowWithTopLeftOccupiedCornerOnly() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.freeRowWithOccupiedCorner(O), is(true));
    }

    @Test
    public void identifiesRowWithTopRightOccupiedCornerOnly() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NO_OFFSET).build();
        assertThat(row.freeRowWithOccupiedCorner(O), is(true));
    }

    @Test
    public void identifiesRowWithBottomLeftOccupiedCornerOnly() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        assertThat(row.freeRowWithOccupiedCorner(O), is(true));
    }

    @Test
    public void identifiesRowWithBottomRightOccupiedCornerOnly() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build();
        assertThat(row.freeRowWithOccupiedCorner(O), is(true));
    }

    @Test
    public void identifiesTheIndexOfAGivenSymbol() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build();
        assertThat(row.getCellOffsetOf(O), is(8));
    }

    @Test
    public void identifiesThereIsNoWinningMoveToBeMade() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(X, O, O, BOTTOM_ROW_OFFSET).build();
        assertNull(row.getWinningCellFor(O));
    }

    @Test
    public void identifiesTheCellToMakeAWinningMove() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        Cell winningCell = row.getWinningCellFor(O);
        assertThat(winningCell.getOffset(), is(0));
    }

    @Test
    public void putsSymbolAtGivenIndex() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        row.putSymbolAt(0, O);

        assertThat(row.getSymbolAt(0), is(O));
    }

    @Test
    public void getsIndexOfFreeCorner() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        assertThat(row.getIndexOfFreeCorner(), is(0));
    }

    @Test
    public void noFreeCornerCells() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(X, O, O, NO_OFFSET).build();
        assertThat(row.getIndexOfFreeCorner(), is(-1));
    }

    @Test
    public void resetsRowToEmpty() {
        Row row = RowBuilder.aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();

        row.reset();

        assertThat(row.getSymbolAt(1), is(VACANT));
        assertThat(row.getSymbolAt(2), is(VACANT));
    }
}