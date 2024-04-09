package managers;

import java.io.File;


public interface BasicFileManager {
    void listFiles();
    File createFile(String fileName);
    void deleteFile(String fileName);
}