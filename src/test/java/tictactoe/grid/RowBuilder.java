package tictactoe.grid;

import tictactoe.Symbol;

/**
 * Created by Georgina on 17/05/2015.
 */
public final class RowBuilder {
    private Cell[] cells;

    private RowBuilder() {

    }

    public static RowBuilder aRowBuilder() {
        return new RowBuilder();
    }

    public RowBuilder withHorizontalRow(Symbol zero, Symbol one, Symbol two, int offset) {
        this.cells = new Cell[]{
                new Cell(zero, offset),
                new Cell(one, ++offset),
                new Cell(two, ++offset)
        };
        return this;
    }

    public Row build() {
        return new Row(cells);
    }
}
