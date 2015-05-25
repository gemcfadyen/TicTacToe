package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

/**
 * Created by Georgina on 25/05/2015.
 */
public class TakeWinningMove implements GamePlan {

    public int execute(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateWinningMoveFor(symbol);
        if (status.hasPotentialWin()) {
            return status.getWinningIndex();
        }
        return NO_WINNING_MOVE;
    }
}
