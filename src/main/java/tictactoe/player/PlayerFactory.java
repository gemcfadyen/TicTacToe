package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.prompt.Prompt;

public class PlayerFactory {

    public static Player createHumanPlayer(Symbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}
