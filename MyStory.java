import org.code.theater.*;
import org.code.media.*;

public class MyStory extends Scene {


public void display(){
clear("white");

ImageFilter soda = new ImageFilter("soda.png");
ImageFilter dtf = new ImageFilter("dtf.png");
ImageFilter friends = new ImageFilter("friends.png");
ImageFilter vball = new ImageFilter("vball.png");
playSound("warp.wav");
drawText("My 4 Favs...", 175, 200);
  
printPets();



 drawImage(soda, 0, 0, 150);
 drawImage(dtf, 230, 0, 150);
 drawImage(friends, 0, 250, 150);
 drawImage(vball, 250, 250, 150);

  pause(3.0);
  clear("white");
 //THIS NEXT METHOD APPLIES MY FIRST FILTER, THE PIXELATE FILTER
soda.pixelate(5);
dtf.motionBlur(23, "vertical");
friends.threshold(150);
vball.zeroBlue();
  
drawImage(soda, 0, 0, 150);
 drawImage(dtf, 230, 0, 150);
 drawImage(friends, 0, 250, 150);
 drawImage(vball, 250, 250, 150);
  drawText("with 4 filters", 175, 200);

  pause(3.0);
printAlTimeFav();
  }

      private String[][] pets = {
        {"dog", "cat", "fish"},
        {"bird", "lizard", "ferret"},
      {"hamster", "guinea pig", "frog"}
    };

  private String[][] allTimeFavs = {
        {"Dr Pepper", "Din Tai Fung"},
        {"My Friends", "My Family"},
      {"My pets", "Volleyball"}
    };

    /*
  selects a random pet from a 2d array
  uses .length and row.length in order to keep the random number within the 2d array
  clears it then prints out the random pet found
  
  */
     public void printPets() {
        int row = (int) (Math.random() * pets.length);  // Random row index 
        int col = (int) (Math.random() * pets[row].length);  // Random column index 

        //Print the pet 
      
      System.out.println("my favorite pet is: " + pets[row][col]);
    }


   /*
chooses my all time fav thing from my 2d array
  uses .length and row.length in order to keep the random number within the 2d array
  determines the background color depending on if there is a letter "i" in it
  
  */
     public void printAlTimeFav() {
        int row = (int) (Math.random() * allTimeFavs.length);  // Random row index 
        int col = (int) (Math.random() * allTimeFavs[row].length);  // Random column index 

//now the selection statement
if(pets[row][col].indexOf("i") == -1){
  clear("black");
}      
if(pets[row][col].indexOf("i") >= 0){
  clear("white");
}
    }
}

    