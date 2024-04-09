package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFile {
    private File file;

    public MyFile(String path) {
        this.file = new File(path);
    }


    public boolean exists() {
        return file.exists();
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public boolean delete() {
        return file.delete();
    }

    public boolean renameTo(String newName) {
        File newFile = new File(newName);
        return file.renameTo(newFile);
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "failas=" + file +
                '}';
    }

    public String readText() {
        StringBuffer textContent = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Klaida skaitant is failo: " + e.getMessage());
        }
        return textContent.toString();
    }

    public void writeText(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(content);
            System.out.println("Irasyta sekmingai");
        } catch (IOException e) {
            System.err.println("Klaida rasant i faila: " + e.getMessage());
        }
    }

    public void appendText(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true))) {
            writer.write(content);
            System.out.println("Irasyta sekmingai");
        } catch (IOException e) {
            System.err.println("Klaida rasant i faila: " + e.getMessage());
        }
    }
}
