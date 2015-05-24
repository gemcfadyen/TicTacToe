package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.prompt.Prompt;

/**
 * Created by Georgina on 24/05/2015.
 */
public class PlayerFactory {

    public static Player createHumanPlayer(Symbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}
