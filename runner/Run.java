package runner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import exceptions.DirectoryNotFoundException;
import factories.FileManagerFactory;
import managers.FileManager;
import managers.ImageFileManager;
import managers.SerializationManager;
import managers.TextFileManager;

public class Run {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        FileManagerFactory factory = new FileManagerFactory();
        while (running) {
            boolean pickingManager = true;

            FileManager fileManager = null;
            while (pickingManager) {

                System.out.println("Program has starded");
                System.out.println("Type the type of fileManager you wish to use: ");
                System.out.println("Possible choices: \nFileManager \nTextFileManager \nImageFileManager \nOr type 'Load' to load previous program instace");
                String manager = scanner.nextLine();

                try {
                    if(manager.equalsIgnoreCase("load")){
                        fileManager = factory.createManager("filemanager");
                        fileManager = fileManager.loadState(fileManager,"FileManagerState.bin");
                    }
                    else{
                        System.out.println(
                        "Plese input the directory you wish to initialize or the current one will be initialized: ");
                        String dir = scanner.nextLine();
                        fileManager = factory.createManager(manager, dir);
                    }
                    

                    break;
                } catch (DirectoryNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }

            if (fileManager instanceof TextFileManager) {
                TextFileManager textFileManager = (TextFileManager) fileManager;
            } else if (fileManager instanceof ImageFileManager) {
                ImageFileManager imageFileManager = (ImageFileManager) fileManager;
            } 

            boolean continueOperations = true;

            while (continueOperations) {
                try {
                    System.out.println("Choose what action you would like to take next:");
                    System.out.println(
                            "1. Create File \n2. Create Directory \n3. Delete File \n4. List Files \n5. Save program state \n6. Exit \n7. Get curent directory \n8. Choose different file manager\n9. Set new base directory");
                    int action = Integer.parseInt(scanner.nextLine());

                    switch (action) {
                        case 1:
                            System.out.println("Enter the name of the file to create:");
                            String fileName = scanner.nextLine();
                            fileManager.createFile(fileName);
                            break;
                        case 2:
                            System.out.println("Enter the name of the directory to create:");
                            String dirName = scanner.nextLine();
                            fileManager.createDirectory(dirName);
                            break;
                        case 3:
                            System.out.println("Enter the name of the file to delete:");
                            String fileToDelete = scanner.nextLine();
                            fileManager.deleteFile(fileToDelete);
                            break;
                        case 4:
                            fileManager.listFiles();
                            break;
                        case 5:
                            final FileManager fileManager2 = fileManager;
                            Thread t = new Thread() {
                                public void run() {
                                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("FileManagerState.bin"))) {
                                        out.writeObject(fileManager2);
                                        System.out.println("FileManager state has been serialized to FileManagerState.bin");
                                    } catch (IOException e) {
                                        System.out.println("Failed to save state: " + e.getMessage());
                                    } catch (Exception e) {
                                        System.out.println("An unexpected error occurred: " + e.getMessage());
                                    }
                                }
                            };
                            t.start();
                            break;
                        case 6:
                            continueOperations = false;
                            running = false;
                            break;

                        case 7:
                            System.out.println("Current dir is -> " + fileManager.getBaseDirectory());
                            break;

                        case 8:
                            continueOperations = false;
                            break;
                        case 9:
                            try {
                                System.out.println("Enter a new directory:");
                                String baseDirectory = scanner.nextLine();
                                fileManager.setBaseDirectory(baseDirectory);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                        default:
                            System.out.println("Invalid action.");
                            break;

                    }
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
           
        }
        scanner.close();
    }
}
