package files;
import java.io.File;

public class TextFile extends MyFile {
    public TextFile(String path) {
        super(path);
    }

    // Additional methods specific to text files

    public void readText() {
        // Code to read text from the file
    }

    public void writeText(String content) {
        // Code to write text to the file
    }

    public String toString() {
        return "TextFile{" +
                "file=" + super.getFile().getName() +
                '}';
    }

}