package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.RowGenerator.aRowGenerator;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;
    private static final int LEFT_CELL_INDEX = 0;
    private static final int MIDDLE_CELL_INDEX = 1;
    private static final int RIGHT_CELL_INDEX = 2;

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
        return winningHorizontalRow()
                || winningVerticalColumn()
                || winningDiagonal();
    }

    public Symbol getWinningSymbol() {
        return null;
    }

    public Grid update(int index, Symbol symbol) {
        Row row = determineRowFrom(index);
        if (row.getCellWithOffset(index).getSymbol() == VACANT) {
            row.putSymbolAt(index, symbol);
        }
        return this;
    }

    private boolean winningDiagonal() {
        return generateLeftDiagonal().isWinningRow()
                || generateRightDiagonal().isWinningRow();
    }

    private Row generateRightDiagonal() {
        return aRowGenerator().withRightDiagonal(
                topRow.getSymbolAt(RIGHT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(LEFT_CELL_INDEX)).generate();
    }

    private Row generateLeftDiagonal() {
        return aRowGenerator().withLeftDiagonal(
                topRow.getSymbolAt(LEFT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(RIGHT_CELL_INDEX)).generate();
    }

    private Row generateVerticalRow(int startingOffset) {
        return aRowGenerator().withVerticalRow(
                topRow.getSymbolAt(startingOffset),
                middleRow.getSymbolAt(startingOffset),
                bottomRow.getSymbolAt(startingOffset),
                startingOffset).generate();
    }

    private boolean winningVerticalColumn() {
        return generateVerticalRow(LEFT_CELL_INDEX).isWinningRow()
                || generateVerticalRow(MIDDLE_CELL_INDEX).isWinningRow()
                || generateVerticalRow(RIGHT_CELL_INDEX).isWinningRow();
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
                return cell.getSymbol() == VACANT;
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

    public String display() {
        StringBuffer gridDisplay = prints(topRow);
        gridDisplay.append(prints(middleRow));
        gridDisplay.append(prints(bottomRow));

        return gridDisplay.toString();
    }

    private StringBuffer prints(Row row) {
        StringBuffer gridDisplay = new StringBuffer();
        gridDisplay.append(" | ");
        for (Cell cell : row.getCells()) {
            if (cell.getSymbol() == VACANT) {
                gridDisplay.append(cell.getOffset());
            } else {
                gridDisplay.append(cell.getSymbol());
            }
            gridDisplay.append(" | ");
        }
        return gridDisplay.append("\n");
    }
}
