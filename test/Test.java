package test;
import java.io.File;

import managers.AdvancedFileManager;
import managers.FileManager;
import managers.ImageFileManager;
import managers.TextFileManager;

public class Test {

    public static void main(String args[]) {
        
    String dir = "C:\\Users\\HP\\OneDrive\\Java\\porj"; //"C:\\Users\\kroda\\OneDrive\\Java\\porj"
    System.out.println("Testuojamas failu tvarkykles modulis:");

    FileManager fileManager1 = new FileManager(dir);
    fileManager1.toString();
    System.out.println("Objektu skaicius: " + FileManager.getObjectCount());
    fileManager1.toString();
    AdvancedFileManager advancedFileManager = new FileManager();
    advancedFileManager.

    // Testuojama ImageFileManager klasė
    System.out.println("\nTestuojama ImageFileManager klase:");
    ImageFileManager imageFileManager = new ImageFileManager(dir+"\\images");
    imageFileManager.displayImage("example.jpg");
    imageFileManager.resizeImage("example.jpg", 100, 100);

    // Testuojama TextFileManager klasė
    System.out.println("\nTestuojama TextFileManager klase:");
    TextFileManager textFileManager = new TextFileManager(dir+"\\text_files");
    File textFile = textFileManager.createFile("example");
    textFileManager.writeText("Labas, pasauli!", textFile);
    System.out.println("Tekstinio failo turinys:");
    System.out.println(textFileManager.readText(textFile));
    textFileManager.toString();

    textFileManager.appendText("\nPapildomas tekstas.", textFile);
    System.out.println("Atnaujintas tekstinio failo turinys:");

    File textFile2 = textFileManager.createFile("example2");
    textFileManager.writeText("Labas, pasauli!", textFile2);
    System.out.println("Tekstinio failo turinys:");
    System.out.println(textFileManager.readText(textFile2));

        
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
    }
    
}
