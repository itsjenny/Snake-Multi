import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

/*
 * Food Class, Parent class of Smol, Medium, and Big
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * */
public class Food 
{
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //ATTRIBUTES

  //X and Y positions of the food
  private int foodX; 
  private int foodY; 
  
// Used to determine random position of food
  private int randPos = 30;
  private int size;
  
//Importing image in
  private ImageIcon image;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //CONSTRUCTORS 
  
  public void food()
  {
    size = 0;
    image = new ImageIcon();
  }
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   //GETTERS AND SETTERS
  
  public int getFoodX() 
  {
    return foodX;
  } 
  public int getFoodY() 
  {
    return foodY;
  }
  public Image getImage()
  {
    return image.getImage();
  }
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //METHODS 
  
  //Creates the location of the piece of food
  //NOTES: The createFood() method was referenced from user tanu1215 from stack exchange, used to generate the random position of the food
  public void createFood() 
  {
    //Use the random method to get a random position
    int location = (int) (Math.random() * randPos);
    //But because we use a grid for our game, we have to move the food to the appropriate spot in the grid
    foodX = ((location * Map.getGridSize()));
  
    //Do the same for the y position
    location = (int) (Math.random() * randPos);
    foodY = ((location * Map.getGridSize()));
  }
  
  //Determines how much the food is worth and how much is added to the snake
  public int addToSnake()
  {
    return size;
  }

}