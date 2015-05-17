package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

/**
 * Created by Georgina on 16/05/2015.
 */
public interface Player {
    int nextMoveOn(Grid grid);

    Symbol getSymbol();
}
