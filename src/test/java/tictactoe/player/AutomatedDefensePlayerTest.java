package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.CENTRE;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedDefensePlayerTest {
    @Mock
    private Prompt prompt;
    private AutomatedDefensePlayer player;

    @Before
    public void setup() {
        player = new AutomatedDefensePlayer(X, prompt);
    }

    @Test
    public void getsPlayersSymbol() {
        assertThat(player.getSymbol(), is(X));
    }

    @Test
    public void takesWinningMove() {
        Grid gridWithPotentialWin = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, X, O, BOTTOM_ROW_OFFSET).build()
        );


        assertThat(player.nextMoveOn(gridWithPotentialWin), is(1));
    }

    @Test
    public void takeOpponentsWinningMove() {
        Grid gridWithOpponentPotentialWin = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, X, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridWithOpponentPotentialWin), is(1));
    }

    @Test
    public void takeCentreMove() {
        Grid gridWithNoPotentialWinningMovesButFreeCentre = new Grid(
                aRowBuilder().withHorizontalRow(O, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridWithNoPotentialWinningMovesButFreeCentre), is(CENTRE));
    }

    @Test
    public void takeOppositeCornerMove() {
        Grid gridWithOppositeCornerFree = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(X, O, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridWithOppositeCornerFree), is(6));
    }

    @Test
    public void blockOpponentsForkFromCentre() {
        Grid gridToBlockOpponentsFork = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridToBlockOpponentsFork), is(1));
    }

    @Test
    public void blockOpponentsForkAroundCorner() {
        Grid gridToBlockOpponentsFork = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridToBlockOpponentsFork), is(1));
    }

    @Test
    public void takesEmptyCorner() {
        Grid gridWithEmptyCorners = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(O, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridWithEmptyCorners), is(0));
    }

    @Test
    public void takeAnyVacantCellMove() {
        Grid gridWithEmptyEdge = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(O, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(player.nextMoveOn(gridWithEmptyEdge), is(1));
    }

    @Test(expected = NoMovesAvailableException.class)
    public void noPotentialMoves() {
        Grid gridWithNoMoves = new Grid(
                aRowBuilder().withHorizontalRow(O, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(O, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, X, O, BOTTOM_ROW_OFFSET).build()
        );

        player.nextMoveOn(gridWithNoMoves);
    }
}