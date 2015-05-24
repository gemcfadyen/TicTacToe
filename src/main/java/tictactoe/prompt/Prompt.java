package tictactoe.prompt;

import tictactoe.grid.Row;

import java.util.List;

/**
 * Created by Georgina on 16/05/2015.
 */
public interface Prompt {
    String readsInput();
    void promptPlayer();
    void display(String message);
    void display(List<Row> rows);
}
