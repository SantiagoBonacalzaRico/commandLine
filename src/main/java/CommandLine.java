import Models.Directory;
import Service.Strategy.OperationStrategy;
import Service.FileSystem;

import java.util.Scanner;

public class CommandLine {

    private static final String QUIT = "quit";
    private static final String ROOT_DIRECTORY = "root";

    public static void main(String[] args) {
        String command = "";
        String suffix = "";

        FileSystem fileSystem = new FileSystem();
        Directory startDirectory = new Directory();
        startDirectory.setName(ROOT_DIRECTORY);
        fileSystem.setCurrentDirectory(startDirectory);

        while(! command.equals(QUIT)) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            String[] inputs = input.split("\\s+");
            command = inputs.length > 0 ? inputs[0]  : "";
            suffix = inputs.length > 1 ? inputs[1] : "";

            if (command != null) {
                run(fileSystem, command, suffix);
            }
        }
    }

    private static void run(FileSystem fileSystem, String command, String suffix) {
        OperationStrategy operationStrategy = new OperationStrategy();
        operationStrategy.runOperation(command, suffix, fileSystem);
    }
}
