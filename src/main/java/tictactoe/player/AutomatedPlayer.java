package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

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

        return status.hasPotentialWin()
                ? status.getWinningIndex()
                : NO_WINNING_MOVE;
        }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
