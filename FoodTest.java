import junit.framework.TestCase;
import org.junit.Test;

/*
 * Food Test Cases (testing all three child classes as well
 * @Course: ICS4U
 * @Date: June 2019
 * @Authors: Abhishek R, Anthony T, Jenny N, Kelvin H
 * NOTES: The game class was referenced from user tanu1215 from stack exchange
 */

public class FoodTest extends TestCase 
{ 
  //Testing to see if each child class overrides the original parent class and returns a different size each time
  
  @Test
  public void test1() 
  {
    Food food = new Smol();
    int size = food.addToSnake();
    assertEquals(size, 1);
  }
   
  @Test
  public void test2() 
  {
    Food food = new Medium();
    int size = food.addToSnake();
    assertEquals(size, 2);
  }
  
  @Test
  public void test3() 
  {
    Food food = new Big();
    int size = food.addToSnake();
    assertEquals(size, 3);
  }
  
}
