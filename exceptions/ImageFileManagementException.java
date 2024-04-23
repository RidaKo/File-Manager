package exceptions;

import java.io.File;

public class ImageFileManagementException extends FileManagementException{
    public String directory;
    public ImageFileManagementException(String message) {
        super(message);
    }
    public String getDirectory()
    {
        return this.directory;
    }
}
