package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.MIDDLE_ROW_OFFSET;
import static tictactoe.grid.Grid.TOP_ROW_OFFSET;

/**
 * Created by Georgina on 17/05/2015.
 */
public final class RowBuilder {
    private int offset;
    private Symbol[] rowSymbols;

    private RowBuilder() {

    }

    public static RowBuilder aRowBuilder() {
        return new RowBuilder();
    }

    public RowBuilder withTopRow(Symbol zero, Symbol one, Symbol two) {
        this.offset = TOP_ROW_OFFSET;
        this.rowSymbols = new Symbol[] {zero, one, two};
        return this;
    }

    public RowBuilder withMiddleRow(Symbol three, Symbol four, Symbol five) {
        this.offset = MIDDLE_ROW_OFFSET;
        this.rowSymbols = new Symbol[] {three, four, five};
        return this;
    }

    public RowBuilder withBottomRow(Symbol six, Symbol seven, Symbol eight) {
        this.offset = BOTTOM_ROW_OFFSET;
        this.rowSymbols = new Symbol[] {six, seven, eight};
        return this;
    }

    public RowBuilder withEmptyTopRow() {
        this.offset = TOP_ROW_OFFSET;
        this.rowSymbols = emptyRow();
        return this;
    }

    public RowBuilder withEmptyMiddleRow() {
        this.offset = MIDDLE_ROW_OFFSET;
        this.rowSymbols = emptyRow();
        return this;
    }

    public RowBuilder withEmptyBottomRow() {
        this.offset = BOTTOM_ROW_OFFSET;
        this.rowSymbols = emptyRow();
        return this;
    }

    private Symbol[] emptyRow() {
       return new Symbol[] {VACANT, VACANT, VACANT};
    }

    public Row build() {
        return new Row(offset, rowSymbols);
    }

}
