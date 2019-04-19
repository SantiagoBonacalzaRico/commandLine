package Service.Operation;

import Models.Directory;
import Models.File;
import Service.FileSystem;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class LsOperation implements Operation {

    private FileSystem fileSystem;
    private String suffix;

    public LsOperation(FileSystem fileSystem, String suffix) {
        this.fileSystem = fileSystem;
        this.suffix = suffix;
    }

    @Override
    public void processOperation() {
        Directory currentDirectory = fileSystem.getCurrentDirectory();

        List<String> results = new ArrayList<>();
        Optional<List<Directory>> insideDirectories = getOptionalListDirectories(currentDirectory);
        Optional<List<File>> insideFiles = getOptionalListFiles(currentDirectory);

        insideDirectories.ifPresentOrElse(d -> {
            d.forEach(i -> {
                results.add("/" + i.getName());
                if (suffix.equals(RECURSIVE_SUFFIX)) {
                    Optional<List<Directory>> recursiveDirectories = Optional.ofNullable(i.getDirectoryList());
                    recursiveDirectories.ifPresent(r -> {
                        r.forEach(di -> {
                            results.add("/" + di.getName());
                            Optional<List<File>> currentFiles = getOptionalListFiles(di);
                            currentFiles.ifPresent(f -> {
                                f.forEach(fi -> {
                                    results.add(fi.getName());
                                });
                            });
                        });
                    });
                    Optional<List<File>> recursiveFiles = getOptionalListFiles(i);
                    recursiveFiles.ifPresent(rf -> {
                        rf.forEach(f -> {
                            results.add(f.getName());
                        });
                    });
                }
            });
        }, () -> {});

        insideFiles.ifPresentOrElse(d -> {
            d.forEach(i -> {
                results.add(i.getName());
            });
        }, () -> {});

        ListIterator li = results.listIterator(0);
        while(li.hasNext()) {
            System.out.println(li.next());
        }

        System.out.println("");
    }

    private Optional<List<File>> getOptionalListFiles(Directory directory) {
        return Optional.ofNullable(directory.getFileList());
    }

    private Optional<List<Directory>> getOptionalListDirectories(Directory directory) {
        return Optional.ofNullable(directory.getDirectoryList());
    }
}
