package src;

import src.AI.State;

public interface Entity
{
  public boolean cell( DIRECTION dir, Entity e );

  public void move( DIRECTION dir );

  public void turn( DIRECTION dir );

  public void tick( int elapsed );

  public State getState();
}
