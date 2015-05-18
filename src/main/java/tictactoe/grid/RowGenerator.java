package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.Grid.TOTAL_CELLS;

public final class RowGenerator {
    private Cell[] cells;

    private RowGenerator() {

    }

    public static RowGenerator aRowGenerator() {
        return new RowGenerator();
    }

    public RowGenerator withVerticalRow(Symbol top, Symbol middle, Symbol bottom, int startingOffset) {
        this.cells = new Cell[]{
                new Cell(top, startingOffset),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + startingOffset),
                new Cell(bottom, 2 * NUMBER_OF_CELLS_IN_ROW + startingOffset)
        };
        return this;
    }

    public RowGenerator withLeftDiagonal(Symbol top, Symbol middle, Symbol bottom) {
        this.cells = new Cell[]{
                new Cell(top, 0),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + 1),
                new Cell(bottom, TOTAL_CELLS - 1)
        };
        return this;
    }

    public RowGenerator withRightDiagonal(Symbol top, Symbol middle, Symbol bottom) {
        this.cells = new Cell[]{
                new Cell(top, NUMBER_OF_CELLS_IN_ROW - 1),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + 1),
                new Cell(bottom, 2 * NUMBER_OF_CELLS_IN_ROW)
        };
        return this;
    }

    public Row generate() {
        return new Row(cells);
    }
}
