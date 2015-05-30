package tictactoe.prompt;

import tictactoe.Symbol;
import tictactoe.grid.Row;

import java.util.List;

/**
 * Created by Georgina on 16/05/2015.
 */
public interface Prompt {
    String readsInput();

    void promptPlayerToStartNewGame();
    void promptPlayerForNextMove();

    void displayWinningMessageFor(Symbol symbol);
    void displayGameOver();
    void display(List<Row> rows);
}
