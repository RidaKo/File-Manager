package exceptions;

public class FileNotFoundException extends FileManagementException {
    public FileNotFoundException(String message) {
        super(message);
    }
}