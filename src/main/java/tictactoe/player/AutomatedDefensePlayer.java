package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCellMove;
import tictactoe.player.gameplan.cornermoves.TakeEmptyCornerMove;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerToOpponentMove;
import tictactoe.player.gameplan.forking.BlockOpponentFromForking;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;
import tictactoe.prompt.Prompt;

public class AutomatedDefensePlayer extends AutomatedPlayer implements Player {

    public AutomatedDefensePlayer(Symbol symbol, Prompt prompt) {
       super(symbol, prompt);
    }

    protected GamePlan[] orderedGamePlan() {
        return new GamePlan[]{
                new TakeWinningMove(),
                new Block(new TakeWinningMove()),

                new TakeCentreMove(),
                new TakeOppositeCornerToOpponentMove(),

                new BlockOpponentFromForking(),

                new TakeEmptyCornerMove(),
                new TakeVacantCellMove()
        };
    }
}
