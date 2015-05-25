package tictactoe.grid;

import tictactoe.Symbol;
import tictactoe.grid.status.GameStatus;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Row.FIRST_CELL_INDEX;
import static tictactoe.grid.RowBuilder.aRowBuilder;

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
        return row.isVacantAt(index);
    }

    public void update(int index, Symbol symbol) {
        Row row = determineRowFrom(index);
        if (row.getCellWithOffset(index).getSymbol() == VACANT) {
            row.putSymbolAt(index, symbol);
        }
    }

    public GameStatus evaluateWinningStatus() {
        for (Row row : generateRowsForAllDirections()) {
            if (row.isWinningRow()) {
                return GameStatus.winFor(row.getSymbolAt(FIRST_CELL_INDEX));
            }
        }
        return GameStatus.noWin();

    }

    private List<Row> generateRowsForAllDirections() {
        List<Row> allRows = horizontalRows();
        allRows.addAll(verticalRows());
        allRows.addAll(diagonalRows());

        return allRows;
    }

    public List<Row> rows() {
        return horizontalRows();
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

    private Row determineRowFrom(int index) {
        if (index < NUMBER_OF_CELLS_IN_ROW) {
            return topRow;
        }
        if (index <= BOTTOM_ROW_OFFSET - 1) {
            return middleRow;
        }
        return bottomRow;
    }

    public GameStatus evaluateWinningMoveFor(Symbol symbol) {
        List<Row> rows = generateRowsForAllDirections();
        for (Row row : rows) {
            Cell remainingVacantCell = row.getWinningCellFor(symbol);
            if (isWinningMoveAt(remainingVacantCell)) {
                return GameStatus.potentialWinAt(remainingVacantCell.getOffset());
            }
        }

        return GameStatus.noWin();
    }

    private boolean isWinningMoveAt(Cell remainingVacantCell) {
        return remainingVacantCell != null;
    }
}

