package exceptions;

public class DirectoryNotFoundException extends FileManagementException {
    public DirectoryNotFoundException(String message) {
        super(message);
    }
}