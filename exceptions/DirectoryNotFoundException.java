package exceptions;

public class DirectoryNotFoundException extends FileManagementException {
    public String directory;
    public DirectoryNotFoundException(String message, String directory) {
        super(message);
        this.directory = directory;
    }
    public String getDirectory()
    {
        return this.directory;
    }
}