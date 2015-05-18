package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {

    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;

    private Row topRow;
    private Row middleRow;
    private Row bottomRow;


    public Grid(Row topRow, Row middleRow, Row bottomRow) {
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

    public boolean isEmptyAt(int index) {
        Row row = determineRowFrom(index);
        return isVacantAt(row, index);
    }

    public boolean containsWinningRow() {
        if (winningHorizontalRow()) {
            return true;
        }
        if (winningVerticalColumn()) {
            return true;
        }
        return false;
    }

    private boolean winningVerticalColumn() {
        Row leftColumn = new Row(
                new Cell[]{
                        new Cell(topRow.getSymbolAt(0), 0),
                        new Cell(middleRow.getSymbolAt(0), NUMBER_OF_CELLS_IN_ROW),
                        new Cell(bottomRow.getSymbolAt(0), 2 * NUMBER_OF_CELLS_IN_ROW)
                }
        );

        Row middleColumn = new Row(new Cell[]{
                new Cell(topRow.getSymbolAt(1), 0),
                new Cell(middleRow.getSymbolAt(1), NUMBER_OF_CELLS_IN_ROW),
                new Cell(bottomRow.getSymbolAt(1), 2 * NUMBER_OF_CELLS_IN_ROW)
        }
        );

        Row rightColumn = new Row(new Cell[]{
                new Cell(topRow.getSymbolAt(2), 0),
                new Cell(middleRow.getSymbolAt(2), NUMBER_OF_CELLS_IN_ROW),
                new Cell(bottomRow.getSymbolAt(2), 2 * NUMBER_OF_CELLS_IN_ROW)
        }
        );
        return leftColumn.isWinningRow()
                || middleColumn.isWinningRow()
                || rightColumn.isWinningRow();
    }

    private boolean winningHorizontalRow() {
        return topRow.isWinningRow()
                || middleRow.isWinningRow()
                || bottomRow.isWinningRow();
    }

    public Symbol getWinningSymbol() {
        return null;
    }

    public Grid update(int index, Symbol symbol) {
        return null;
    }

    private boolean isVacantAt(Row row, int index) {
        Cell[] cells = row.getCells();
        for (Cell cell : cells) {
            if (cell.getOffset() == index) {
                return  cell.getSymbol() == VACANT;
            }
        }

        return false;
    }

    private Row determineRowFrom(int index) {
        if (index < NUMBER_OF_CELLS_IN_ROW) {
            return topRow;
        }
        if (index <= BOTTOM_ROW_OFFSET - 1) {
            return middleRow;
        }
        return bottomRow;
    }
}
