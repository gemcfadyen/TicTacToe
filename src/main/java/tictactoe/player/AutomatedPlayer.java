package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 25/05/2015.
 */
public class AutomatedPlayer implements Player {
    private static final int NO_WINNING_MOVE = -1;

    private final Symbol symbol;

    public AutomatedPlayer(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        GameStatus status = grid.evaluateWinningMoveFor(symbol);
        if (status.hasPotentialWin()) {
            return status.getWinningIndex();
        }

        GameStatus opponentsStatus = grid.evaluateWinningMoveFor(opponent(symbol));
        if (opponentsStatus.hasPotentialWin()) {
            return opponentsStatus.getWinningIndex();
        }

        return NO_WINNING_MOVE;

    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
