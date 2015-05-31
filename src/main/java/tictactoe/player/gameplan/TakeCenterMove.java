package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.GamePlan;

import static tictactoe.grid.Grid.CENTER;

public class TakeCenterMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        return grid.centerCellTaken()
                ? NO_SUGGESTED_MOVE
                : CENTER;
    }
}
