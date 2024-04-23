package exceptions;

public class FileNotFoundException extends FileManagementException {
    public String directory;
    public FileNotFoundException(String message, String directory) {
        super(message);
        this.directory = directory;
    }
    public String getDirectory()
    {
        return this.directory;
    }
}