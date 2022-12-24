package ru.nsu.fit;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import ru.nsu.fit.notebook.Notebook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class to run Notebook App with command line arguments.
 */
@Command(name = "notebook")
public class App implements Runnable {
    private final Notebook notebook = new Notebook();

    /**
     * Adds new note to Notebook.
     *
     * @param title note's title
     * @param description note's description
     * @return 0
     */
    @Command(name = "-add")
    private Integer add(
            @Parameters() String title,
            @Parameters() String description
    ) {
        System.out.println("Adding note: " + title + " " + description);
        notebook.addNote(title, description);
        return 0;
    }

    /**
     * Removes note from notebook by title.
     *
     * @param title note's title
     * @return 0
     */
    @Command(name = "-rm")
    private Integer rm(
            @Parameters() String title
    ) {
        System.out.println("Remove note with title: " + title);
        notebook.removeNote(title);
        return 0;
    }

    /**
     * Shows notes based on input parameters.
     *
     * @param after    Notes with creation date after this date will be printed
     * @param before   Notes with creation date before this date will be printed
     * @param keywords Notes with title containing any of the keywords will be printed
     * @return 0
     */
    @Command(name = "-show")
    private Integer show(
            @Parameters(index = "0", arity = "0..1") String after,
            @Parameters(index = "1", arity = "0..1") String before,
            @Parameters(index = "2..*", arity = "0..1") List<String> keywords
    ) {
        if (after == null && before == null && keywords == null) {
            notebook.show();
        } else {
            LocalDateTime afterDate = LocalDateTime.parse(after, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            LocalDateTime beforeDate = LocalDateTime.parse(before, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            notebook.showFiltered(afterDate, beforeDate, keywords);
        }
        return 0;
    }

    /**
     * Executes if there is no subcommands in command line arguments.
     */
    @Override
    public void run() {
        System.out.println("Subcommand needed: '-add', '-rm' or '-show'");
    }

    /**
     * Runs notebook App.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
