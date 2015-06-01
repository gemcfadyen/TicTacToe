package tictactoe.grid;

import com.google.common.collect.ImmutableMap;
import tictactoe.Symbol;
import tictactoe.grid.status.GameStatus;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Row.FIRST_CELL_INDEX;
import static tictactoe.grid.RowGenerator.generateLeftDiagonal;
import static tictactoe.grid.RowGenerator.generateRightDiagonal;
import static tictactoe.grid.RowGenerator.generateRowsForAllDirections;
import static tictactoe.grid.RowGenerator.generateVerticalRow;
import static tictactoe.grid.RowGenerator.horizontalRows;
import static tictactoe.grid.status.GameStatus.noPotentialMove;
import static tictactoe.grid.status.GameStatus.noWin;
import static tictactoe.grid.status.GameStatus.potentialMoveAt;
import static tictactoe.grid.status.GameStatus.winFor;

public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;
    public static final int CENTRE = TOTAL_CELLS / 2;

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

    public GameStatus getVacantCellOnEdge() {
        Function<Integer, Boolean> emptyCellThatIsNotCorner = offset -> isEmptyAt(offset)
                && !DIAGONAL_OPPOSITE_CORNERS.containsKey(offset);
        return getVacantCell(emptyCellThatIsNotCorner);
    }


    public GameStatus getFirstVacantCell() {
        return getVacantCell(offset -> isEmptyAt(offset));
    }

    public GameStatus evaluateForForksWhenCentreIsOccupied(Symbol symbol) {
        Function<Row, Integer> diagonalOppositeCorner = row -> getOppositeCornerOf(row.getCellOffsetOf(symbol));

        GameStatus status = checkForPotentialForkUsingOppositeCorners(topRow, symbol, diagonalOppositeCorner);
        return status.hasPotentialMove()
                ? status
                : checkForPotentialForkUsingOppositeCorners(bottomRow, symbol, diagonalOppositeCorner);

    }

    public GameStatus evaluateForksFromTopRow(Symbol symbol) {
        return checkForPotentialForkUsingOppositeCorners(topRow, symbol, freeCornerFunction());
    }

    public GameStatus evaluateForksFromBottomRow(Symbol symbol) {
        return checkForPotentialForkUsingOppositeCorners(bottomRow, symbol, freeCornerFunction());
    }

    public GameStatus evaluateForksFromVerticalRows(Symbol symbol) {
        return checkForPotentialForkUsingOppositeCorners(generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow), symbol, freeCornerFunction());
    }

    public GameStatus evaluateForksFromDiagonalRows(Symbol symbol) {
        GameStatus gameStatus = checkForPotentialForksUsingDiagonal(generateLeftDiagonal(topRow, middleRow, bottomRow), symbol);

        return gameStatus.hasPotentialMove()
                ? gameStatus
                : checkForPotentialForksUsingDiagonal(generateRightDiagonal(topRow, middleRow, bottomRow), symbol);
    }

    public GameStatus evaluateWinningStatus() {
        for (Row row : generateRowsForAllDirections(topRow, middleRow, bottomRow)) {
            if (row.isWinningRow()) {
                return winFor(row.getSymbolAt(FIRST_CELL_INDEX));
            }
        }
        return noWin();
    }

    public GameStatus evaluateWinningMoveFor(Symbol symbol) {
        List<Row> rows = generateRowsForAllDirections(topRow, middleRow, bottomRow);
        for (Row row : rows) {
            Cell remainingVacantCell = row.getWinningCellFor(symbol);
            if (isWinningMoveAt(remainingVacantCell)) {
                return potentialMoveAt(remainingVacantCell.getOffset());
            }
        }
        return noWin();
    }

    public boolean centreCellTaken() {
        return !isEmptyAt(CENTRE);
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

    public GameStatus evaluateTopRowCornerTraps(Symbol symbol) {
        GameStatus gameStatus = checkForLShapedVacantRowsWithOccupiedCentres(
                topRow,
                generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow),
                symbol,
                LEFT_CELL_INDEX);

        if(!gameStatus.hasPotentialMove()) {
            gameStatus = checkForLShapedVacantRowsWithOccupiedCentres(
                    topRow,
                    generateVerticalRow(NUMBER_OF_CELLS_IN_ROW - 1, topRow, middleRow, bottomRow),
                    symbol,
                    NUMBER_OF_CELLS_IN_ROW - 1);
        }

        return gameStatus;
    }

    public GameStatus evaluateBottomRowCornerTraps(Symbol symbol) {
        GameStatus gameStatus = checkForLShapedVacantRowsWithOccupiedCentres(
                bottomRow,
                generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow),
                symbol,
                BOTTOM_ROW_OFFSET);

        if (!gameStatus.hasPotentialMove()) {
            gameStatus = checkForLShapedVacantRowsWithOccupiedCentres(
                    bottomRow,
                    generateVerticalRow(NUMBER_OF_CELLS_IN_ROW - 1, topRow, middleRow, bottomRow),
                    symbol,
                    TOTAL_CELLS - 1);
        }

        return gameStatus;
    }

    public List<Row> rows() {
        return horizontalRows(topRow, middleRow, bottomRow);
    }

    public void reset() {
        topRow.reset();
        middleRow.reset();
        bottomRow.reset();
    }

    private int getOppositeCornerOf(int index) {
        return DIAGONAL_OPPOSITE_CORNERS.get(index);
    }

    private GameStatus checkForPotentialForkUsingOppositeCorners(Row row, Symbol symbol, Function<Row, Integer> function) {
        if (vacantLShapedFormationAroundVerticalRows(row, symbol)) {

            Integer nextMove = function.apply(row);
            if (isEmptyAt(nextMove)) {
                return potentialMoveAt(nextMove);
            }
        }

        return noPotentialMove();
    }

    private boolean vacantLShapedFormationAroundVerticalRows(Row row, Symbol symbol) {
        return row.freeRowWithOccupiedCorner(symbol) && hasForkFormationInVerticalRows(symbol);
    }

    private boolean hasForkFormationInVerticalRows(Symbol symbol) {
        return checkForkInVertical(generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow), symbol)
                || checkForkInVertical(generateVerticalRow(NUMBER_OF_CELLS_IN_ROW - 1, topRow, middleRow, bottomRow), symbol);
    }

    private GameStatus checkForPotentialForksUsingDiagonal(Row diagonalRow, Symbol symbol) {
        if (vacantLShapedFormationAroundDiagonalRows(symbol, diagonalRow)) {
            return potentialMoveAt(freeCornerFunction().apply(diagonalRow));
        }
        return noPotentialMove();
    }

    private boolean vacantLShapedFormationAroundDiagonalRows(Symbol symbol, Row diagonal) {
        return diagonal.freeRowWithOccupiedCorner(symbol)
                && (hasForkFormationInHorizontalRows(symbol) || hasForkFormationInVerticalRows(symbol));
    }

    private boolean hasForkFormationInHorizontalRows(Symbol symbol) {
        return checkForkInVertical(topRow, symbol)
                || checkForkInVertical(bottomRow, symbol);
    }

    private boolean checkForkInVertical(Row row, Symbol symbol) {
        return row.isVacant() || row.freeRowWithOccupiedCorner(symbol);
    }

    private GameStatus getVacantCell(Function<Integer, Boolean> cellConditions) {
        for (int offset = LEFT_CELL_INDEX; offset < TOTAL_CELLS; offset++) {
            if (cellConditions.apply(offset)) {
                return potentialMoveAt(offset);
            }
        }
        return noPotentialMove();
    }

    private Function<Row, Integer> freeCornerFunction() {
        return Row::getIndexOfFreeCorner;
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

    private boolean midCellOccupied(Row row, Symbol symbol) {
        return row.freeRowWithOccupiedMiddleCell(symbol);
    }

    private GameStatus checkForLShapedVacantRowsWithOccupiedCentres(Row horizontalRow, Row verticalRow, Symbol symbol, int cellIndex) {
        if (midCellOccupied(horizontalRow, symbol) &&
                midCellOccupied(verticalRow, symbol)) {
            Integer nextMove = horizontalRow.getCellWithOffset(cellIndex).getOffset();
            if (isEmptyAt(nextMove)) {
                return potentialMoveAt(nextMove);
            }
        }
        return noPotentialMove();
    }
}

