package src.View;

import src.*;

public class AvatarFactory
{
  public static Avatar make( Entity e )
  {
    if( e instanceof SnakeHead )
    {
      return new SnakeHeadAvatar( (SnakeHead)e );
    }
    else if( e instanceof SnakeTail )
    {
      return new SnakeTailAvatar( (SnakeTail)e );
    }
    else if( e instanceof Apple )
    {
      return new AppleAvatar( (Apple)e );
    }
    else if( e instanceof Egg )
    {
      return new EggAvatar( (Egg)e );
    }
    return null;
  }
}
