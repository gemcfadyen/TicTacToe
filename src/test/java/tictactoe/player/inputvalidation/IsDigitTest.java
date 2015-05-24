package tictactoe.player.inputvalidation;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Georgina on 24/05/2015.
 */
public class IsDigitTest {
    private IsDigit isDigitValidation = new IsDigit();

    @Test
    public void numericValueIsValid() {
        assertThat(isDigitValidation.isValid("1"), is(true));
    }

    @Test
    public void nonNumericValueIsNotValid() {
        assertThat(isDigitValidation.isValid("abc"), is(false));
    }
}
