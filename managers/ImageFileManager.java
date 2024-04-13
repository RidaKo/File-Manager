package managers;

import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.io.IOException; 
import java.awt.Color;
import java.awt.image.BufferedImage;

import exceptions.*;

public class ImageFileManager extends FileManager {

    public ImageFileManager() throws DirectoryNotFoundException{
        super();
    }

    public ImageFileManager(String directory) throws DirectoryNotFoundException{
        super(directory);
    }

    public File createImageFile(String fileName) throws IOException, ImageFileManagementException{
        File imageFile = new File(getBaseDirectory(), fileName + ".jpg"); // Assuming JPEG format
            if (imageFile.createNewFile()) {
                System.out.println("Image file created: " + fileName);
                super.addFile(super.getFiles(), imageFile);
                super.setFileCount(getFileCount() + 1);
            } else {
                throw new ImageFileManagementException("Image file already exists: " + fileName);
            }
        return imageFile;
    }

    public File createImageFile(String fileName, Color color, int width, int height) throws IOException, ImageFileManagementException{
        File imageFile = new File(getBaseDirectory(), fileName + ".jpg"); 
        if (imageFile.createNewFile()) {
            System.out.println("Image file created: " + fileName);
            super.addFile(super.getFiles(), imageFile);
            super.setFileCount(getFileCount() + 1);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, color.getRGB());
                }
            }
            ImageIO.write(image, "jpg", imageFile);
        } else {
            throw new ImageFileManagementException("Image file already exists: " + fileName);
        }
        

       
        

        return imageFile;
    }

    public void displayImage(String imageName) {
        try {
            File imageFile = new File(getBaseDirectory(), imageName);
            BufferedImage image = ImageIO.read(imageFile);


            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(image));
            JScrollPane scrollPane = new JScrollPane(label);
            frame.getContentPane().add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Klaida rodant paveiksleli: " + e.getMessage());
        }
    }


    public void resizeImage(String imageName, int newWidth, int newHeight) {
        try {
            File imageFile = new File(getBaseDirectory(), imageName);
            BufferedImage originalImage = ImageIO.read(imageFile);

            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);

            File outputFile = new File(getBaseDirectory(), "resized_" + imageName);
            ImageIO.write(toBufferedImage(resizedImage), "jpg", outputFile);
            
            System.out.println("Pakeisto fomato paveikslelis: " + imageName);
        } catch (Exception e) {
            System.out.println("Klaida pakeiciant formata: " + e.getMessage());
        }
    }

    private BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);
        return bufferedImage;
    }

    public String toString() {
        return "ImageFileManager: [id=" + getId() + ", baseDirectory=" + getBaseDirectory() + ", directoryCount=" + getDirectoryCount() + ", fileCount=" + getFileCount() + "]";
    }
}