package managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface AdvancedFileManager extends BasicFileManager {
    File createDirectory(String fileName);

    String getBaseDirectory();
    void setBaseDirectory(String baseDirectory);
    int getDirectoryCount();
    void setDirectoryCount(int directoryCount);
    int getFileCount();
    void setFileCount(int fileCount);
    ArrayList<File> getDirectories();
    void setDirectories(ArrayList<File> directories);
    ArrayList<File> getFiles();
    void setFiles(ArrayList<File> files);
    void addFile(ArrayList<File> files, File file);
}