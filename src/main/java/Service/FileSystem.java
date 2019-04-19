package Service;

import Models.Directory;

public class FileSystem {

    private Directory currentDirectory;

    public void setCurrentDirectory(Directory directory) {
        this.currentDirectory = directory;
    }

    public Directory getCurrentDirectory() {
        return this.currentDirectory;
    }
}
