package tictactoe.player.gameplan;

/**
 * Created by Georgina on 27/05/2015.
 */

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Grid;

import java.util.Map;

import static com.google.common.collect.Maps.filterEntries;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.DIAGONAL_OPPOSITE_CORNERS;

public class TakeOppositeCornerMove implements GamePlan {
    @Override
    public int execute(final Grid grid, Symbol symbol) {
        Map<Integer, Integer> vacantCorners = getFreeCornersOppositeOpponent(grid, symbol);

        return has(vacantCorners)
                ? first(vacantCorners)
                : GamePlan.NO_SUGGESTED_MOVE;
    }

    private Integer first(Map<Integer, Integer> vacantCorners) {
        return vacantCorners.keySet().iterator().next();
    }

    private boolean has(Map<Integer, Integer> vacantCorners) {
        return vacantCorners != null && vacantCorners.size() > 0;
    }

    private Map<Integer, Integer> getFreeCornersOppositeOpponent(final Grid grid, final Symbol symbol) {
        return filterEntries(DIAGONAL_OPPOSITE_CORNERS, new Predicate<Map.Entry<Integer, Integer>>() {
            @Override
            public boolean apply(Map.Entry<Integer, Integer> input) {
                return isCornerOppositeOpponentsCornerVacant(input, grid, symbol);
            }
        });
    }

    private boolean isCornerOppositeOpponentsCornerVacant(Map.Entry<Integer, Integer> input, Grid grid, Symbol symbol) {
        return grid.isEmptyAt(input.getKey())
                && grid.getSymbolAtCellWithOffset(input.getValue()) == opponent(symbol);
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}