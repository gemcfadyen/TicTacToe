package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 25/05/2015.
 */
public class BlockOpponentsWinningMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        GameStatus opponentsStatus = grid.evaluateWinningMoveFor(opponent(symbol));
        if (opponentsStatus.hasPotentialWin()) {
            return opponentsStatus.getIndexOfMove();
        }
        return NO_WINNING_MOVE;
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}
