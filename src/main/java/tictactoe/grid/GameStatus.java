package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.grid.State.WIN;

/**
 * Created by Georgina on 19/05/2015.
 */

public class GameStatus {
    private final State state;
    private Symbol winningSymbol;

    public GameStatus(State state) {
        this.state = state;
    }

    public GameStatus(State state, Symbol winningSymbol) {
        this.state = state;
        this.winningSymbol = winningSymbol;
    }

    public Symbol winningSymbol() {
        return winningSymbol;
    }

    public boolean hasWinner() {
        return state == WIN;
    }
}

