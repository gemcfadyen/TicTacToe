package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

/**
 * Created by Georgina on 25/05/2015.
 */
public abstract class ForkingGamePlan implements GamePlan {

    protected int evaluateForkFormations(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateForkFormations(symbol);
        if (status.hasPotentialFork()) {
            return status.getIndexOfMove();
        }
        return NO_SUGGESTED_MOVE;
    }
}
