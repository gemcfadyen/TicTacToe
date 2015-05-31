package tictactoe;

import com.google.common.collect.Lists;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import java.util.List;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.GridFactory.createEmptyGrid;
import static tictactoe.player.PlayerFactory.createAutomatedPlayer;
import static tictactoe.player.PlayerFactory.createHumanPlayer;
import static tictactoe.prompt.PromptFactory.createCommandLinePrompt;

public class Game {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;
    private static final int NUMBER_OF_PLAYERS = 2;

    private final Grid grid;
    private final Player[] players;
    private final Prompt prompt;

    Game() {
        grid = createEmptyGrid();
        prompt = createCommandLinePrompt();
        players = initialiseOrderOfPlayers(createAutomatedPlayer(X, prompt), createHumanPlayer(O, prompt));
    }

    protected Game(Grid grid, Player playerO, Player playerX, Prompt prompt) {
        this.grid = grid;
        this.players = initialiseOrderOfPlayers(playerO, playerX);
        this.prompt = prompt;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private Player[] initialiseOrderOfPlayers(Player playerO, Player playerX) {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        players[FIRST_PLAYER] = playerO;
        players[SECOND_PLAYER] = playerX;

        return players;
    }

    public void play() {
        boolean isGameInProgress = true;
        while (isGameInProgress) {
            grid.reset();
            playGame();
            isGameInProgress = replay();
        }
    }

    private void playGame() {
        prompt.display(grid.rows());
        int currentPlayerIndex = FIRST_PLAYER;
        for (int i = 0; i < Grid.TOTAL_CELLS; i++) {

            grid.update(playersMove(currentPlayerIndex), playersSymbol(currentPlayerIndex));
            prompt.display(grid.rows());

            if (isWinningMove()) {
                break;
            }
            currentPlayerIndex = opponent(currentPlayerIndex);
        }
        prompt.displayGameOver();
    }

    private Symbol playersSymbol(int currentPlayerIndex) {
        return players[currentPlayerIndex].getSymbol();
    }

    private int playersMove(int currentPlayerIndex) {
        return players[currentPlayerIndex].nextMoveOn(grid);
    }

    private boolean isWinningMove() {
        GameStatus gameStatus = grid.evaluateWinningStatus();
        if (gameStatus.hasWinner()) {
            prompt.displayWinningMessageFor(gameStatus.winningSymbol());
            return true;
        }
        return false;
    }

    private int opponent(int playersTurn) {
        return playersTurn == FIRST_PLAYER
                ? SECOND_PLAYER
                : FIRST_PLAYER;
    }

    private boolean replay() {
        prompt.promptPlayerToStartNewGame();
        String playAgainOption = prompt.readsInput();
        playAgainOption = repromptUntilValid(playAgainOption);
        return playAgainOption.equalsIgnoreCase("Y");
    }

    private String repromptUntilValid(String playAgainOption) {
        String replayOption = playAgainOption;
        while (!valid(replayOption)) {
            prompt.promptPlayerToStartNewGame();
            replayOption = prompt.readsInput();
        }
        return replayOption;
    }

    private boolean valid(String playAgainOption) {
        List<String> validOptionsForNewGame = Lists.newArrayList("Y", "N");
        return validOptionsForNewGame.contains(playAgainOption.toUpperCase())
                ? true
                : false;
    }
}
