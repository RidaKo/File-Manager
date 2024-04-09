package managers;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileManager extends FileManager{
    
    public TextFileManager() {
        super();
    }

    public TextFileManager(String directory) {
        super(directory);
    }

    public File createFile(String fileName) {
        File file = new File(super.getBaseDirectory(), fileName+".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("Text failas sukurtas: " + fileName);
                super.addFile(super.getFiles(), file);
                super.setFileCount(getFileCount()+1);
            } else {
                System.out.println("Failas jau sukurtas: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Nepavyko sukurti klases: " + e.getMessage());
        }
        return file;
    }


    public String readText(File file) {
        StringBuffer textContent = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Klaida skaitant is failo: " + e.getMessage());
        }
        return textContent.toString();
    }

    public void writeText(String content, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("Irasyta sekmingai");
        } catch (IOException e) {
            System.err.println("Klaida rasant i faila: " + e.getMessage());
        }
    }

    public void appendText(String content, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            System.out.println("Irasyta sekmingai");
        } catch (IOException e) {
            System.err.println("Klaida rasant i faila: " + e.getMessage());
        }
    }

    public String toString() {
        super.toString();
        return "TextFileManager:[id=" + getId() + ", baseDirectory=" + getBaseDirectory() + ", directoryCount=" + getDirectoryCount() + ", fileCount=" + getFileCount() + "]";
    }
    
}
