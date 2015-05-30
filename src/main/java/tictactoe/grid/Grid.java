package tictactoe.grid;

import com.google.common.collect.ImmutableMap;
import tictactoe.Symbol;
import tictactoe.grid.status.GameStatus;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Row.FIRST_CELL_INDEX;
import static tictactoe.grid.RowGenerator.generateRowsForAllDirections;
import static tictactoe.grid.RowGenerator.generateVerticalRow;
import static tictactoe.grid.RowGenerator.horizontalRows;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;
    public static final int CENTER = TOTAL_CELLS / 2;

    protected static final int LEFT_CELL_INDEX = 0;

    public static final Map<Integer, Integer> DIAGONAL_OPPOSITE_CORNERS = ImmutableMap.<Integer, Integer>builder()
            .put(LEFT_CELL_INDEX, TOTAL_CELLS - 1)
            .put(NUMBER_OF_CELLS_IN_ROW - 1, BOTTOM_ROW_OFFSET)
            .put(BOTTOM_ROW_OFFSET, NUMBER_OF_CELLS_IN_ROW - 1)
            .put(TOTAL_CELLS - 1, LEFT_CELL_INDEX)
            .build();

    private Row topRow;
    private Row middleRow;
    private Row bottomRow;

    public Grid(Row topRow, Row middleRow, Row bottomRow) {
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

    public boolean isEmptyAt(int offset) {
        Row row = determineRowFrom(offset);
        return row.isVacantAt(offset);
    }

    public boolean isEmpty() {
        boolean allEmpty = true;
        List<Row> horizontalRows = horizontalRows(topRow, middleRow, bottomRow);
        for (Row horizontalRow : horizontalRows) {
            allEmpty = allEmpty && horizontalRow.isVacant();
        }
        return allEmpty;
    }

    public GameStatus getVacantCell() {
        for (int offset = LEFT_CELL_INDEX; offset < TOTAL_CELLS; offset++) {
            if (isEmptyAt(offset)) {
                return GameStatus.potentialMoveAt(offset);
            }
        }
        return GameStatus.noWin();
    }

    public GameStatus evaluateForForksWhenCenterIsOccupied(Symbol symbol) {
        Function<Row, Integer> diagonalOppositeCorner = row -> getOppositeCornerOf(row.getCellOffsetOf(symbol));

        GameStatus status = checkForPotentialForkUsingOppositeCorners(topRow, symbol, diagonalOppositeCorner);
        return status.hasPotentialFork()
                ? status
                : checkForPotentialForkUsingOppositeCorners(bottomRow, symbol, diagonalOppositeCorner);

    }

    private GameStatus evaluateForForksAroundEdgeOfGrid(Row rowToConsider, Symbol symbol) {
        Function<Row, Integer> freeCornerInRow = row -> row.getIndexOfFreeCorner();
        GameStatus gameStatus = checkForPotentialForkUsingOppositeCorners(rowToConsider, symbol, freeCornerInRow);
        if (gameStatus.hasPotentialFork()) {
            return gameStatus;
        }
        return gameStatus;
    }

    public GameStatus evaluateForksFromTopRow(Symbol symbol) {
        return evaluateForForksAroundEdgeOfGrid(topRow, symbol);
    }

    public GameStatus evaluateForksFromBottomRow(Symbol symbol) {
        return evaluateForForksAroundEdgeOfGrid(bottomRow, symbol);
    }

    public GameStatus evaluateForksFromVerticalRows(Symbol symbol) {
        return evaluateForForksAroundEdgeOfGrid(
                generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow),
                symbol);
    }

    public boolean centerCellTaken() {
        return !isEmptyAt(CENTER);
    }

    public void update(int offset, Symbol symbol) {
        Row row = determineRowFrom(offset);
        if (row.getCellWithOffset(offset).getSymbol() == VACANT) {
            row.putSymbolAt(offset, symbol);
        }
    }

    public Symbol getSymbolAtCellWithOffset(int offset) {
        Row row = determineRowFrom(offset);
        return row.getCellWithOffset(offset).getSymbol();
    }

    public GameStatus evaluateWinningStatus() {
        for (Row row : generateRowsForAllDirections(topRow, middleRow, bottomRow)) {
            if (row.isWinningRow()) {
                return GameStatus.winFor(row.getSymbolAt(FIRST_CELL_INDEX));
            }
        }
        return GameStatus.noWin();
    }

    public GameStatus evaluateWinningMoveFor(Symbol symbol) {
        List<Row> rows = generateRowsForAllDirections(topRow, middleRow, bottomRow);
        for (Row row : rows) {
            Cell remainingVacantCell = row.getWinningCellFor(symbol);
            if (isWinningMoveAt(remainingVacantCell)) {
                return GameStatus.potentialMoveAt(remainingVacantCell.getOffset());
            }
        }

        return GameStatus.noWin();
    }

    private int getOppositeCornerOf(int index) {
        return DIAGONAL_OPPOSITE_CORNERS.get(index);
    }

    public List<Row> rows() {
        return horizontalRows(topRow, middleRow, bottomRow);
    }

    private GameStatus checkForPotentialForkUsingOppositeCorners(Row row, Symbol symbol, Function<Row, Integer> function) {
        if (vacantLShapedFormationAround(row, symbol)) {
            return GameStatus.potentialForkAt(function.apply(row));
        }

        return GameStatus.noWin();
    }

    private boolean vacantLShapedFormationAround(Row row, Symbol symbol) {
        return row.freeRowWithOccupiedCorner(symbol) && hasForkFormationInVerticalRows(symbol);
    }

    private boolean hasForkFormationInVerticalRows(Symbol symbol) {
        return checkForkInVertical(generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow), symbol)
                || checkForkInVertical(generateVerticalRow(NUMBER_OF_CELLS_IN_ROW - 1, topRow, middleRow, bottomRow), symbol);
    }

    private boolean checkForkInVertical(Row row, Symbol symbol) {
        return row.isVacant() || row.freeRowWithOccupiedCorner(symbol);
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

    private boolean isWinningMoveAt(Cell remainingVacantCell) {
        return remainingVacantCell != null;
    }

//    private List<Row> generateRowsForAllDirections() {
//        List<Row> allRows = horizontalRows();
//        allRows.addAll(verticalRows());
//        allRows.addAll(diagonalRows());
//
//        return allRows;
//    }
//
//    private List<Row> horizontalRows() {
//        List<Row> rows = new ArrayList<>();
//        rows.add(topRow);
//        rows.add(middleRow);
//        rows.add(bottomRow);
//        return rows;
//    }
//
//    private List<Row> verticalRows() {
//        List<Row> rows = new ArrayList<>();
//        rows.add(generateVerticalRow(LEFT_CELL_INDEX));
//        rows.add(generateVerticalRow(MIDDLE_CELL_INDEX));
//        rows.add(generateVerticalRow(RIGHT_CELL_INDEX));
//        return rows;
//    }
//
//    private List<Row> diagonalRows() {
//        List<Row> rows = new ArrayList<>();
//        rows.add(generateRightDiagonal());
//        rows.add(generateLeftDiagonal());
//        return rows;
//    }
//
//    private Row generateRightDiagonal() {
//        return aRowBuilder().withRightDiagonal(
//                topRow.getSymbolAt(RIGHT_CELL_INDEX),
//                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
//                bottomRow.getSymbolAt(LEFT_CELL_INDEX)).build();
//    }
//
//    private Row generateLeftDiagonal() {
//        return aRowBuilder().withLeftDiagonal(
//                topRow.getSymbolAt(LEFT_CELL_INDEX),
//                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
//                bottomRow.getSymbolAt(RIGHT_CELL_INDEX)).build();
//    }
//
//    private Row generateVerticalRow(int startingOffset) {
//        return aRowBuilder().withVerticalRow(
//                topRow.getSymbolAt(startingOffset),
//                middleRow.getSymbolAt(startingOffset),
//                bottomRow.getSymbolAt(startingOffset),
//                startingOffset).build();
//    }
}

