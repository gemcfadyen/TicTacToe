package tictactoe.player.gameplan;

/**
 * Created by Georgina on 27/05/2015.
 */
import tictactoe.Symbol;
import tictactoe.grid.Grid;

import static tictactoe.grid.Grid.CENTER;

public class TakeCenterMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        return grid.centerCellTaken()
                ? NO_SUGGESTED_MOVE
                : CENTER;
    }
}
