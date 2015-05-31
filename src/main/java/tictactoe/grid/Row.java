package tictactoe.grid;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import tictactoe.Symbol;

import java.util.List;

import static com.google.common.collect.Iterables.all;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;

public class Row {
    protected static final int FIRST_CELL_INDEX = 0;
    private static final Cell NO_CELL = null;
    private List<Cell> cells;

    Row(Cell[] cells) {
        this.cells = newArrayList(cells);
    }

    public Symbol getSymbolAt(int index) {
        return cells.get(index).getSymbol();
    }

    public Cell[] getCells() {
        return cells.toArray(new Cell[NUMBER_OF_CELLS_IN_ROW]);
    }

    public boolean isWinningRow() {
        Symbol symbol = getSymbolAt(FIRST_CELL_INDEX);
        return (symbol == VACANT)
                ? false
                : all(cells, checkAllCellsHaveTheSame(symbol));
    }

    public void putSymbolAt(int offset, Symbol symbol) {
        Cell cellToUpdate = getCellWithOffset(offset);
        cellToUpdate.setSymbol(symbol);
    }

    public Cell getCellWithOffset(int offset) {
        Iterable<Cell> cellWithOffset = filter(cells, cell -> cell.getOffset() == offset);
        return getOnlyElement(cellWithOffset);
    }

    public boolean isVacantAt(int offset) {
        Cell cellWithOffset = getCellWithOffset(offset);
        return cellWithOffset.getSymbol() == VACANT;
    }

    public boolean isVacant() {
        return all(cells, checkAllCellsHaveTheSame(VACANT));
    }

    public Cell getWinningCellFor(Symbol symbol) {
        Iterable<Cell> vacantCell = filter(cells, cell -> cell.getSymbol() == VACANT);
        Iterable<Cell> cellsWithSymbol = filter(cells, cell -> cell.getSymbol() == symbol);

        return (size(vacantCell) == 1 && size(cellsWithSymbol) == NUMBER_OF_CELLS_IN_ROW - 1)
                ? getFirst(vacantCell, NO_CELL)
                : NO_CELL;
    }

    private Predicate<Cell> checkAllCellsHaveTheSame(final Symbol firstSymbol) {
        return new Predicate<Cell>() {
            @Override
            public boolean apply(Cell cell) {
                return symbolsMatch(cell);
            }

            private boolean symbolsMatch(Cell cell) {
                return cell.getSymbol() == firstSymbol;
            }

        };
    }

    public boolean freeRowWithOccupiedCorner(Symbol symbol) {
        Iterable<Cell> vacantCell = filter(cells, cell -> cell.getSymbol() == VACANT);
        Iterable<Cell> cellsWithSymbol = filter(cells, cell -> cell.getSymbol() == symbol && cell.isCorner());

        return (size(vacantCell) == NUMBER_OF_CELLS_IN_ROW - 1
                && size(cellsWithSymbol) == 1)
                ? true
                : false;
    }

    public int getCellOffsetOf(Symbol symbol) {
        Iterable<Cell> cellWithSymbol = filter(cells, cell -> cell.getSymbol() == symbol);
        return Iterables.getOnlyElement(cellWithSymbol).getOffset();
    }

    public int getIndexOfFreeCorner() {
        Iterable<Cell> freeCornerCell = filter(cells, cell -> cell.isCorner() && cell.getSymbol() == VACANT);
        return  Iterables.getFirst(freeCornerCell, NO_CELL).getOffset();
    }

    public void reset() {
        for (Cell cell : cells) {
            cell.setSymbol(VACANT);
        }
    }
}