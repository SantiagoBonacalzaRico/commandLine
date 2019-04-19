package Service.Strategy;

import Service.FileSystem;
import Service.Operation.*;

public class OperationStrategy {

    private static final String QUIT = "quit";
    private static final String CURRENT_DIRECTORY = "pwd";
    private static final String LIST_CONTENTS = "ls";
    private static final String MAKE_DIRECTORY = "mkdir";
    private static final String CHANGE_DIRECTORY = "cd";
    private static final String CREATE_FILE = "touch";

    public void runOperation(String command, String suffix, FileSystem fileSystem) {
        if (CURRENT_DIRECTORY.equals(command)) {
            PwdOperation pwdOperation = new PwdOperation(fileSystem);
            pwdOperation.processOperation();
        } else if (LIST_CONTENTS.equals(command)) {
            LsOperation lsOperation = new LsOperation(fileSystem, suffix);
            lsOperation.processOperation();
        } else if (QUIT.equals(command)) {
            System.out.println("Logged Out");
        } else if (MAKE_DIRECTORY.equals(command)) {
            MkdirOperation mkdirOperation = new MkdirOperation(fileSystem, suffix);
            mkdirOperation.processOperation();
        } else if (CHANGE_DIRECTORY.equals(command)) {
            CdOperation cdOperation = new CdOperation(fileSystem, suffix);
            cdOperation.processOperation();
        } else if (CREATE_FILE.equals(command)) {
            TouchOperation touchOperation = new TouchOperation(fileSystem, suffix);
            touchOperation.processOperation();
        } else {
            System.out.println("Not a recognizable command");
        }
    }
}
