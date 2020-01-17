import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
/*
 * Big Class, inherits from food class
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * */

public class Big extends Food 
{
  //Importing the fruits
  private ImageIcon image = new ImageIcon("Pineapple.png");
  
  //Getter
  public Image getImage()
  {
    return image.getImage();
  }
  
  //Big piece of food will add 3 lengths to the snake
  public int addToSnake()
  {
   return 3; 
  }
    
}

