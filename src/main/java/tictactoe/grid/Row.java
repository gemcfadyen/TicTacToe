package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;

/**
 * Created by Georgina on 17/05/2015.
 */
class Row {
    private Cell[] cells = new Cell[NUMBER_OF_CELLS_IN_ROW];

    Row(Cell[] cells) {
        this.cells = cells.clone();
    }

    public Symbol getSymbolAt(int index) {
        return cells[index].getSymbol();
    }

    public boolean isWinningRow() {
        Symbol firstSymbol = getSymbolOfFirstCell(0);
        for (Cell cell: cells) {
            if (!symbolsAllMatch(firstSymbol, cell.getSymbol())) {
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

    public Symbol getSymbolOfFirstCell(int index) {
        return cells[index].getSymbol();
    }

    public Cell[] getCells() {
        return cells;
    }
}