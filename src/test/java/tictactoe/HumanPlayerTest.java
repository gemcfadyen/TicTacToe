package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Georgina on 16/05/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HumanPlayerTest {
    private static final int VACANT_CELL = 3;
    private static final int OCCUPIED_CELL = 2;

    @Mock private Prompt prompt;
    @Mock private Grid grid;

    private Player human;

    @Before
    public void setup() {
        human = new HumanPlayer(prompt);
    }

    @Test
    public void playerIsPromptedForNextMove() {
        when(prompt.readsInput()).thenReturn(VACANT_CELL);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt).promptPlayer();
    }

    @Test
    public void playerSpecifiesNextMoveThroughThePrompt() {
        when(prompt.readsInput()).thenReturn(VACANT_CELL);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        int indexOfNextMove = human.nextMoveOn(grid);

        verify(prompt).readsInput();
        assertThat(indexOfNextMove, is(equalTo(VACANT_CELL)));
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellLargerThanGrid() {
        when(prompt.readsInput()).thenReturn(100).thenReturn(VACANT_CELL);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(2)).promptPlayer();
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellSmallerThanTheGrid() {
        when(prompt.readsInput()).thenReturn(-50).thenReturn(-1).thenReturn(VACANT_CELL);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(3)).promptPlayer();
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellThatIsAlreadyTaken() {
        when(prompt.readsInput()).thenReturn(OCCUPIED_CELL).thenReturn(VACANT_CELL);
        when(grid.isEmptyAt(OCCUPIED_CELL)).thenReturn(false);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(2)).promptPlayer();
    }
}
