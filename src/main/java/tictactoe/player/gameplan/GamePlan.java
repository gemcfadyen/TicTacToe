package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

/**
 * Created by Georgina on 25/05/2015.
 */
public interface GamePlan {
    int NO_SUGGESTED_MOVE = -1;

    int execute(Grid grid, Symbol symbol);
}
