package tictactoe.player.gameplan.winningmoves;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

/**
 * Created by Georgina on 25/05/2015.
 */
public abstract class WinningMoveGamePlan implements GamePlan {

    public int execute(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateWinningMoveFor(symbol);
        if (status.hasPotentialWin()) {
            return status.getIndexOfMove();
        }
        return NO_SUGGESTED_MOVE;
    }
}
