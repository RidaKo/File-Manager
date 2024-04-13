package managers;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import exceptions.*;

public class TextFileManager extends FileManager {
    
    public TextFileManager() throws DirectoryNotFoundException {
        super();
    }

    public TextFileManager(String directory) throws DirectoryNotFoundException {
        super(directory);
    }

    public File createFile(String fileName) throws IOException {
        File file = new File(super.getBaseDirectory(), fileName + ".txt");
        if (file.createNewFile()) {
            System.out.println("Text file created: " + fileName);
            super.addFile(super.getFiles(), file);
            super.setFileCount(getFileCount() + 1);
        } else {
            System.out.println("File already exists: " + fileName);
        }
        return file;
    }

    public String readText(File file) throws IOException {
        StringBuffer textContent = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textContent.append(line).append("\n");
            }
        }
        return textContent.toString();
    }

    public void writeText(String content, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("Written successfully");
        }
    }

    public void appendText(String content, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            System.out.println("Appended successfully");
        }
    }

    @Override
    public String toString() {
        super.toString();  
        return "TextFileManager:[id=" + getId() + ", baseDirectory=" + getBaseDirectory() + ", directoryCount=" + getDirectoryCount() + ", fileCount=" + getFileCount() + "]";
    }
}
