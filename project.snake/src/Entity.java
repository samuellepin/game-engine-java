package src;

import src.Grid.Cell;
import src.AI.State;

public interface Entity
{
  public boolean cell( DIRECTION dir, Entity e );

  public void move( DIRECTION dir );

  public void turn( DIRECTION dir );

  public void tick( int elapsed );

  public void setCell( Cell c );

  public State getState();
}
