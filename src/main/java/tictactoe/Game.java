package tictactoe;

import static java.lang.String.format;

/**
 * Created by Georgina on 16/05/2015.
 */
public class Game {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;

    private final Grid grid;
    private final Player[] players;
    private final Publisher statusPublisher;

    public Game(Grid grid, Player playerO, Player playerX, Publisher statusPublisher) {
        this.grid = grid;
        this.players = initialiseOrderOfPlayers(playerO, playerX);
        this.statusPublisher = statusPublisher;
    }

    private Player[] initialiseOrderOfPlayers(Player playerO, Player playerX) {
        Player[] players = new Player[2];
        players[FIRST_PLAYER] = playerO;
        players[SECOND_PLAYER] = playerX;

        return players;
    }

    public void play() {
        int currentPlayerIndex = FIRST_PLAYER;

        for (int i = 0; i < grid.numberOfCells(); i++) {
            players[currentPlayerIndex].nextMoveOn(grid);

            if (isWinningMove()) {
                break;
            }
            currentPlayerIndex = opponent(currentPlayerIndex);
        }
        statusPublisher.display("Game Over");
    }

    private boolean isWinningMove() {
        if (grid.containsWinningRow()) {
            statusPublisher.display(format("Player%s wins", grid.getWinningSymbol()));
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
