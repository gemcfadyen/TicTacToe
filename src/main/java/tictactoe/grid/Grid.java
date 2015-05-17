package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {

    private static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int TOP_ROW_OFFSET = 0;
    public static final int MIDDLE_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;

    private Row topRow = new Row(TOP_ROW_OFFSET);
    private Row middleRow = new Row(MIDDLE_ROW_OFFSET);
    private Row bottomRow = new Row(BOTTOM_ROW_OFFSET);

    public Grid() {

    }

    public Grid(Row topRow, Row middleRow, Row bottomRow) {
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

    public boolean isEmptyAt(int index) {
        Row row = determineRowFrom(index);
        return isVacantAt(row, index - row.getOffset());
    }

    public boolean containsWinningRow() {
        if (winningHorizontalRow()) {
            return true;
        }
        return false;
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
        return row.getSymbolAt(index) == VACANT;
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

class Row {
    private int offset = 0;
    private Symbol[] row = new Symbol[]{VACANT, VACANT, VACANT};

    Row(int offset) {
        this.offset = offset;
    }

    Row(int offset, Symbol[] symbols) {
        this.offset = offset;
        this.row = symbols.clone();
    }

    public int getOffset() {
        return offset;
    }

    public Symbol getSymbolAt(int index) {
        return row[index];
    }

    public boolean isWinningRow() {
        Symbol firstSymbol = getSymbolAtFirstCell();
        for (Symbol symbol : row) {
            if (!symbolsAllMatch(firstSymbol, symbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean symbolsAllMatch(Symbol firstSymbol, Symbol symbol) {
        if (isVacant(firstSymbol) || !symbolsMatch(symbol, firstSymbol)) {
            return false;
        }
        return true;
    }

    private boolean symbolsMatch(Symbol symbolToMatch, Symbol currentSymbol) {
        return symbolToMatch.equals(currentSymbol);
    }

    private boolean isVacant(Symbol playersSymbol) {
        return playersSymbol == VACANT;
    }

    private Symbol getSymbolAtFirstCell() {
        return row[0];
    }
}
