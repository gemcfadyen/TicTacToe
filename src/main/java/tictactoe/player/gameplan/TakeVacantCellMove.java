package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

public class TakeVacantCellMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        GameStatus gameStatus = grid.getVacantCell();

        return gameStatus.hasPotentialMove()
                ? gameStatus.getIndexOfMove()
                : NO_SUGGESTED_MOVE;
    }
}