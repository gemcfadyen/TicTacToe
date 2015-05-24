package tictactoe.player.inputvalidation;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * Created by Georgina on 24/05/2015.
 */
public class IsDigit implements InputValidator {
    @Override
    public boolean isValid(String move) {
        return isNumber(move);
    }
}
