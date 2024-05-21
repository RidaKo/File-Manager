package managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationManager implements Runnable {
    private FileManager fileManager;
    private String fileName;

    public SerializationManager() {
    }

    public SerializationManager(FileManager fileManager, String filename) {
        this.fileManager = fileManager;
        this.fileName = filename;
    }

    public static void serializeToFile(Object object, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        }
    }

    public static Object deserializeFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        }
    }

    
    public void saveState(FileManager fileManager, String fileName) {
        Thread thread = new Thread(this);
        this.fileManager = fileManager;
        this.fileName = fileName;
        thread.start();
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

    @Override
    public void run() {
        try {
            serializeToFile(this.fileManager, this.fileName);
        } catch (IOException e) {
            System.out.println("Failed to save state: " + e.getMessage());
        }
    }
}
