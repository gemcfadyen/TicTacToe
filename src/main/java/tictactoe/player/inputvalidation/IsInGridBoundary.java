package tictactoe.player.inputvalidation;

import static tictactoe.grid.Grid.TOTAL_CELLS;

/**
 * Created by Georgina on 24/05/2015.
 */
public class IsInGridBoundary implements InputValidator {
    @Override
    public boolean isValid(String move) {
        int cellIndex = Integer.valueOf(move);
        if (cellIndex < 0 || cellIndex >= TOTAL_CELLS) {
            return false;
        }
        return true;
    }
}
