package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;

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

    public RowBuilder withTopHorizontalRow(Symbol zero, Symbol one, Symbol two) {
        int offset = 0;
        this.cells = new Cell[]{
                new Cell(zero, offset),
                new Cell(one, ++offset),
                new Cell(two, ++offset)
        };
        return this;
    }

    public RowBuilder withMiddleHorizontalRow(Symbol three, Symbol four, Symbol five) {
        int offset = NUMBER_OF_CELLS_IN_ROW;
        this.cells = new Cell[]{
                new Cell(three, offset),
                new Cell(four, ++offset),
                new Cell(five, ++offset)
        };
        return this;
    }

    public RowBuilder withBottomHorizontalRow(Symbol six, Symbol seven, Symbol eight) {
        int offset = BOTTOM_ROW_OFFSET;
        this.cells = new Cell[]{
                new Cell(six, offset),
                new Cell(seven, ++offset),
                new Cell(eight, ++offset)};
        return this;
    }

    public RowBuilder withEmptyTopHorizontalRow() {
        int offset = 0;
        this.cells =  new Cell[] {
                new Cell(VACANT, offset),
                new Cell(VACANT, ++offset),
                new Cell(VACANT, ++offset)
        };
        return this;
    }

    public RowBuilder withEmptyMiddleHorizontalRow() {
        int offset = NUMBER_OF_CELLS_IN_ROW;
        this.cells =  new Cell[] {
                new Cell(VACANT, ++offset),
                new Cell(VACANT, ++offset),
                new Cell(VACANT, ++offset)
        };
        return this;
    }

    public RowBuilder withEmptyBottomHorizontalRow() {
        int offset = BOTTOM_ROW_OFFSET;
        this.cells = new Cell[] {
                new Cell(VACANT, ++offset),
                new Cell(VACANT, ++offset),
                new Cell(VACANT, ++offset)
        };
        return this;
    }

    public RowBuilder withLeftVerticalRow(Symbol top, Symbol middle, Symbol bottom) {
        this.cells = new Cell[]{
                new Cell(top, 0),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW),
                new Cell(bottom, 2 * NUMBER_OF_CELLS_IN_ROW)
        };
        return this;
    }


    public Row build() {
        return new Row(cells);
    }
}
