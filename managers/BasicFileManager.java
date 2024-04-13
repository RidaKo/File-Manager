package managers;

import java.io.File;
import java.io.IOException;


public interface BasicFileManager {
    void listFiles();
    File createFile(String fileName) throws IOException;
    void deleteFile(String fileName);
}