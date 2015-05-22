package tictactoe;

import tictactoe.grid.GameStatus;
import tictactoe.grid.Grid;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import static tictactoe.grid.Grid.TOTAL_CELLS;
import static java.lang.String.format;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Game {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;

    private final Grid grid;
    private final Player[] players;
    private final Prompt prompt;

    public Game(Grid grid, Player playerO, Player playerX, Prompt prompt) {
        this.grid = grid;
        this.players = initialiseOrderOfPlayers(playerO, playerX);
        this.prompt = prompt;
    }

    private Player[] initialiseOrderOfPlayers(Player playerO, Player playerX) {
        Player[] players = new Player[2];
        players[FIRST_PLAYER] = playerO;
        players[SECOND_PLAYER] = playerX;

        return players;
    }

    public void play() {
        int currentPlayerIndex = FIRST_PLAYER;

        for (int i = 0; i < TOTAL_CELLS; i++) {
            grid.update(playersMove(currentPlayerIndex), playersSymbol(currentPlayerIndex));

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
