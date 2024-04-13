package test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import managers.AdvancedFileManager;
import managers.FileManager;
import managers.ImageFileManager;
import managers.TextFileManager;
import exceptions.*;

public class Test {

    public static void main(String args[]) {

        String dir = "C:\\Users\\kroda\\OneDrive\\Java\\porj"; // "C:\\Users\\HP\\OneDrive\\Java\\porj"; // 
        System.out.println("Testuojamas failu tvarkykles modulis:");

        FileManager fileManager1 = null;
        try {
            fileManager1 = new FileManager(dir);
            System.out.println(fileManager1.toString());
            System.out.println("Objektu skaicius: " + FileManager.getObjectCount());
        } catch (DirectoryNotFoundException e) {
            System.err.println("FileManager inicializcija nesuveike: " + e.getMessage());
        }

        AdvancedFileManager advancedFileManager = null;
        try {
            advancedFileManager = new FileManager(dir);
            // Continue with operations that use advancedFileManager
        } catch (DirectoryNotFoundException e) {
            System.err.println("AdvancedFileManager inicializcija nesuveike: " + e.getMessage());
        }

        ImageFileManager imageFileManager = null;
        try {
            System.out.println("\nTestuojama ImageFileManager klase:");
            imageFileManager = new ImageFileManager(dir + "\\images");
            imageFileManager.displayImage("example.jpg");
            imageFileManager.resizeImage("example.jpg", 100, 100);
            imageFileManager.createImageFile("example2", new Color(0), 200, 200);
            
        } catch (DirectoryNotFoundException e) {
            System.err.println("ImageFileManager inicializcija nesuveike: " + e.getMessage());
        }   catch(IOException | ImageFileManagementException e)
        {
            System.err.println("Nepavyko sukurti nuotraukos" + e.getMessage());
        }

        TextFileManager textFileManager = null;
        try {
            System.out.println("\nTestuojama TextFileManager klase:");
            textFileManager = new TextFileManager(dir + "\\text_files");
            File textFile = textFileManager.createFile("example");
            textFileManager.writeText("Labas, pasauli!", textFile);
            System.out.println("Tekstinio failo turinys:");
            System.out.println(textFileManager.readText(textFile));
            textFileManager.appendText("\nPapildomas tekstas.", textFile);
            System.out.println("Atnaujintas tekstinio failo turinys:");

            File textFile2 = textFileManager.createFile("example2");
            textFileManager.writeText("Labas, pasauli!", textFile2);
            System.out.println("Tekstinio failo turinys:");
            System.out.println(textFileManager.readText(textFile2));
        } catch (DirectoryNotFoundException e) {
            System.err.println("TextFileManager inicializcija nesuveike: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ivyko I/O klaida: " + e.getMessage());
        }
        
        // catch (IOException e) {
        //     System.err.println("Klaida su failu, dirbant su TextFileManager: " + e.getMessage());
        // }

    }
}

// String testDirectory = "C:\\Users\\kroda\\OneDrive\\Java\\proj";
// testDirectory = "C:\\Users\\HP\\OneDrive\\Java\\proj";
// FileManager fileManager = new FileManager(testDirectory);
// fileManager.println("listFiles");

// String directory = "directory";
// String directory2 = "directory2";
// fileManager.createFile(directory);
// //fileManager.listFiles();
// fileManager.println("listFiles"); //fileManager.println();
// fileManager.createFile(directory2);
// //fileManager.listFiles();
// fileManager.println("listFiles");
// fileManager.deleteFile(directory);
// fileManager.deleteFile(directory2);
// //fileManager.listFiles();
// fileManager.println("listFiles"); //fileManager.println();
// System.out.println(FileManager.getObjectCount());

// //new File(testDirectory).delete();
