package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 16/05/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock private Player playerO;
    @Mock private Player playerX;
    @Mock private Prompt prompt;
    @Mock private Grid grid;

    private Game game;

    @Before
    public void setup() {
        game = new Game(grid, playerO, playerX, prompt);
    }

    @Test
    public void gameEndsWhenNineMovesHaveBeenMade() {
        when(grid.evaluateWinningStatus()).thenReturn(GameStatus.noWin());

        game.play();

        verify(playerX, times(4)).nextMoveOn(grid);
        verify(playerO, times(5)).nextMoveOn(grid);
        verify(prompt).display("Game Over");
    }

    @Test
    public void gameEndsWhenGridContainsThreeXsInARow() {
        when(grid.evaluateWinningStatus()).thenReturn(GameStatus.winFor(X));

        game.play();

        verify(prompt, times(2)).display(grid.rows());
        verify(prompt, times(1)).display("PlayerX wins");
    }

    @Test
    public void gameEndsWhenGridContainsThreeOsInARow() {
        when(grid.evaluateWinningStatus()).thenReturn(GameStatus.winFor(O));
        game.play();

        verify(prompt, times(2)).display(grid.rows());
        verify(prompt, times(1)).display("PlayerO wins");
    }

    @Test
    public void gridIsUpdatedOnceAPlayerHasMadeTheirMove() {
        when(playerO.nextMoveOn(grid)).thenReturn(3);
        when(playerO.getSymbol()).thenReturn(O);
        when(grid.evaluateWinningStatus()).thenReturn(GameStatus.winFor(O));

        game.play();

        verify(grid, times(1)).update(3, O);
    }
}
