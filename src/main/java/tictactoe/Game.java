package tictactoe;

import tictactoe.grid.GameStatus;
import tictactoe.grid.Grid;
import tictactoe.player.HumanPlayer;
import tictactoe.player.Player;
import tictactoe.prompt.CommandLinePrompt;
import tictactoe.prompt.Prompt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static java.lang.String.format;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Game {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;
    private static final int NUMBER_OF_PLAYERS = 2;

    private final Grid grid;
    private final Player[] players;
    private final Prompt prompt;

    Game() {
        grid = new Grid();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Writer writer = new BufferedWriter(new OutputStreamWriter(System.out));
        prompt = new CommandLinePrompt(reader, writer);
        players = initialiseOrderOfPlayers(new HumanPlayer(X, prompt), new HumanPlayer(O, prompt));
    }

    public Game(Grid grid, Player playerO, Player playerX, Prompt prompt) {
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
        int currentPlayerIndex = FIRST_PLAYER;
        prompt.display(grid.display());
        for (int i = 0; i < Grid.TOTAL_CELLS; i++) {

            grid.update(playersMove(currentPlayerIndex), playersSymbol(currentPlayerIndex));
            prompt.display(grid.display());

            if (isWinningMove()) {
                break;
            }
            currentPlayerIndex = opponent(currentPlayerIndex);
        }
        prompt.display("Game Over");
    }

    private Symbol playersSymbol(int currentPlayerIndex) {
        return players[currentPlayerIndex].getSymbol();
    }

    private int playersMove(int currentPlayerIndex) {
        return players[currentPlayerIndex].nextMoveOn(grid);
    }

    private boolean isWinningMove() {
        GameStatus gameStatus = grid.getWinStatus();
        if (gameStatus.hasWinner()) {
            prompt.display(format("Player%s wins", gameStatus.winningSymbol()));
            return true;
        }
        return false;
    }

    private int opponent(int playersTurn) {
        return playersTurn == FIRST_PLAYER
                ? SECOND_PLAYER
                : FIRST_PLAYER;
    }
}
