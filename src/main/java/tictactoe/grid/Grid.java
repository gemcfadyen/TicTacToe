package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;
    private static final int LEFT_VERTICAL_INDEX = 0;
    private static final int MIDDLE_VERTICAL_INDEX = 1;
    private static final int RIGHT_VERTICAL_INDEX = 2;

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

    public Symbol getWinningSymbol() {
        return null;
    }

    public Grid update(int index, Symbol symbol) {
        return null;
    }

    private Row generateVerticalRow(int position) {
        return aRowBuilder().withLeftVerticalRow(
                topRow.getSymbolAt(position),
                middleRow.getSymbolAt(position),
                bottomRow.getSymbolAt(position)).build();

    }
    private boolean winningVerticalColumn() {
        Row leftColumn = generateVerticalRow(LEFT_VERTICAL_INDEX);
        Row middleColumn =  generateVerticalRow(MIDDLE_VERTICAL_INDEX);
        Row rightColumn =  generateVerticalRow(RIGHT_VERTICAL_INDEX);

        return leftColumn.isWinningRow()
                || middleColumn.isWinningRow()
                || rightColumn.isWinningRow();
    }

    private boolean winningHorizontalRow() {
        return topRow.isWinningRow()
                || middleRow.isWinningRow()
                || bottomRow.isWinningRow();
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
