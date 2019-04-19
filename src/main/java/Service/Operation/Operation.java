package Service.Operation;

import Service.FileSystem;

interface Operation {
    static final String RECURSIVE_SUFFIX = "-r";
    static final String PREVIOUS_DIR_SUFFIX = "..";

    void processOperation();
}
