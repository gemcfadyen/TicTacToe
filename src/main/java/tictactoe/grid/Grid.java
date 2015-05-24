package tictactoe.grid;

import tictactoe.Symbol;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Row.FIRST_CELL_INDEX;
import static tictactoe.grid.RowBuilder.aRowBuilder;
import static tictactoe.grid.State.NO_WIN;
import static tictactoe.grid.State.WIN;

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

    protected Grid(Row topRow, Row middleRow, Row bottomRow) {
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

    public boolean isEmptyAt(int index) {
        Row row = determineRowFrom(index);
        return isVacantAt(row, index);
    }

    public Grid update(int index, Symbol symbol) {
        Row row = determineRowFrom(index);
        if (row.getCellWithOffset(index).getSymbol() == VACANT) {
            row.putSymbolAt(index, symbol);
        }
        return this;
    }

    public GameStatus getWinStatus() {
        for (Row row : generateRowsForAllDirections()) {
            if (row.isWinningRow()) {
                return new GameStatus(WIN, row.getSymbolAt(FIRST_CELL_INDEX));
            }
        }
        return new GameStatus(NO_WIN);

    }

    public String display() {
        StringBuffer gridDisplay = prints(topRow);
        gridDisplay.append(prints(middleRow));
        gridDisplay.append(prints(bottomRow));

        return gridDisplay.toString();
    }

    private List<Row> generateRowsForAllDirections() {
        List<Row> allRows = horizontalRows();
        allRows.addAll(verticalRows());
        allRows.addAll(diagonalRows());

        return allRows;
    }

    private List<Row> horizontalRows() {
        List<Row> rows = new ArrayList<>();
        rows.add(topRow);
        rows.add(middleRow);
        rows.add(bottomRow);
        return rows;
    }

    private List<Row> verticalRows() {
        List<Row> rows = new ArrayList<>();
        rows.add(generateVerticalRow(LEFT_CELL_INDEX));
        rows.add(generateVerticalRow(MIDDLE_CELL_INDEX));
        rows.add(generateVerticalRow(RIGHT_CELL_INDEX));
        return rows;
    }

    private List<Row> diagonalRows() {
        List<Row> rows = new ArrayList<>();
        rows.add(generateRightDiagonal());
        rows.add(generateLeftDiagonal());
        return rows;
    }

    private Row generateRightDiagonal() {
        return aRowBuilder().withRightDiagonal(
                topRow.getSymbolAt(RIGHT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(LEFT_CELL_INDEX)).build();
    }

    private Row generateLeftDiagonal() {
        return aRowBuilder().withLeftDiagonal(
                topRow.getSymbolAt(LEFT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(RIGHT_CELL_INDEX)).build();
    }

    private Row generateVerticalRow(int startingOffset) {
        return aRowBuilder().withVerticalRow(
                topRow.getSymbolAt(startingOffset),
                middleRow.getSymbolAt(startingOffset),
                bottomRow.getSymbolAt(startingOffset),
                startingOffset).build();
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
