package tictactoe.player.gameplan.winningmoves;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

public abstract class WinningMoveGamePlan implements GamePlan {

    public int execute(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateWinningMoveFor(symbol);
        if (status.hasPotentialMove()) {
            return status.getIndexOfMove();
        }
        return NO_SUGGESTED_MOVE;
    }
}
