package tictactoe.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 25/05/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    private static final int NO_WINNING_MOVE = -1;
    private Player automatedPlayer = new AutomatedPlayer(X);
    @Mock
    private Grid grid;

    @Test
    public void returnsPlayerSymbol() {
        assertThat(automatedPlayer.getSymbol(), is(X));
    }

    @Test
    public void takesWinningMove() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.potentialWinAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void noStrategicMoveAvailable() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(GameStatus.noWin());

        assertThat(automatedPlayer.nextMoveOn(grid), is(NO_WINNING_MOVE));
    }

    @Test
    public void takeOpponentsWinningMove() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(GameStatus.potentialWinAt(7));

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

}
