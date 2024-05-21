package managers;

import exceptions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.lang.CloneNotSupportedException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class FileManager implements Cloneable, Serializable, Runnable,  FileManagerInterface{

    protected static int objectCount = 0;
    protected final int id;
    protected String baseDirectory;
    protected int directoryCount;
    protected int fileCount;
    protected ArrayList<File> directories;
    protected ArrayList<File> files;
    public String saveFile;

    public FileManager() throws DirectoryNotFoundException {
        this.id = FileManager.objectCount++;
        this.baseDirectory = "C:";
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryCount = 0;
        this.fileCount = 0;
        initializeDirectory(this.baseDirectory);
    }

    public FileManager(String directory) throws DirectoryNotFoundException {
        this.id = FileManager.objectCount++;
        this.baseDirectory = directory;
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryCount = 0;
        this.fileCount = 0;
        initializeDirectory(directory);
    }


    public FileManager clone() throws CloneNotSupportedException
    {
        try {
            FileManager fm  = (FileManager)super.clone();
            fm.setBaseDirectory(this.baseDirectory);
            fm.setDirectories(cloneFiles(this.directories));
            fm.setFiles(cloneFiles(this.files));
            return fm;
        } 
        catch (DirectoryNotFoundException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException("Directorija nerasta, klonavimas nesekmingas.");
        }
    }

    private ArrayList<File> cloneFiles(ArrayList<File> original) {
        ArrayList<File> clonedList = new ArrayList<>(original.size());
        for (File file : original) {
            File clonedFile = new File(file.getPath());
            clonedList.add(clonedFile);
        }
        return clonedList;
    }


    // public void serializeObject(ExampleObject obj, String filename) {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
    //         oos.writeObject(obj);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public void run() {
        try {
            //serializeToFile(this, this.fileName);
            String filename = this.saveFile;
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(this);
            }
        } catch (IOException e) {
            System.out.println("Failed to save state: " + e.getMessage());
        }
    }

    private static Object deserializeFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        }
    }

    public FileManager loadState(FileManager fileManager, String fileName)
    {
        try {
            fileManager =  (FileManager)deserializeFromFile(fileName);
        } catch (Exception e) {
            System.out.println("Failed to load: " + e.getMessage());
        }
        return fileManager;
    }


    public File createDirectory(String fileName)  {
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

   

    public File createFile(String fileName) throws IOException {
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
        File baseDir = new File(this.baseDirectory);
        String files[] = baseDir.list();
        if (files != null && files.length > 0) {
            System.out.println("Failai direktorijoje " + this.baseDirectory + ":");
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("Nera failu direktorijoje " + this.baseDirectory);
        }
    }

    private void initializeDirectory(String directory) throws DirectoryNotFoundException {
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryCount = 0;
        this.fileCount = 0;
        File baseDir = new File(directory);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            throw new DirectoryNotFoundException("Direktorija nerasta arba tai ne direktorija: ", directory);
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


    // public void saveObject(Object obj, String filename) {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
    //         oos.writeObject(obj);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public Object loadObject(String filename) {
    //     Object obj = null;
    //     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
    //         obj = ois.readObject();
    //     } catch (IOException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //     return obj;
    // }


    public String toString() {
        return "FileManager: [id=" + this.getId() + ", baseDirectory=" + this.getBaseDirectory() + ", directoryCount="
                + this.getDirectoryCount() + ", fileCount=" + this.getFileCount() + "]";
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

    public void setBaseDirectory(String baseDirectory) throws DirectoryNotFoundException {
        this.baseDirectory = baseDirectory;

        initializeDirectory(baseDirectory);
        
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

    /// Metodas skirtas tik testavimui 
    public void removeFileByIndex(int index)
    {
        this.files.remove(index);
        this.fileCount--;
    }

}