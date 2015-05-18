package tictactoe.grid;

import tictactoe.Symbol;

/**
 * Created by Georgina on 17/05/2015.
 */
public class Cell {
    private Symbol symbol;
    private int offset;

    Cell(Symbol symbol, int offset) {
        this.symbol = symbol;
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}