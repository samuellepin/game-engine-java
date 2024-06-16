package src.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.Model.Document;
import src.Model.Entity;
import src.Model.Light;
import src.Model.Spy;
import src.Model.Wall;

public class AvatarFactory
{
  public static BufferedImage[] m_idleSpyImg;
  public static  BufferedImage[] m_runningSpyImg;
  public static BufferedImage   m_floorImg;
  public static BufferedImage   m_docImg;

  public static void Initialize()
  {
    try
    {
      m_floorImg = AvatarFactory.loadImage( "resources/Tile_Brick.png" );
      m_idleSpyImg = AvatarFactory.loadSprite( "resources/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
      m_runningSpyImg = AvatarFactory.loadSprite( "resources/Spy/SMS_Adv_Idle_strip4.png", 1, 4 );
      m_docImg = AvatarFactory.loadImage( "resources/ConciseDocumentationOfLustre.png" );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }

  public static Avatar make( Entity e )
  {
    if( e instanceof Light )
    {
      return new LightAvatar( (Light)e );
    }
    else if( e instanceof Spy )
    {
      return new SpyAvatar( (Spy)e );
    }
    else if( e instanceof Wall )
    {
      return new WallAvatar( (Wall)e );
    }
    else if( e instanceof Document )
    {
      return new DocumentAvatar( (Document)e );
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
