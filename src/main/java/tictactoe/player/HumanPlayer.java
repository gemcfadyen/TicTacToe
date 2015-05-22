package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static tictactoe.grid.Grid.TOTAL_CELLS;

/**
 * Created by Georgina on 16/05/2015.
 */
public class HumanPlayer implements Player {
    private final Prompt prompt;
    private final Symbol symbol;

    public HumanPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        prompt.promptPlayer();
        String nextMove = prompt.readsInput();

        return repromptUntilValid(nextMove, grid);
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    private int repromptUntilValid(String nextMove, Grid grid) {
        String move = nextMove;
        while (!valid(move, grid)) {
            prompt.promptPlayer();
            move = prompt.readsInput();
        }
        return Integer.valueOf(move);
    }

    private boolean valid(String specifiedMove, Grid grid) {
        if (!isNumber(specifiedMove)) {
            return false;
        }

        int cellIndex = Integer.valueOf(specifiedMove);
        if (!insideGridBoundaries(cellIndex)) {
            return false;
        }

        if (!gridHasFreeCellAt(cellIndex, grid)) {
            return false;
        }
        return true;
    }

    private boolean gridHasFreeCellAt(int specifiedMove, Grid grid) {
        return grid.isEmptyAt(specifiedMove);
    }

    private boolean insideGridBoundaries(int specifiedMove) {
        if (specifiedMove < 0 || specifiedMove >= TOTAL_CELLS) {
            return false;
        }
        return true;
    }
}