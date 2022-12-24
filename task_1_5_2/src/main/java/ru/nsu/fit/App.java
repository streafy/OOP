package ru.nsu.fit;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import ru.nsu.fit.notebook.Notebook;

@Command(name = "notebook")
public class App implements Runnable {
    Notebook notebook = new Notebook();

    @Command(name = "-add")
    public Integer add(
            @Parameters(arity = "1") String[] noteData
    ) {
        System.out.println("Adding note: " + noteData[0] + " " + noteData[1]);
        notebook.addNote(noteData[0], noteData[1]);
        return 0;
    }

    @Command(name = "-rm")
    public Integer rm(
            @Parameters(arity = "1") String title
    ) {
        System.out.println("Remove note with title: " + title);
        notebook.removeNote(title);
        return 0;
    }

    @Command(name = "-show")
    public Integer show() {
        notebook.show();
        return 0;
    }

    @Override
    public void run() {
        System.out.println("Subcommand needed: '-add', '-rm' or '-show'");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
