package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.Symbol.VACANT;

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

    public RowBuilder withTopRow(Symbol zero, Symbol one, Symbol two) {
        this.cells = new Cell[]{
                new Cell(zero, 0),
                new Cell(one, 1),
                new Cell(two, 2)
        };
        return this;
    }

    public RowBuilder withMiddleRow(Symbol three, Symbol four, Symbol five) {
        this.cells = new Cell[]{
                new Cell(three, 3),
                new Cell(four, 4),
                new Cell(five, 5)
        };
        return this;
    }

    public RowBuilder withBottomRow(Symbol six, Symbol seven, Symbol eight) {
        this.cells = new Cell[]{
                new Cell(six, 6),
                new Cell(seven, 7),
                new Cell(eight, 8)};
        return this;
    }

    public RowBuilder withEmptyTopRow() {
        this.cells =  new Cell[] {
                new Cell(VACANT, 0),
                new Cell(VACANT, 1),
                new Cell(VACANT, 2)
        };
        return this;
    }

    public RowBuilder withEmptyMiddleRow() {
        this.cells =  new Cell[] {
                new Cell(VACANT, 3),
                new Cell(VACANT, 4),
                new Cell(VACANT, 5)
        };
        return this;
    }

    public RowBuilder withEmptyBottomRow() {
        this.cells = new Cell[] {
                new Cell(VACANT, 6),
                new Cell(VACANT, 7),
                new Cell(VACANT, 8)
        };
        return this;
    }


    public Row build() {
        return new Row(cells);
    }

}
