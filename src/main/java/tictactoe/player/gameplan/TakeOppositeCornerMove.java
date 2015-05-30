package tictactoe.player.gameplan;

/**
 * Created by Georgina on 27/05/2015.
 */

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Grid;

import java.util.Map;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class TakeOppositeCornerMove extends CornerMove implements GamePlan {
    @Override
    public int execute(final Grid grid, Symbol symbol) {
        Map<Integer, Integer> vacantCorners = applyToCorners(getFreeCornersOppositeOpponent(grid, symbol));

        return has(vacantCorners)
                ? first(vacantCorners)
                : GamePlan.NO_SUGGESTED_MOVE;
    }

    private Predicate<Map.Entry<Integer, Integer>> getFreeCornersOppositeOpponent(final Grid grid, final Symbol symbol) {
        return cornerAndItsOpposite -> grid.isEmptyAt(cornerAndItsOpposite.getKey())
                        && grid.getSymbolAtCellWithOffset(cornerAndItsOpposite.getValue()) == opponent(symbol);
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}