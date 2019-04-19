package Service.Operation;

import Models.Directory;
import Models.File;
import Service.FileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TouchOperation implements Operation {

    private FileSystem fileSystem;
    private String suffix;

    public TouchOperation(FileSystem fileSystem, String suffix) {
        this.fileSystem = fileSystem;
        this.suffix = suffix;
    }

    @Override
    public void processOperation() {
        Directory currentDirectory = fileSystem.getCurrentDirectory();

        if (suffix != null && ! suffix.isEmpty()) {
            Optional<List<File>> insideFiles = Optional.ofNullable(currentDirectory.getFileList());

            insideFiles.ifPresentOrElse(f -> {
                f.forEach(fi -> {
                    List<File> newFiles = new ArrayList<>(f);
                    if (fi.getName().equals(suffix)) {
                        System.out.println("File already exists");
                    } else {
                        File newFile = new File();
                        newFile.setName(suffix);
                        newFile.setParentDirectory(currentDirectory);
                        newFiles.add(newFile);
                        currentDirectory.setFileList(newFiles);
                    }
                });
            }, () -> {
                List<File> fileList = new ArrayList<>();
                File newFile = new File();
                newFile.setName(suffix);
                newFile.setParentDirectory(currentDirectory);
                fileList.add(newFile);
                currentDirectory.setFileList(fileList);
            });
        } else {
            System.out.println("A name for the file must be provided");
        }
    }
}
