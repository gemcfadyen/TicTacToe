package tictactoe.player.inputvalidation;

import tictactoe.grid.Grid;

/**
 * Created by Georgina on 24/05/2015.
 */
public class IsVacantCell implements InputValidator {
    private final Grid grid;

    public IsVacantCell(Grid grid) {
        this.grid = grid;
    }

    @Override
    public boolean isValid(String move) {
        int cellIndex = Integer.valueOf(move);
        return grid.isEmptyAt(cellIndex);
    }
}
