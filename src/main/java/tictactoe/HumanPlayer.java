package tictactoe;

import static tictactoe.Grid.NUMBER_OF_CELLS;

/**
 * Created by Georgina on 16/05/2015.
 */
public class HumanPlayer implements Player {
    private final Prompt prompt;

    public HumanPlayer(Prompt prompt) {
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        prompt.promptPlayer();
        int nextMove = prompt.readsInput();

        return repromptUntilValid(nextMove, grid);
    }

    private int repromptUntilValid(int nextMove, Grid grid) {
        int move = nextMove;
        while (!valid(move, grid)) {
            prompt.promptPlayer();
            move = prompt.readsInput();
        }
        return move;
    }

    private boolean valid(int specifiedMove, Grid grid) {
        if (!insideGridBoundaries(specifiedMove)) {
            return false;
        }

        if (!gridHasFreeCellAt(specifiedMove, grid)) {
            return false;
        }
        return true;
    }

    private boolean gridHasFreeCellAt(int specifiedMove, Grid grid) {
        return grid.isEmptyAt(specifiedMove);
    }

    private boolean insideGridBoundaries(int specifiedMove) {
        if (specifiedMove < 0 || specifiedMove >= NUMBER_OF_CELLS) {
            return false;
        }
        return true;
    }
}