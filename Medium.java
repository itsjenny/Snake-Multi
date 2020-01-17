import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
/*
 * Medium Class, inherits from food class
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * */

public class Medium extends Food 
{ 
  //Importing the fruits
  private ImageIcon image = new ImageIcon("Banana.png");
  
  //Getter
  public Image getImage()
  {
    return image.getImage();
  }
  //Medium piece will add 2 lengths to the snake
  public int addToSnake()
  {
   return 2; 
  }
}
