package tictactoe.prompt;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                is("\nPlease enter the number of the cell where you wish to put your next move\n"));
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

        assertThat(stringWriter.toString(), is("\nHello World\n"));
    }

    @Test (expected = RuntimeException.class)
    public void exceptionIsThrownWhenThereIsAnErrorInReading() throws IOException {
        Prompt commandLinePrompt = createPromptWithMockedReaderAndWriterWhichThrowExceptions();
        commandLinePrompt.readsInput();
    }

    @Test (expected = RuntimeException.class)
    public void exceptionIsThrownWhenThereIsAnErrorInWriting() throws IOException {
        Prompt commandLinePrompt = createPromptWithMockedReaderAndWriterWhichThrowExceptions();
        commandLinePrompt.readsInput();
    }

    private CommandLinePrompt createPromptWithMockedReaderAndWriterWhichThrowExceptions() throws IOException {
        Writer writer = mock(Writer.class);
        BufferedReader reader = mock(BufferedReader.class);

        when(reader.readLine()).thenThrow(new IOException("issue with reading"));
        doThrow(new IOException("issue with writing")).when(writer).write("\n");

        return new CommandLinePrompt(reader, writer);
    }

    private CommandLinePrompt createCommandLinePrompt() {
        BufferedReader stringReader = new BufferedReader(new StringReader("hello"));
        stringWriter = new StringWriter();
        return new CommandLinePrompt(stringReader, stringWriter);
    }
}
