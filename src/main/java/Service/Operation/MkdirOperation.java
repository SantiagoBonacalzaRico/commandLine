package Service.Operation;

import Models.Directory;
import Service.FileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MkdirOperation implements Operation{

    private FileSystem fileSystem;
    private String suffix;

    public MkdirOperation(FileSystem fileSystem, String suffix) {
        this.fileSystem = fileSystem;
        this.suffix = suffix;
    }

    @Override
    public void processOperation() {
        Directory currentDirectory = fileSystem.getCurrentDirectory();

        if (suffix != null && ! suffix.isEmpty()) {
            Optional<List<Directory>> insideDirectories = Optional.ofNullable(currentDirectory.getDirectoryList());

            insideDirectories.ifPresentOrElse(d -> {
                d.forEach(di -> {
                    List<Directory> newDirectories = new ArrayList<>(d);
                    if (di.getName().equals(suffix)) {
                        System.out.println("Directory already exists");
                    } else /* if (! d.contains(di)) */ {
                        Directory newDirectory = new Directory();
                        newDirectory.setName(suffix);
                        newDirectory.setParentDirectory(currentDirectory);
                        newDirectories.add(newDirectory);
                        currentDirectory.setDirectoryList(newDirectories);
                    }
                });
            }, () -> {
                List<Directory> directoryList = new ArrayList<>();
                Directory newDirectory = new Directory();
                newDirectory.setName(suffix);
                directoryList.add(newDirectory);
                currentDirectory.setDirectoryList(directoryList);
            });
        } else {
            System.out.println("Directory name must be provided");
        }
    }
}
