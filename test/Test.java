package test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import factories.FileManagerFactory;
import managers.AdvancedFileManager;
import managers.FileManager;
import managers.ImageFileManager;
import managers.TextFileManager;
import exceptions.*;

public class Test {

    public static void main(String args[]) {

        String dir = "C:\\Users\\kroda\\OneDrive\\Java\\porj"; //"C:\\Users\\HP\\OneDrive\\Java\\porj"; 
        System.out.println("Testuojamas failu tvarkykles modulis:");
        FileManagerFactory fManagerFact = new FileManagerFactory();
        
        System.err.println("Deep copy");
        FileManager fileManager1 = null;
        try {
            fileManager1 = fManagerFact.createManagerWithArgument("FileManager", dir); //new FileManager(dir);
            System.out.println("1" + fileManager1.getFiles().toString());

            //System.out.println("Objektu skaicius: " + FileManager.getObjectCount());
        } catch (DirectoryNotFoundException e) {
            System.err.println("FileManager inicializcija nesuveike: " + e.getMessage() + e.getDirectory());
        }

        
        try {
            FileManager fileManager2 = fileManager1.clone();
            System.out.println("2" + fileManager2.getFiles().toString());
            System.err.println("Po klonavimo");
            //System.out.println("Objektu skaicius: " + FileManager.getObjectCount());
            //fileManager2.setBaseDirectory(dir+"\\text_files");
            fileManager1.removeFileByIndex(0);
            System.out.println("1" + fileManager1.getFiles().toString());
            System.out.println("2" + fileManager2.getFiles().toString());
        } catch (CloneNotSupportedException e) {
            System.err.println("FileManager klonavimas nesuveike " + e.getMessage());
        } 
        // catch(DirectoryNotFoundException e){
        //     System.err.println(e.getMessage()+ " " + e.getDirectory());
        // }




        
        //// Preitas kodas
        AdvancedFileManager advancedFileManager = null;
        try {
            advancedFileManager = fManagerFact.createManagerWithArgument("FileManager", dir);///new FileManager(dir);
        } catch (DirectoryNotFoundException e) {
            System.err.println("AdvancedFileManager inicializcija nesuveike: " + e.getMessage());
        }

        ImageFileManager imageFileManager = null;
        //FileManager imageFileManager = null;
        try {
            System.out.println("\nTestuojama ImageFileManager klase:");
            imageFileManager = (ImageFileManager)fManagerFact.createManagerWithArgument("ImageFileManager", dir + "\\images"); //new ImageFileManager(dir + "\\images"); //
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
        //FileManager textFileManager = null;
        try {
            System.out.println("\nTestuojama TextFileManager klase:");
            textFileManager = (TextFileManager)fManagerFact.createManagerWithArgument("TextFileManager", dir); //new TextFileManager(dir + "\\text_files");  // fManagerFact.createManagerWithArgument("TextFileManager", dir);
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
