package tictactoe.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.status.GameStatus.noPotentialMove;
import static tictactoe.grid.status.GameStatus.potentialMoveAt;

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
        when(grid.evaluateWinningMoveFor(X)).thenReturn(potentialMoveAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void noStrategicMoveAvailable() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centreCellTaken()).thenReturn(true);
        when(grid.isEmptyAt(anyInt())).thenReturn(false);
        allCornersOccupiedBySamePlayer();
        when(grid.getVacantCell()).thenReturn(noPotentialMove());

        assertThat(automatedPlayer.nextMoveOn(grid), is(NO_WINNING_MOVE));
    }

    @Test
    public void takeOpponentsWinningMove() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(noPotentialMove());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(potentialMoveAt(7));

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

    @Test
    public void takeOpeningMoveInTopLeftCell() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        when(grid.isEmpty()).thenReturn(true);

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void startForkWhenCentreIsTaken() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        when(grid.isEmpty()).thenReturn(false);
        when(grid.centreCellTaken()).thenReturn(true);
        when(grid.evaluateForForksWhenCentreIsOccupied(X)).thenReturn(potentialMoveAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromTopRowWhenCentreIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCentreCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(potentialMoveAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromVerticalRowWhenCentreIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCentreCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(potentialMoveAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void startForkFromBottomRowWhenCentreIsOccupied() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCentreCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromBottomRow(X)).thenReturn(potentialMoveAt(2));

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void blockOpponentsForkWhenCentreIsTaken() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCentreCell();
        when(grid.centreCellTaken()).thenReturn(true);
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(potentialMoveAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromTopRowWhenCentreIsVacant() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(potentialMoveAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromVerticalRowWhenCentreIsOccupied() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(potentialMoveAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blockOpponentsForkFromBottomRowWhenCentreIsOccupied() {
        noSuggestedMovesForAutomatedPlayer();
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromBottomRow(O)).thenReturn(potentialMoveAt(3));

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void takeCentreMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centreCellTaken()).thenReturn(false);

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));

    }

    @Test
    public void takeOppositeCornerMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centreCellTaken()).thenReturn(true);
        when(grid.isEmptyAt(0)).thenReturn(true);
        when(grid.getSymbolAtCellWithOffset(8)).thenReturn(O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void takeVacantCornerMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centreCellTaken()).thenReturn(true);
        threeCornersOccupiedBySamePlayer();

        assertThat(automatedPlayer.nextMoveOn(grid), is(6));
    }

    @Test
    public void takeAnyVacantCellMove() {
        noSuggestedMovesForAutomatedPlayer();
        noPotentialForksForOpponent();
        when(grid.centreCellTaken()).thenReturn(true);
        allCornersOccupiedBySamePlayer();
        when(grid.getVacantCell()).thenReturn(potentialMoveAt(1));

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    private void allCornersOccupiedBySamePlayer() {
        when(grid.isEmptyAt(0)).thenReturn(false);
        when(grid.isEmptyAt(2)).thenReturn(false);
        when(grid.isEmptyAt(6)).thenReturn(false);
        when(grid.isEmptyAt(8)).thenReturn(false);
    }

    private void threeCornersOccupiedBySamePlayer() {
        when(grid.isEmptyAt(0)).thenReturn(false);
        when(grid.isEmptyAt(2)).thenReturn(false);
        when(grid.isEmptyAt(8)).thenReturn(false);
        when(grid.isEmptyAt(6)).thenReturn(true);
        when(grid.getSymbolAtCellWithOffset(2)).thenReturn(O);
    }

    private void noPotentialWinForAutomatedPlayerOrOpponent() {
        when(grid.evaluateWinningMoveFor(X)).thenReturn(noPotentialMove());
        when(grid.evaluateWinningMoveFor(O)).thenReturn(noPotentialMove());
    }

    private void noSuggestedMovesForAutomatedPlayer() {
        noPotentialWinForAutomatedPlayerOrOpponent();
        noForksAroundCentreCell();
        when(grid.evaluateForksFromTopRow(X)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(X)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromBottomRow(X)).thenReturn(noPotentialMove());
    }

    private void noForksAroundCentreCell() {
        when(grid.isEmpty()).thenReturn(false);
        when(grid.centreCellTaken()).thenReturn(false);
        when(grid.evaluateForForksWhenCentreIsOccupied(X)).thenReturn(noPotentialMove());
    }

    private void noPotentialForksForOpponent() {
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForForksWhenCentreIsOccupied(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromTopRow(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromVerticalRows(O)).thenReturn(noPotentialMove());
        when(grid.evaluateForksFromBottomRow(O)).thenReturn(noPotentialMove());
    }
}
