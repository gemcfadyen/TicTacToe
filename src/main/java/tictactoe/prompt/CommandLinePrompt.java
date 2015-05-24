package tictactoe.prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Georgina on 22/05/2015.
 */
public class CommandLinePrompt implements Prompt {
    private static final String NEW_LINE_CHARACTER = "\n";

    private final BufferedReader reader;
    private final Writer writer;

    public CommandLinePrompt(BufferedReader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public String readsInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error in reading input", e);
        }
    }

    @Override
    public void promptPlayer() {
        writeToConsole("Please enter the number of the cell where you wish to put your next move");
    }

    @Override
    public void display(String message) {
        writeToConsole(message);
    }

    private void writeToConsole(String message) {
        try {
            writer.write(NEW_LINE_CHARACTER);
            writer.write(message);
            writer.write(NEW_LINE_CHARACTER);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error in writing to the command line", e);
        }
    }
}
