package src;

import src.Grid.Cell;

import java.util.ArrayList;

import src.AI.AppleAutomaton;
import src.AI.SnakeHeadAutomaton;

public class Model
{
  private int       m_width;
  private int       m_height;

  private SnakeHead m_snake;
  private Apple     m_apple;
  private boolean   m_isGameOver;
  private Grid      m_grid;

  public Model( int width, int height )
  {
    m_grid = Grid.getInstance();
    m_isGameOver = false;
    m_width = width;
    m_height = height;

    Cell cell = m_grid.getFreeRandomCell();
    m_snake = new SnakeHead( SnakeHeadAutomaton.getInstance(), cell );
    m_grid.setEntity( m_snake, cell );
    generateApple();
  }

  public void generateApple()
  {
    Cell cell = m_grid.getFreeRandomCell();
    m_apple = new Apple( AppleAutomaton.getInstance(), Grid.getInstance().getFreeRandomCell() );
    m_grid.setEntity( m_apple, cell );
  }

  public void tick( long elapsed )
  {
    m_snake.tick( elapsed );
    m_apple.tick( elapsed );

    if( m_apple.isEaten() )
    {
      generateApple();
    }

    if( m_snake.isDead() )
    {
      m_isGameOver = true;
    }
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public void print()
  {
    System.out.println( Grid.getInstance().toString() );
  }

  public ArrayList< Entity > GetEntityList()
  {
    ArrayList< Entity > entities = new ArrayList<>();
    entities.add( m_snake );
    entities.add( m_apple );
    return entities;
  }
}
