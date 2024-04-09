package managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager extends AbstractFileManager{

    // private static int objectCount = 0;
    // private final int id;
    // private String baseDirectory;
    // private int directoryCount;
    // private int fileCount;
    // private ArrayList<File> directories;
    // private ArrayList<File> files;

    public FileManager() {
        super();
    }

    public FileManager(String directory) {
        super(directory); 
    }

    // public FileManager() {
    //     this.id = FileManager.objectCount;
    //     FileManager.objectCount++;
    //     this.baseDirectory = "C:";
    //     File baseDir = new File(this.baseDirectory);

    //     this.directories = new ArrayList<>();
    //     this.files = new ArrayList<>();
    //     this.directoryCount = 0;
    //     this.fileCount = 0;

    //     File files[] = baseDir.listFiles();
    //     for (File file : files) {
    //         if (file.isDirectory()) {
    //             directoryCount++;
    //             this.directories.add(file);
    //         } else {
    //             this.fileCount++;
    //             this.files.add(file);
    //         }
    //     }

    // }

    // public FileManager(String directory) {
    //     this.id = FileManager.objectCount;
    //     FileManager.objectCount++;
    //     this.baseDirectory = directory;
    //     File baseDir = new File(directory);
    //     this.directories = new ArrayList<>();
    //     this.files = new ArrayList<>();
    //     this.directoryCount = 0;
    //     this.fileCount = 0;

    //     File files [] = baseDir.listFiles();
    //     for (File file : files) {
    //         if (file.isDirectory()) {
    //             this.directoryCount++;
    //             this.directories.add(file);
    //         } else {
    //             this.fileCount++;
    //             this.files.add(file);
    //         }
    //     }

    // }


    public File createDirectory(String fileName) {
        File file = new File(baseDirectory, fileName);
        if (!file.exists()) {
            boolean created = file.mkdirs();
            if (created) {
                System.out.println("Sukurta: " + fileName);
                this.directories.add(file);
                this.directoryCount++;
            } else {
                System.out.println("Nepavyko sukurti: " + fileName);
            }
        } else {
            System.out.println("Failas jau egzistuoja: " + fileName);
        }
        return file;
    }

    // public File createFile(String fileName) {
    //     File file = new File(baseDirectory, fileName);
    //     if (!file.exists()) {
    //         boolean created = file.createNewFile();
    //         if (created) {
    //             System.out.println("Sukurta: " + fileName);
    //             this.files.add(file);
    //             this.fileCount++;
    //         } else {
    //             System.out.println("Nepavyko sukurti: " + fileName);
    //         }
    //     } else {
    //         System.out.println("Failas jau egzistuoja: " + fileName);
    //     }
    //     return file;
    // }

    public File createFile(String fileName) {
        File file = new File(baseDirectory, fileName);
        try {
            if(file.createNewFile()) {
                System.out.println("Failas sukurats: " + fileName);
                this.files.add(file);
                this.fileCount++;
            } else {
                System.out.println("Falas jau yra: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Klaida" + e.getMessage());
        }
        return file;
    }

    public void deleteFile(String fileName) {
        File file = new File(baseDirectory, fileName);
        if (file.exists()) {
            boolean deleted = deleteFileRecursively(file);
            if (deleted) {
                System.out.println("Failas istrnntas: " + fileName);
                this.directories.remove(file);
                this.directoryCount--;
            } else {
                System.out.println("Nepavyko istrinti: " + fileName);
            }
        } else {
            System.out.println("Failas neegzistuoja: " + fileName);
        }
    }

    private boolean deleteFileRecursively(File file) {
        File content[] = file.listFiles();
        if (content != null) {
            for (File f : content) {
                deleteFileRecursively(f);
            }
        }
        return file.delete();
    }

    final public void listFiles() {
        File baseDir = new File(baseDirectory);
        String files[] = baseDir.list();
        if (files != null && files.length > 0) {
            System.out.println("Failai direktorijoje " + baseDirectory + ":");
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("Nera failu direktorijoje " + baseDirectory);
        }
    }

    public String toString() {
        return "FileManager: [id=" + this.getId() + ", baseDirectory=" + this.getBaseDirectory() + ", directoryCount="
                + this.getDirectoryCount() + ", fileCount=" + this.getFileCount() + "]";
    }

    // public static int getObjectCount() {
    //     return objectCount;
    // }

    // public int getId() {
    //     return id;
    // }

    // public String getBaseDirectory() {
    //     return baseDirectory;
    // }

    // public void setBaseDirectory(String baseDirectory) {
    //     this.baseDirectory = baseDirectory;
    // }

    // public int getDirectoryCount() {
    //     return directoryCount;
    // }

    // public void setDirectoryCount(int directoryCount) {
    //     this.directoryCount = directoryCount;
    // }

    // public int getFileCount() {
    //     return fileCount;
    // }

    // public void setFileCount(int fileCount) {
    //     this.fileCount = fileCount;
    // }

    // public ArrayList<File> getDirectories() {
    //     return directories;
    // }

    // public void setDirectories(ArrayList<File> directories) {
    //     this.directories = directories;
    // }

    // public ArrayList<File> getFiles() {
    //     return files;
    // }

    // public void setFiles(ArrayList<File> files) {
    //     this.files = files;
    // }

    // public void addFile(ArrayList<File> files, File file) {
    //     this.files.add(file);
    // }

}