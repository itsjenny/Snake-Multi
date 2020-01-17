import junit.framework.TestCase;
import org.junit.Test;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class SnekTest extends TestCase 
{ 
  @Test
  public void test1() 
  {
    //Check the initial length of snake
    Snek snake = new Snek (1, 1, 1, 1);
    int length = snake.getLength();    
    assertEquals(length, 2);
    
    //After eating a big piece of food, the length should increase by 3
    Food food = new Big();
    boolean grew = snake.grow(food);
    length = snake.getLength();
    assertEquals(length, 5);   
  }
   
  @Test
  public void test2() 
  {
    //Checking the sizes after eating the other sized foods
    Snek snake = new Snek (1, 1, 1, 1);
    
    //Instantiating the pieces of food
    Food bigFood = new Big();
    Food smolFood = new Smol();
    Food medFood = new Medium();
    
    //Eating the diff foods
    boolean grew = snake.grow(bigFood);
    grew = snake.grow(bigFood);
    grew = snake.grow(smolFood);
    grew = snake.grow(medFood);
    
    int length = snake.getLength();
    assertEquals(length, 11);   
  }
  
}
