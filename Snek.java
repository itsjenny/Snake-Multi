import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Snek Class
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * 
 * Notes: The is the snake object. it handles the controls of the snake, movement and directions, and also checks the collisions with the food and grows.
 * */

public class Snek  implements ActionListener
{
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
 //ATTRIBUTES 
// Stores the length / body part locations for our snake
  private int[] x = new int[Map.getAllDots()];
  private int[] y = new int[Map.getAllDots()];
  
// Stores direction of our snake
  private boolean movingLeft = false;
  private boolean movingRight = false;
  private boolean movingUp = false;
  private boolean movingDown = false;
  
//Stores the keys that the snake is using to move
  private int left;
  private int right;
  private int up;
  private int down;
  
//Length of the snake
  private int length;
  
//Stores boolean to keep track of which snake dies
  boolean dead;
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//CONSTRUCTORS
  public Snek(int l, int r, int u, int d)
  {
    //
    left = l;
    right = r;
    up = u;
    down = d;
    
    length = 2;
    dead = false;
  }
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //GETTERS AND SETTERS
  
  //For x and y positions of snake
  public int getSnakeX(int index) 
  {
    return x[index];
  }
  public int getSnakeY(int index) 
  {
    return y[index];
  }
  public void setSnakeX(int i) 
  {
    x[0] = i;
  } 
  public void setSnakeY(int i) 
  {
    y[0] = i;
  }
 
  //For length of snake
  public int getLength() 
  {
    return length;
  }
  public void setLength(int j) 
  {
    length = j;
  }
  
  //For setting the direction of the snake
  public void setMovingLeft(boolean movingLeft) 
  {
    this.movingLeft = movingLeft;
  }
  public void setMovingRight(boolean movingRight) 
  {
    this.movingRight = movingRight;
  }
   public void setMovingUp(boolean movingUp) 
   {
     this.movingUp = movingUp;
   }  
  public void setMovingDown(boolean movingDown) 
  {
    this.movingDown = movingDown;
  }
  
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //METHODS
 
  //Move method to control each snake
  public void controls(KeyEvent e) 
  {
    //See which key was pressed
    int key = e.getKeyCode();
    //Set the movement
    if ((key == left) && (movingRight == false)) 
    {
      setMovingLeft(true);
      setMovingUp(false);
      setMovingDown(false);
    }
    if ((key == right) && (movingLeft == false)) 
    {
      setMovingRight(true);
      setMovingUp(false);
      setMovingDown(false);
    }
    if ((key == up) && (movingDown == false)) 
    {
      setMovingUp(true);
      setMovingRight(false);
      setMovingLeft(false);
    }
    if ((key == down) && (movingUp==false)) 
    {
      setMovingDown(true);
      setMovingRight(false);
      setMovingLeft(false);
    }
  }
  
  //The method move() was taken from user tanu1215 from stack exchange
  //Actually moving the snake
  public void move() 
  {
    for (int i = length; i > 0; i--) 
    {
      // Moves the length of the snake 'up the chain'
      // Meaning, the lengths of the snake all move up one
      x[i] = x[(i - 1)];
      y[i] = y[(i - 1)];
    }
    // Moves snake to the left
    if (movingLeft == true) 
    {
      x[0] -= Map.getGridSize();
    }
    // Right
    if (movingRight == true) 
    {
      x[0] += Map.getGridSize();
    }
    // Down
    if (movingDown == true) 
    {
      y[0] += Map.getGridSize();
    }
    // Up
    if (movingUp == true) 
    {
      y[0] -= Map.getGridSize();
    }
  }
  
  //Uses this to check collisions, referenced from tanu1215
  private boolean proximity(int x, int y, int pixel) 
  {
    return Math.abs( x - y) <= pixel;
  }
  
  //Checking if the snake ate a food and adding to length of snake if it did
  public boolean grow(Food food) 
  {
    //Grew variable is used to tell the map class whether or not a piece of food was collected and if the snake grew. 
    boolean grew = false;
    if ((proximity(this.getSnakeX(0), food.getFoodX(), 20))&& (proximity(this.getSnakeY(0), food.getFoodY(), 20))) 
    {
      //If the head of the snake collides with the coordinates of a piece of food, the length of the snake increases
      this.setLength(this.getLength() + food.addToSnake());
      grew = true;
    }
    return grew;
  }
  
    //So that class is considered abstract
  public void actionPerformed(ActionEvent e)
  {
    move();
  }
 
}