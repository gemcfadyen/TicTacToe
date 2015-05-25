package tictactoe.prompt;

import tictactoe.grid.Cell;
import tictactoe.grid.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static tictactoe.Symbol.VACANT;

/**
 * Created by Georgina on 22/05/2015.
 */
public class CommandLinePrompt implements Prompt {
    private static final String NEW_LINE_CHARACTER = "\n";

    private final BufferedReader reader;
    private final Writer writer;

    protected CommandLinePrompt(BufferedReader reader, Writer writer) {
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

    @Override
    public void display(List<Row> rows) {
        StringBuffer gridDisplay = new StringBuffer();

        for (Row row : rows) {
            gridDisplay.append(prints(row));
        }

        writeToConsole(gridDisplay.toString());
    }

    private StringBuffer prints(Row row) {
        StringBuffer gridDisplay = new StringBuffer();
        gridDisplay.append(" | ");
        for (Cell cell : row.getCells()) {
            if (cell.getSymbol() == VACANT) {
                gridDisplay.append("(" + cell.getOffset() + ")");
            } else {
                gridDisplay.append(" " + cell.getSymbol() + " ");
            }
            gridDisplay.append(" | ");
        }

        return gridDisplay.append("\n");
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