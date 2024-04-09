package managers;

import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ImageFileManager extends FileManager {

    public ImageFileManager() {
        super();
    }

    public ImageFileManager(String directory) {
        super(directory);
    }

    //create

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