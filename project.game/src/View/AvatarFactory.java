package src.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.*;
import src.Model.Entity;
import src.Model.Light;
import src.Model.Player;
import src.Model.Rectangle;
import src.Model.Wall;

public class AvatarFactory
{
  public static Avatar make( Entity e )
  {
    if( e instanceof Light )
    {
      return new LightAvatar( (Light)e );
    }
    else if( e instanceof Rectangle )
    {
      return new RectangleAvatar( (Rectangle)e );
    }
    else if( e instanceof Player )
    {
      return new PlayerAvatar( (Player)e );
    }
    else if ( e instanceof Wall)
    {
      return new WallAvatar( (Wall) e);
    }

    // TODO return default avatar if the entity is unknown
    // throw new Exception( "Unknow entity" );
    return null;
  }

  public static BufferedImage loadImage( String filename ) throws IOException
  {
    File imageFile = new File( filename );
    if( !imageFile.exists() ) return null;
    return ImageIO.read( imageFile );
  }

  public static BufferedImage[] loadSprite( String filename, int nrows, int ncols ) throws IOException
  {
    File imageFile = new File( filename );
    if( imageFile.exists() )
    {
      BufferedImage   image  = ImageIO.read( imageFile );
      int             width  = image.getWidth( null ) / ncols;
      int             height = image.getHeight( null ) / nrows;

      BufferedImage[] images = new BufferedImage[ nrows * ncols ];
      for ( int i = 0; i < nrows; i++ )
      {
        for ( int j = 0; j < ncols; j++ )
        {
          int x = j * width;
          int y = i * height;
          images[ ( i * ncols ) + j ] = image.getSubimage( x, y, width, height );
        }
      }
      return images;
    }
    return null;
  }
}
