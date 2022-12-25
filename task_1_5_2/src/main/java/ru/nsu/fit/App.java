package ru.nsu.fit;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParseResult;
import ru.nsu.fit.notebook.Notebook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class to run Notebook App with command line arguments.
 */
@Command(name = "notebook")
public class App implements Runnable {
    @Option(names = { "-f", "--file" })
    private String filename;

    private Notebook notebook;

    /**
     * Adds new note to Notebook.
     *
     * @param title       note's title
     * @param description note's description
     * @return 0
     */
    @Command(name = "-add")
    private Integer add(
            @Parameters() String title,
            @Parameters() String description
    ) {
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
            @Parameters(index = "0", arity = "0..1") LocalDateTime after,
            @Parameters(index = "1", arity = "0..1") LocalDateTime before,
            @Parameters(index = "2..*", arity = "0..1") List<String> keywords
    ) {
        if (after == null && before == null && keywords == null) {
            notebook.show();
        } else {
            notebook.showFiltered(after, before, keywords);
        }
        return 0;
    }

    /**
     * Custom execution strategy.
     */
    private int executionStrategy(ParseResult parseResult) {
        init();
        return new CommandLine.RunLast().execute(parseResult);
    }

    /**
     * Initialize notebook for every subcommand.
     */
    private void init() {
        notebook = (filename == null) ? new Notebook() : new Notebook(filename);
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
        App app = new App();
        int exitCode = new CommandLine(app)
                .registerConverter(
                        LocalDateTime.class,
                        s -> LocalDateTime.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                )
                .setExecutionStrategy(app::executionStrategy)
                .execute(args);
        System.exit(exitCode);
    }
}
