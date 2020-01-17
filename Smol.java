import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

/*
 * Smol Class, inherits from food class
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * */

public class Smol extends Food 
{
  //Importing the fruits
  private ImageIcon image = new ImageIcon("Cherry.png");
  
  //Getter
  public Image getImage()
  {
    return image.getImage();
  }
  
  //The smol piece of food will only add 1 length to the snake
  public int addToSnake()
  {
   return 1; 
  }  
  
}
