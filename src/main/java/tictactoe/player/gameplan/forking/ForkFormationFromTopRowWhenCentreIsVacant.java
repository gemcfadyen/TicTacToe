package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

public class ForkFormationFromTopRowWhenCentreIsVacant implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        if (!grid.centreCellTaken()) {
            GameStatus gameStatus = grid.evaluateForksFromTopRow(symbol);
            if (gameStatus.hasPotentialMove()) {
                return gameStatus.getIndexOfMove();
            }
        }
        return NO_SUGGESTED_MOVE;
    }
}
