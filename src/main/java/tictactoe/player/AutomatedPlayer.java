package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.winningmoves.BlockOpponentsWinningMove;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;

/**
 * Created by Georgina on 25/05/2015.
 */
public class AutomatedPlayer implements Player {
    private static final int NO_WINNING_MOVE = -1;

    private final Symbol symbol;
    private final GamePlan[] orderedGamePlan;

    public AutomatedPlayer(Symbol symbol) {
        this.symbol = symbol;
        this.orderedGamePlan = orderedGamePlan();
    }

    @Override
    public int nextMoveOn(Grid grid) {
        for (GamePlan gamePlan : orderedGamePlan) {
            int move = gamePlan.execute(grid, symbol);
            if (move != NO_WINNING_MOVE) {
                return move;
            }
        }

       return NO_WINNING_MOVE;
    }

    private GamePlan[] orderedGamePlan() {
        return new GamePlan[] {
                    new TakeWinningMove(),
                    new BlockOpponentsWinningMove() };
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
