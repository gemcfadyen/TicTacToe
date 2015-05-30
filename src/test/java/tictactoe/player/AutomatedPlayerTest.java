package tictactoe.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.anyInt;
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
    @Mock private Grid grid;

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
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centerCellTaken()).thenReturn(true);
        when(grid.isEmptyAt(anyInt())).thenReturn(false);

        assertThat(automatedPlayer.nextMoveOn(grid), is(NO_WINNING_MOVE));
    }

    @Test
    public void takeOpponentsWinningMove() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(GameStatus.potentialWinAt(7));

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

    @Test
    public void takeOpeningMoveInTopLeftCell() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        when(grid.isEmpty()).thenReturn(true);

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void startForkWhenCenterIsTaken() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        when(grid.isEmpty()).thenReturn(false);
        when(grid.centerCellTaken()).thenReturn(true);
        when(grid.evaluateForForksWhenCenterIsOccupied(X)).thenReturn(GameStatus.potentialForkAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromTopRowWhenCenterIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCenterCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(GameStatus.potentialForkAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromVerticalRowWhenCenterIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCenterCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(GameStatus.potentialForkAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromBottomRowWhenCenterIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCenterCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromBottomRow(X)).thenReturn(GameStatus.potentialForkAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void blockOpponentsForkWhenCenterIsTaken() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCenterCell();
        when(grid.centerCellTaken()).thenReturn(true);
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.potentialForkAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromTopRowWhenCenterIsVacant() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(GameStatus.potentialForkAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromVerticalRowWhenCenterIsOccupied() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(GameStatus.potentialForkAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromBottomRowWhenCenterIsOccupied() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromBottomRow(O)).thenReturn(GameStatus.potentialForkAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void takeCenterMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centerCellTaken()).thenReturn(false);

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));

    }

    @Test
    public void takeOppositeCornerMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centerCellTaken()).thenReturn(true);
        when(grid.isEmptyAt(0)).thenReturn(true);
        when(grid.getSymbolAtCellWithOffset(8)).thenReturn(O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void takeFreeCornerMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centerCellTaken()).thenReturn(true);
        threeCornersOccupiedBySamePlayer();

        assertThat(automatedPlayer.nextMoveOn(grid), is(6));
    }

    private void threeCornersOccupiedBySamePlayer() {
        when(grid.isEmptyAt(0)).thenReturn(false);
        when(grid.isEmptyAt(2)).thenReturn(false);
        when(grid.isEmptyAt(8)).thenReturn(false);
        when(grid.isEmptyAt(6)).thenReturn(true);
        when(grid.getSymbolAtCellWithOffset(2)).thenReturn(O);
    }

    private void noPotentialWinForAutomatedPlayerOrOpponent() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(GameStatus.noWin());
    }

    private void noSuggestedMovesForAutomatedPlayer() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCenterCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromBottomRow(X)).thenReturn(GameStatus.noWin());
    }

    private void noForksAroundCenterCell() {
        when(grid.isEmpty()).thenReturn(false);
        when(grid.centerCellTaken()).thenReturn(false);
        when(grid.evaluateForForksWhenCenterIsOccupied(X)).thenReturn(GameStatus.noWin());
    }

    private void noPotentialForksForOpponent() {
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForForksWhenCenterIsOccupied(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(GameStatus.noWin());
        when(grid.evaluateForksFromBottomRow(O)).thenReturn(GameStatus.noWin());
    }
}
