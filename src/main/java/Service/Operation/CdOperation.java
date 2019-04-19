package Service.Operation;

import Models.Directory;
import Service.FileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Stream;

public class CdOperation implements Operation {

    private FileSystem fileSystem;
    private String suffix;

    public CdOperation(FileSystem fileSystem, String suffix) {
        this.fileSystem = fileSystem;
        this.suffix = suffix;
    }

    @Override
    public void processOperation() {
        Directory currentDirectory = fileSystem.getCurrentDirectory();

        if (suffix.equals(PREVIOUS_DIR_SUFFIX)) {
            if (currentDirectory.getParentDirectory() != null) {
                fileSystem.setCurrentDirectory(currentDirectory.getParentDirectory());
                currentDirectory = currentDirectory.getParentDirectory();
            }
        }

        getDirectoryListForCd(currentDirectory, fileSystem, suffix);
    }

    private static void getDirectoryListForCd(Directory currentDirectory, FileSystem fileSystem, String suffix) {
        Optional<List<Directory>> currentDirectoryList = Optional.ofNullable(currentDirectory.getDirectoryList());
        List<String> results = new ArrayList<>();
        results.add(currentDirectory.getName());

        currentDirectoryList.ifPresentOrElse(l -> {
            Stream<Directory> streamDirectory = l.stream().filter(ld -> ld.getName().equals(suffix));
            streamDirectory.forEach(
                    li -> {
                        li.setParentDirectory(currentDirectory);
                        fileSystem.setCurrentDirectory(li);
                        results.add(li.getName());
                    }
            );
        },() -> {});

        if (results.size() == 1 && ! suffix.equals(PREVIOUS_DIR_SUFFIX)) {
            System.out.println("No such File or Directory");
        } else {
            ListIterator li = results.listIterator(0);
            while(li.hasNext()) {
                System.out.print("/" + li.next());
            }
            System.out.println("");
        }
    }
}
