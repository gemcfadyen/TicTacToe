package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

/**
 * Created by Georgina on 26/05/2015.
 */
public class ForkFormationFromBottomRowWhenCenterIsVacant implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        if (!grid.centerCellTaken()) {
            GameStatus gameStatus = grid.evaluateForksFromBottomRow(symbol);
            if (gameStatus.hasPotentialMove()) {
                return gameStatus.getIndexOfMove();
            }
        }
        return NO_SUGGESTED_MOVE;
    }
}
