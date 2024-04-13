package managers;

import exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractFileManager implements AdvancedFileManager {
    protected static int objectCount = 0;
    protected final int id;
    protected String baseDirectory;
    protected int directoryCount;
    protected int fileCount;
    protected ArrayList<File> directories;
    protected ArrayList<File> files;

    protected AbstractFileManager() throws DirectoryNotFoundException {
        this.id = AbstractFileManager.objectCount++;
        this.baseDirectory = "C:";
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryCount = 0;
        this.fileCount = 0;
        initializeDirectory(baseDirectory);
    }

    protected AbstractFileManager(String directory) throws DirectoryNotFoundException {
        this.id = AbstractFileManager.objectCount++;
        this.baseDirectory = directory;
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryCount = 0;
        this.fileCount = 0;
        initializeDirectory(directory);
    }

    private void initializeDirectory(String directory) throws DirectoryNotFoundException {
        File baseDir = new File(directory);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            throw new DirectoryNotFoundException("Directory not found or is not a directory: " + directory);
        }

        File[] files = baseDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    directoryCount++;
                    directories.add(file);
                } else {
                    fileCount++;
                    this.files.add(file);
                }
            }
        }
    }

    public void listFiles() {
        File baseDir = new File(baseDirectory);
        String[] files = baseDir.list();
        if (files != null && files.length > 0) {
            System.out.println("Files in directory " + baseDirectory + ":");
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files in directory " + baseDirectory);
        }
    }

    public static int getObjectCount() {
        return objectCount;
    }

    public int getId() {
        return id;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public int getDirectoryCount() {
        return directoryCount;
    }

    public void setDirectoryCount(int directoryCount) {
        this.directoryCount = directoryCount;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public ArrayList<File> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<File> directories) {
        this.directories = directories;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public void addFile(ArrayList<File> files, File file) {
        this.files.add(file);
    }

}
