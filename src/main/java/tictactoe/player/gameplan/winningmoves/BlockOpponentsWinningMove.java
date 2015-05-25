package tictactoe.player.gameplan.winningmoves;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 25/05/2015.
 */
public class BlockOpponentsWinningMove extends WinningMoveGamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        return super.execute(grid, opponent(symbol));
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}
