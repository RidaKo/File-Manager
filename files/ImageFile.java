package files;

public class ImageFile extends MyFile {
    public ImageFile(String path) {
        super(path);
    }

    public void displayImage() {
        // Code to display image
    }

    public void resizeImage(int width, int height) {
        // Code to resize image
    }

    // Additional method to demonstrate polymorphism
    public String toString() {
        return "ImageFile{" +
                "file=" + super.getFile().getName()+
                '}';
    }
}