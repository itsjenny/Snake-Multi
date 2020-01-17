import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

/*
 * Map Class
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * Notes: Lots of things happening in this class lol.
     * Sets dimensions of the game screen
     * Does all of the actual to-screen painting
     * Instantiates all other objects (snakes, foods, etc)
     * Detects collisions with the edges of the screens, as well as the collisions of the snakes with each other and themselves
     * REMEMBER! Snake is Black, Snake2 is White
 */

public class Map extends JPanel implements ActionListener 
{
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//ATTRIBUTES
  
  //NOTES: The 4 attributes BoardWidth, BoardHeight, PixelSize, and TotalPixels were referenced from user tanu1215 from stack exchange
  private static int BoardWidth = 900;
  private static int BoardHeight = 900;
  
// Used to represent pixel size of food & our snake's joints
  private static int PixelSize = 25;
  
// The total amount of pixels the game could possibly have.
  private static int TotalPixels = (BoardWidth * BoardHeight) /(PixelSize * PixelSize);
  
// Check to see if the game is running to be used to see if snake dies
  private boolean gameRunning = true;
  
// Timer used to record tick times
  private Timer timer; 
  private int speed = 10;
  
// Snake objects 
  private Snek snake;
  private Snek snake2;
  private Snek [] snakeList;
  
//Food objects   
  private Food food;
  private Random rand = new Random();
  private int foodType;
  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//CONSTRUCTORS
  public Map() 
  {
    addKeyListener(new Keys());
    setBackground(Color.PINK);
    setFocusable(true);
    setPreferredSize(new Dimension(BoardWidth, BoardHeight));
    
    // Make the snakes and set their controls 
    snake = new Snek(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
    snake2 = new Snek(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
    //Add Snakes to the snake list
    snakeList = new Snek [2];
    snakeList[0] = snake;
    snakeList[1] = snake2;
    
    //Add them to the game
    addSnake();
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//GETTERS AND SETTERS
    public static int getAllDots() 
  {
    return TotalPixels;
  }
  
  public static int getGridSize() 
  {
    return PixelSize;
  }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
//METHODS
// Painting our stuff to the screen
  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g); 
    draw(g);
  }
  
// Drawing the snake and the foods to screen
  public void draw(Graphics g) 
  {
    // Only draw if the game is running / the snake is gameRunning
    if (gameRunning == true) 
    {
      //Draw the piece of food
      g.drawImage(food.getImage(), food.getFoodX(), food.getFoodY(), this);
      
      //Set the colour of snake1
      g.setColor(Color.BLACK);
      
      //By looping through the snake list, we can get rid of repetitive code
      for (int j = 0; j < snakeList.length; j++)
      {
        // Draw first snake.
        for (int i = 0; i < snakeList[j].getLength(); i++) 
        {
        // Snake's head
          if (i == 0) 
          {
            g.fillRect(snakeList[j].getSnakeX(i), snakeList[j].getSnakeY(i), PixelSize, PixelSize);
          }
          //Snake body
          else 
          {
            g.fillRect(snakeList[j].getSnakeX(i), snakeList[j].getSnakeY(i),PixelSize, PixelSize);
          }
        }
        //Sets colour of snake2
        g.setColor(Color.WHITE);
      }
      
    } 
    //If game is over because one of the snakes is dead
    else 
    {
      //Print end screen
      endScreen(g);
    }
  }
  
  public void addSnake() 
  {
    //Make the snake bodies
    for (int i = 0; i < snake.getLength(); i++) 
    {
      snake.setSnakeX(100);
      snake.setSnakeY(100);
    }
    for (int i = 0; i < snake2.getLength(); i++) 
    {
      snake2.setSnakeX(800);
      snake2.setSnakeY(800);
    }
    // Start the snakes in a direction
    snake.setMovingRight(true);
    snake2.setMovingLeft(true);
    
    //Initial food piece
    addFood();
    
    // Set the timer
    timer = new Timer(speed, this);
    timer.start();
  }
  
  public void addFood()
  {
    //Random int to determine foodType of food being spawned
    foodType = rand.nextInt(3);
    foodType++;
    
    //Depending on the foodType, create the corresponding object
    if (foodType == 1)
    {
      food = new Smol();
    }
    else if (foodType == 2)
    {
      food = new Medium();
    }
    else 
    {
      food = new Big();
    }
    food.createFood();
  }
  
// Used to check collisions with snakes self and board edges
  public void detectCollisions() 
  {
    // If the snake hits itself, looping through the snake list to check both snakes
    for (int j = 0; j < snakeList.length; j++)
    {
      for (int i = snakeList[j].getLength(); i > 0; i--) 
      {
        // Snake cant intersect with itself if it's not larger than 5
        if ((i > 5) && (snakeList[j].getSnakeX(0) == snakeList[j].getSnakeX(i) && (snakeList[j].getSnakeY(0) == snakeList[j].getSnakeY(i)))) 
        {
          snakeList[j].dead = true;
          gameRunning = false; // then the game ends
        }
      }
    }
    
    // If the snakes hit the edge of the screen
    for (int j = 0; j < snakeList.length; j++)
    {
      if (snakeList[j].getSnakeY(0) >= BoardHeight || snakeList[j].getSnakeY(0) < 0 || snakeList[j].getSnakeX(0) >= BoardWidth || snakeList[j].getSnakeX(0) < 0) 
      {
        snakeList[j].dead = true;
        gameRunning = false;
      }
    }
    
    // If the head of first snake hits the other snake
    for (int i = 0; i < snake2.getLength(); i++)
    {
      if (snake.getSnakeX(0) == snake2.getSnakeX(i) && snake.getSnakeY(0) == snake2.getSnakeY(i))
      {
        snake.dead = true;
        gameRunning = false;
      }
    }
    // If the head of second snake hits the other snake
    for (int i = 0; i < snake.getLength(); i++)
    {
      if (snake2.getSnakeX(0) == snake.getSnakeX(i) && snake2.getSnakeY(0) == snake.getSnakeY(i))
      {
        snake2.dead = false;
        gameRunning = false;
      }
      
      // If the game has ended, then we can stop our timer
      if (gameRunning==false) 
      {
        timer.stop();
      }
    }
  }
  
  public void endScreen(Graphics g) 
  {  
    // Create a message telling the player the game is over
    String winner = "";
    String loser = "";
    String length = "Black snake's length was " + snake.getLength();
    String length2 = "White snake's length was " + snake2.getLength();
    
    if (snake.dead == true)
    {
      winner = "Congratulations White Snake";
      loser = "Try Harder Black Snake";
    }
    else if (snake2.dead == true)
    {
      winner = "Congratulations Black Snake";
      loser = "Try Harder White Snake";
    }
   
    String message = "Game Over";
    
    // Create a new font instance
    Font font = new Font("Times New Roman", Font.BOLD, 30);
    FontMetrics metrics = getFontMetrics(font);
    
    // Set the color of the text to red, and set the font
    g.setColor(Color.WHITE);
    g.setFont(font);
    
    // Draw the message to middle of the board
    g.drawString(message, (BoardWidth - metrics.stringWidth(message)) / 2, (BoardHeight / 2) - 100);
    g.drawString(winner, (BoardWidth - metrics.stringWidth(winner)) / 2, (BoardHeight / 2) - 50);
    g.drawString(loser, (BoardWidth - metrics.stringWidth(loser)) / 2, BoardHeight / 2);
    g.drawString(length, (BoardWidth - metrics.stringWidth(length)) / 2, (BoardHeight / 2) + 50);
    g.drawString(length2, (BoardWidth - metrics.stringWidth(length2)) / 2, (BoardHeight / 2) + 100);
  }
  
// Run constantly as long as we're in game.
  public void actionPerformed(ActionEvent e) 
  {
    if (gameRunning == true) 
    {
      //While game is running, check for these conditions each frame
      //If food was collected, make a new one using addFood() method
      boolean grew1 = snake.grow(food);
      boolean grew2 = snake2.grow(food);
      if (grew1 == true || grew2 == true)
      {
        addFood();
      }
      detectCollisions();
      
      //Move the snakes each frame
      snake.move();
      snake2.move();
    }
    // Repaint our screen
    repaint();
  }
  
  private class Keys extends KeyAdapter 
  {
    public void keyPressed(KeyEvent e) 
    {  
      int key = e.getKeyCode();
      snake.controls(e);
      snake2.controls(e);
      
      if ((key == KeyEvent.VK_ENTER) && (gameRunning == false)) 
      {
        gameRunning = true;
        addSnake();
      }
    }
  }
}