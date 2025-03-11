import org.code.media.Pixel;
import org.code.media.Color;  // Make sure to import the Color class
public class ImageFilter extends ImagePlus {

  
  
  public ImageFilter(String filename) {
        super(filename);
}

/*
*zero blue makes it so that the image has no blue
*this will bring out all other colors
*this one will change my volleyball picture
*
  */
  
 public void zeroBlue() {
   Pixel[][] pixels = getImagePixels();
    
//for loops go through the 2d array in row-colum order
   for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
//gets current pixel
        Pixel currentPixel = pixels[row][col];
      
//now remove the blue
        currentPixel.setBlue(0);
      }
    }
 }
  /*
   * Applies a threshold filter to an image
   if lighter than the average pixel the color becomes white, if darker then it will become black, 
   very contrasted image
   */
  
  public void threshold(int value) {
    // Get the image pixels
    Pixel[][] imagePixels = getImagePixels(); 

    // Traverse the 2D array of pixels
    for (int row = 0; row < getHeight(); row++) {
        for (int col = 0; col < getWidth(); col++) {
            // Get the current pixel
            Pixel currentPixel = imagePixels[row][col];

//get the avg 
          int red = currentPixel.getRed();
            int green = currentPixel.getGreen();
            int blue = currentPixel.getBlue();

//find the average
          int avgRGB = (red + green + blue) / 3;

//sets it to black
          if (avgRGB < value) {
                currentPixel.setRed(0);
                currentPixel.setGreen(0);
                currentPixel.setBlue(0);
            } else {
//sets everything to white
              currentPixel.setRed(255);
                currentPixel.setGreen(255);
                currentPixel.setBlue(255);
            }
        }
    }
}

/*
*this filter makes the image pixelated
*i used it to make my dr pepper pixelated
*uses a param that determines how pixelated the image should be 
*bigger # = more pixelated
*/
 public void pixelate(int gridSize) {
    int numRows = getHeight();  
    int numCols = getWidth();   //get width and height get the size of image
    
//use size parameter in order to determine how to go through it 
    
    for (int rowStart = 0; rowStart < numRows; rowStart += gridSize) {
        for (int colStart = 0; colStart < numCols; colStart += gridSize) {
            // Determine the end row and column for the current block
            int rowEnd = Math.min(rowStart + gridSize, numRows);
            int colEnd = Math.min(colStart + gridSize, numCols);
            
            int redSum = 0;
          int greenSum = 0; 
          int blueSum = 0;
            int pixelCount = 0;
//blank values to be filled in later
          
//determines start and end of chunk   
          for (int row = rowStart; row < rowEnd; row++) {
                for (int col = colStart; col < colEnd; col++) {
                    Pixel pixel = getPixel(row, col); 
                    redSum += pixel.getRed();        //add current color values
                    greenSum += pixel.getGreen();     
                    blueSum += pixel.getBlue();      
                    pixelCount++;          //increases pixel count
                }
            }
            
            int avgRed = redSum / pixelCount;
            int avgGreen = greenSum / pixelCount;//divides the chunk colors by total amt of pixels there are in the chunk
            int avgBlue = blueSum / pixelCount;
            
            // Set each pixel in the block to the average color
            for (int row = rowStart; row < rowEnd; row++) {
                for (int col = colStart; col < colEnd; col++) {
                    Pixel pixel = getPixel(row, col); // Get the Pixel object at the row and colum
            
                  pixel.setRed(avgRed);             
                   pixel.setGreen(avgGreen);  //setts the colors in that chunk to the average color
                pixel.setBlue(avgBlue);           
         }
      }
   }
}
}


  /*
this motion blur filter makes the pixels change in a certain direction
the direction can be vertical, horizontal, or diagonal
i chose to do vertical
it goes through the image and adjusts the array based on direction and how much it will be stretched
*/
  
public void motionBlur(int length, String direction) {
    Pixel[][] imagePixels = getImagePixels();  
//go through the image pixel array
    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        int redTotal = 0, greenTotal = 0, blueTotal = 0;
        int count = 0;

//check the sides based on the param
        for (int i = 0; i < length; i++) {
          int newX = x;
          int newY = y;

  //makes each pixel move a certain direction
          //diagonal is both because it moves to the side then up
          if (direction.equals("horizontal")) {
            newX = x + i;
          } else if (direction.equals("vertical")) {
            newY = y + i;
          } else if (direction.equals("diagonal")) {
            newX = x + i;
            newY = y + i;
          }

    //this makes sure the new coordinates are within bounds of the image
          if (newX >= 0 && newX < getWidth() && newY >= 0 && newY < getHeight()) {
            Pixel currentPixel = imagePixels[newY][newX];
            redTotal += currentPixel.getRed();
            greenTotal += currentPixel.getGreen();
            blueTotal += currentPixel.getBlue();
            count++;
          }
        }

  //calculate the average color values of the neighboring pixels
        int avgRed = redTotal / count;
        int avgGreen = greenTotal / count;
        int avgBlue = blueTotal / count;

// this combines all avgs into 1 single element
        Color avgColor = new Color(avgRed, avgGreen, avgBlue);

        //sets it to average color
        setPixel(x, y, avgColor);  
      }
    }
  }


 }
