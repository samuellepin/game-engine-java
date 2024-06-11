package src.View;

import src.*;
import src.Model.Entity;
import src.Model.Light;
import src.Model.Rectangle;

public class AvatarFactory
{
  public static Avatar make( Entity e ) throws Exception
  {
    if( e instanceof Light )
    {
      return new LightAvatar( (Light)e );
    }
    else if( e instanceof Rectangle )
    {
      return new RectangleAvatar( (Rectangle)e );
    }
    throw new Exception("Unknow entity");
  }
}
