package tictactoe;

import org.junit.Test;
import tictactoe.grid.GridFactory;
import tictactoe.player.AutomatedPlayer;
import tictactoe.player.Player;
import tictactoe.player.RandomNumberPlayerForTest;
import tictactoe.prompt.FakePromptForTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class UnbeatableComputerPlayerPerformanceTest {
    private static final int TIMES = 2000;

    @Test
    public void automatedPlayerOpensTheGameAndOpponentNeverWins() {
        FakePromptForTest fakePrompt = new FakePromptForTest(TIMES, "A");
        Player automatedPlayer = new AutomatedPlayer(X, fakePrompt);
        Player randomPlayer = new RandomNumberPlayerForTest(O);

        Game game = new Game(GridFactory.createEmptyGrid(), fakePrompt, new Player[] {automatedPlayer, randomPlayer}) {
            protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
                return new Player[] {
                        automatedPlayer, randomPlayer
                };
            }
        };

        game.play();
        assertThat(fakePrompt.countOpponentWins(), is(0));
    }

    @Test
    public void randomPlayerOpensTheGameAndNeverWins() {
        FakePromptForTest fakePrompt = new FakePromptForTest(TIMES, "H");
        Player automatedPlayer = new AutomatedPlayer(X, fakePrompt);
        Player randomPlayer = new RandomNumberPlayerForTest(O);

        Game game = new Game(GridFactory.createEmptyGrid(), fakePrompt, new Player[] {automatedPlayer, randomPlayer}) {
            protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
                return new Player[] {
                        randomPlayer, automatedPlayer
                };
            }
        };

        game.play();
        assertThat(fakePrompt.countOpponentWins(), is(0));
    }
}

