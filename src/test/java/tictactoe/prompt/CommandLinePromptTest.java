package tictactoe.prompt;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Georgina on 22/05/2015.
 */
public class CommandLinePromptTest {
    private Writer stringWriter;

    @Test
    public void promptsUserForInput() {
        Prompt commandLinePrompt = createCommandLinePrompt();
        commandLinePrompt.promptPlayer();

        assertThat(stringWriter.toString(),
                is("Please enter the number of the cell where you wish to put your next move\n"));
    }

    @Test
    public void readsInputFromTheCommandLine() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        assertThat(commandLinePrompt.readsInput(), is("hello"));
    }

    @Test
    public void displaysMessage() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        commandLinePrompt.display("Hello World");

        assertThat(stringWriter.toString(), is("Hello World"));
    }

    private CommandLinePrompt createCommandLinePrompt() {
        BufferedReader stringReader = new BufferedReader(new StringReader("hello"));
        stringWriter = new StringWriter();
        return new CommandLinePrompt(stringReader, stringWriter);
    }
}
