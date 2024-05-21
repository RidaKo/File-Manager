package factories;

import exceptions.DirectoryNotFoundException;
import managers.FileManager;
import managers.ImageFileManager;
import managers.TextFileManager;

public class FileManagerFactory {
    public FileManager createManager(String type) throws DirectoryNotFoundException{
        // if (type.equalsIgnoreCase("FileManager")) {
        //     return new FileManager();
        // } else if (type.equalsIgnoreCase("ImageFileManager")) {
        //     return new ImageFileManager();
        // } else if (type.equalsIgnoreCase("TextFileManager")) {
        //     return new TextFileManager();
        // } else {
        //     throw new IllegalArgumentException("Neteisingas tipas: " + type);
        // }
        switch (type.toLowerCase()) {
            case "filemanager":
                return new FileManager();
            case "imagefilemanager":
                return new ImageFileManager();
            case "textfilemanager":
                return new TextFileManager();
            default:
                throw new IllegalArgumentException("Neteisingas tipas: " + type);
        }

        
    }

    public FileManager createManager(String type, String directory) throws DirectoryNotFoundException{
            // if (type.equalsIgnoreCase("FileManager")) {
            //     return new FileManager(arg);
            // } else if (type.equalsIgnoreCase("ImageFileManager")) {
            //     return new ImageFileManager(arg);
            // } else if (type.equalsIgnoreCase("TextFileManager")) {
            //     return new TextFileManager(arg);
            // } else {
            //     throw new IllegalArgumentException("Neteisingas tipas: " + type);
            // }


            switch (type.toLowerCase()) {
                case "filemanager":
                    return new FileManager(directory);
                case "imagefilemanager":
                    return new ImageFileManager(directory);
                case "textfilemanager":
                    return new TextFileManager(directory);
                default:
                    throw new IllegalArgumentException("Neteisingas tipas: " + type);
            }
       
    }
}

