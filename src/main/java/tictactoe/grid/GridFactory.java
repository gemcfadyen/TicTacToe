package tictactoe.grid;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

/**
 * Created by Georgina on 24/05/2015.
 */
public class GridFactory {
    private static final int STARTING_INDEX = 0;

    public static Grid createEmptyGrid() {
        Row emptyTopRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, STARTING_INDEX).build();
        Row emptyMiddleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row emptyBottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        return new Grid(emptyTopRow, emptyMiddleRow, emptyBottomRow);
    }
}
