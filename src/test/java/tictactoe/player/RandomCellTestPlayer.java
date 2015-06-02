package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

import java.util.Random;

import static tictactoe.grid.Grid.TOTAL_CELLS;

public class RandomCellTestPlayer implements Player {
    private Symbol symbol;
    private final Random randomNumberGenerator;

    public RandomCellTestPlayer(Symbol symbol) {
        randomNumberGenerator = new Random();
        this.symbol = symbol;
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public int nextMoveOn(Grid grid) {
        int randomCellOffset = generateRandomCellOffsetWithinRange();
        randomCellOffset = regenerateUntilVacantCellFound(grid, randomCellOffset);

        System.out.println("Random Test Player Generated Cell Offset: [" + randomCellOffset + "]");
        return randomCellOffset;
    }

    private int regenerateUntilVacantCellFound(Grid grid, int randomCellOffset) {
        while (!grid.isEmptyAt(randomCellOffset)) {
            randomCellOffset = generateRandomCellOffsetWithinRange();
        }
        return randomCellOffset;
    }

    private int generateRandomCellOffsetWithinRange() {
        return randomNumberGenerator.nextInt(TOTAL_CELLS);
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
