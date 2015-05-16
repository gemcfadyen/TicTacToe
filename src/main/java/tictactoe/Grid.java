package tictactoe;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Grid {
    public static final int NUMBER_OF_CELLS = 9;

    public boolean containsWinningRow() {
        return true;
    }

    public int numberOfCells() {
        return -1;
    }

    public Symbol getWinningSymbol() {
        return null;
    }

    public boolean isEmptyAt(int index) {
        return false;
    }
}
