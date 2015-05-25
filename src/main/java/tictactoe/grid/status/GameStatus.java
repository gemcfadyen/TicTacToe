package tictactoe.grid.status;

import tictactoe.Symbol;
import tictactoe.grid.State;

import static tictactoe.grid.State.POTENTIAL_FORK;
import static tictactoe.grid.State.WIN;

/**
 * Created by Georgina on 19/05/2015.
 */

public final class GameStatus {
    private Symbol winningSymbol;
    private int indexOfMove;
    private final State state;

    public static GameStatus noWin() {
        return new GameStatus(State.NO_WIN);
    }

    public static GameStatus winFor(Symbol symbol) {
        return new GameStatus(State.WIN, symbol);
    }

    public static GameStatus potentialWinAt(int index) {
        return new GameStatus(State.POTENTIAL_WIN, index);
    }

    public static GameStatus potentialForkAt(int index) {
        return new GameStatus(State.POTENTIAL_FORK, index);
    }

    private GameStatus(State state) {
        this.state = state;
    }

    private GameStatus(State state, Symbol winningSymbol) {
        this.state = state;
        this.winningSymbol = winningSymbol;
    }

    private GameStatus(State state, int indexOfMove) {
        this.state = state;
        this.indexOfMove = indexOfMove;
    }

    public Symbol winningSymbol() {
        return winningSymbol;
    }

    public boolean hasWinner() {
        return state == WIN;
    }

    public boolean hasPotentialWin() {
        return state == State.POTENTIAL_WIN;
    }

    public int getIndexOfMove() {
        return indexOfMove;
    }

    public boolean hasPotentialFork() {
        return state == POTENTIAL_FORK;
    }
}



