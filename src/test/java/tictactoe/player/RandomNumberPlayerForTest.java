package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

import java.util.Random;

import static tictactoe.grid.Grid.TOTAL_CELLS;

public class RandomNumberPlayerForTest implements Player {
    private Symbol symbol;
    private final Random randomNumberGenerator;

    public RandomNumberPlayerForTest(Symbol symbol) {
        randomNumberGenerator = new Random();
        this.symbol = symbol;
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public int nextMoveOn(Grid grid) {
        int randomCellOffset = generateRandomCellOffset();
        randomCellOffset = findVacantRandomCell(grid, randomCellOffset);

        System.out.println("Random Test Player Generated Cell Offset: [" + randomCellOffset + "]");
        return randomCellOffset;
    }

    private int findVacantRandomCell(Grid grid, int randomCellOffset) {
        while (!grid.isEmptyAt(randomCellOffset)) {
            randomCellOffset = generateRandomCellOffset();
        }
        return randomCellOffset;
    }

    private int generateRandomCellOffset() {
        return randomNumberGenerator.nextInt(TOTAL_CELLS);
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
