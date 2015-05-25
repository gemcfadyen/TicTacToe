package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

/**
 * Created by Georgina on 25/05/2015.
 */
public class CreateFork implements GamePlan {
    private static final int TOP_LEFT_CORNER = 0;

    @Override
    public int execute(Grid grid, Symbol symbol) {
        return grid.isEmpty()
                ? TOP_LEFT_CORNER
                : evaluateForkFormations(grid, symbol);
    }

    private int evaluateForkFormations(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateForkFormations(symbol);
        if (status.hasPotentialFork()) {
            return status.getIndexOfMove();
        }

        return NO_WINNING_MOVE;
    }
}
