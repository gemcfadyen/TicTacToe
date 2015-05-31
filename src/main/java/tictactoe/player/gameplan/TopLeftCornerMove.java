package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

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
