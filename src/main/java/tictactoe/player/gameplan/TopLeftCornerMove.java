package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

/**
 * Created by Georgina on 26/05/2015.
 */
public class TopLeftCornerMove implements GamePlan {
    private static final int TOP_LEFT_CORNER = 0;

    @Override
    public int execute(Grid grid, Symbol symbol) {
        if (grid.isEmpty()) {
            return TOP_LEFT_CORNER;
        }
        return NO_SUGGESTED_MOVE;
    }
}
