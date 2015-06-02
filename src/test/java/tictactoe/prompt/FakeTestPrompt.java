package tictactoe.prompt;

import tictactoe.Symbol;
import tictactoe.grid.Row;

import java.util.List;

public class FakeTestPrompt implements Prompt {
    private static final String GAME_OVER = "N";
    private static final String REPLAY_GAME = "Y";

    private int numberOfGamesLeftToPlay;
    private Symbol symbolForNonAutomatedPlayer;
    private String[] inputs;
    private int indexOfInput = 0;
    private int opponentWin = 0;

    public FakeTestPrompt(int numberOfGamesLeftToPlay, String startingPlayerType, Symbol symbolForNonAutomatedPlayer) {
        this.numberOfGamesLeftToPlay = numberOfGamesLeftToPlay;
        inputs = new String[] {startingPlayerType, REPLAY_GAME};
        this.symbolForNonAutomatedPlayer = symbolForNonAutomatedPlayer;
    }

    @Override
    public String readsInput() {
        String valueToReturn = GAME_OVER;
        if (numberOfGamesLeftToPlay >= 0) {
            valueToReturn = inputs[indexOfInput];
            togglePlayersResponseIndex();
        }
        numberOfGamesLeftToPlay--;
        return valueToReturn;
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void displayWinningMessageFor(Symbol symbol) {
        System.out.println("Win for [" + symbol + "]");
        if (symbol.equals(symbolForNonAutomatedPlayer)) {
            opponentWin++;
        }
    }

    public int totalWinsForNonAutomatedPlayer() {
        return opponentWin;
    }

    private void togglePlayersResponseIndex() {
        if (indexOfInput == 1) {
            indexOfInput = 0;
        } else {
            indexOfInput = 1;
        }
    }

    @Override
    public void promptPlayerToStartNewGame() {
    }

    @Override
    public void promptPlayerForNextMove() {
    }

    @Override
    public void promptForOrderOfPlay() {
    }

    @Override
    public void displayGameOver() {
    }

    @Override
    public void display(List<Row> rows) {
    }

    @Override
    public void display(Symbol symbol, int move) {
    }
}
