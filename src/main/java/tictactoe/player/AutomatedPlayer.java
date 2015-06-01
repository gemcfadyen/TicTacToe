package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.prompt.Prompt;


public abstract class AutomatedPlayer implements Player {
    private static final int NO_WINNING_MOVE = -1;

    private final Symbol symbol;
    private final GamePlan[] orderedGamePlan;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
        this.orderedGamePlan = orderedGamePlan();
    }

    @Override
    public int nextMoveOn(Grid grid) {
        for (GamePlan gamePlan : orderedGamePlan) {
            int move = gamePlan.execute(grid, symbol);
            if (move != NO_WINNING_MOVE) {
                prompt.display(symbol, move);
                return move;
            }
        }

        throw new NoMovesAvailableException("No moves available for player");
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    protected abstract GamePlan[] orderedGamePlan();

}

class NoMovesAvailableException extends RuntimeException {

    public NoMovesAvailableException(String message) {
        super(message);
    }
}
