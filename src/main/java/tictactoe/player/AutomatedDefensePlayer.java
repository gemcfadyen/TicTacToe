package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCell;
import tictactoe.player.gameplan.cornermoves.TakeEmptyCorner;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerToOpponent;
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
                new TakeOppositeCornerToOpponent(),

                new BlockOpponentFromForking(),

                new TakeEmptyCorner(),
                new TakeVacantCell()
        };
    }
}
