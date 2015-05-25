package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.grid.Grid.CORNERS_AND_THEIR_OPPOSITES;

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

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isCorner() {
        return CORNERS_AND_THEIR_OPPOSITES.containsKey(offset);
    }
}