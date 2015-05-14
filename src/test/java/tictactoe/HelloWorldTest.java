package tictactoe;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Georgina on 14/05/2015.
 */
public class HelloWorldTest {

    @Test
    public void printsHello(){
        HelloWorld hi = new HelloWorld();
        Assert.assertThat(hi.greeting(), Is.is("hello"));
    }

}
