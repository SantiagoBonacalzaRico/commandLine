package Service.Operation;

import Models.Directory;
import Service.FileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PwdOperation implements Operation{

    private FileSystem fileSystem;

    public PwdOperation(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void processOperation() {
        getRecursiveTree();
    }

    public void getRecursiveTree() {
        Directory parentDirectory = fileSystem.getCurrentDirectory().getParentDirectory();
        List<String> result = new ArrayList<>();
        result.add(fileSystem.getCurrentDirectory().getName());
        while(parentDirectory != null) {
            result.add(parentDirectory.getName());
            parentDirectory = parentDirectory.getParentDirectory();
        }

        ListIterator li = result.listIterator(result.size());
        while(li.hasPrevious()) {
            System.out.print("/" + li.previous());
        }

        System.out.println("");
    }
}
