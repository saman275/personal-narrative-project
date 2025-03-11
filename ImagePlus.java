import org.code.theater.*;
import org.code.media.*;

/*
 * Represents an image that can be modified with filters and effects
 */
public class ImagePlus extends Image {

  private Pixel[][] pixels;    // The 2D array of pixels

  /*
   * Sets the superclass filename to the specified filename
   * and calls the getPixelsFromImage() method to initialize
   * the 2D array of Pixel objects that make up the image
   */
  public ImagePlus(String filename) {
    super(filename);   // Calls the Image class constructor

    // Initialize the pixels array by getting the pixels from the image
    pixels = getPixelsFromImage();
  }

  /*
   * Returns the 2D array of pixels
   */
  public Pixel[][] getImagePixels() {
    return pixels;
  }

  /*
   * Returns the pixels in the image as a 2D array of Pixel objects
   */
  public Pixel[][] getPixelsFromImage() {
    Pixel[][] tempPixels = new Pixel[getHeight()][getWidth()];
    
    for (int row = 0; row < tempPixels.length; row++) {
      for (int col = 0; col < tempPixels[0].length; col++) {
        tempPixels[row][col] = getPixel(col, row);
      }
    }

    return tempPixels;
  }

}
