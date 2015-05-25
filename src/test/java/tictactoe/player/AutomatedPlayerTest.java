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
import static tictactoe.Symbol.X;

/**
 * Created by Georgina on 25/05/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    private static final int NO_WINNING_MOVE = -1;

    @Mock private Grid grid;

    public AutomatedPlayerTest() {
        super();
    }

    @Test
    public void takesWinningMove() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.potentialWinAt(2));

        Player automatedPlayer = new AutomatedPlayer(X);
        int playersMove = automatedPlayer.nextMoveOn(grid);

        assertThat(playersMove, is(2));
    }

    @Test
    public void noWinningMoveAvailable() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.noWin());

        Player automatedPlayer = new AutomatedPlayer(X);
        int playersMove = automatedPlayer.nextMoveOn(grid);

        assertThat(playersMove, is(NO_WINNING_MOVE));
    }

}
