package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

/**
 * Created by Georgina on 25/05/2015.
 */
public class CreateForkFormation extends ForkingGamePlan {
    private static final int TOP_LEFT_CORNER = 0;

    @Override
    public int execute(Grid grid, Symbol symbol) {
        return grid.isEmpty()
                ? TOP_LEFT_CORNER
                : evaluateForkFormations(grid, symbol);
    }
}
