package src;

import src.AI.State;

public interface Entity
{
  public boolean cell( DIRECTION direction, CATEGORY category );

  public boolean move( DIRECTION direction );

  public void tick( int elapsed );

  public State getState();
}
