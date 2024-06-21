package src.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import src.Model.Document;
import src.Model.Entity;
import src.Model.Spy;
import src.Model.Wall;

public class AvatarFactory
{
  private static final AvatarFactory INSTANCE = new AvatarFactory();

  public static AvatarFactory getInstance()
  {
    return INSTANCE;
  }

  private BufferedImage[]             m_idleSpyImg;
  private BufferedImage[]             m_runningSpyImg;
  private BufferedImage               m_floorImg;
  private BufferedImage               m_docImg;

  public Map< Entity, Avatar > m_entities;
  
  public BufferedImage[] getIdleSpySprite()
  {
    return m_idleSpyImg;
  }
  
  public BufferedImage[] getRunningSpySprite()
  {
    return m_runningSpyImg;
  }
  
  public BufferedImage getFloorSprite()
  {
    return m_floorImg;
  }
  
  public BufferedImage getDocumentSprite()
  {
    return m_docImg;
  }

  private AvatarFactory()
  {
    m_entities = new HashMap<>();
    try
    {
      m_floorImg = AvatarFactory.loadImage( "resources/sprites/Tile_Brick.png" );
      m_idleSpyImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
      m_runningSpyImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_strip4.png", 1, 4 );
      m_docImg = AvatarFactory.loadImage( "resources/sprites/ConciseDocumentationOfLustre.png" );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }

  public Avatar make( Entity e )
  {
    if( m_entities.containsKey( e ) )
    {
      return m_entities.get( e );
    }

    Avatar avatar = null;
    if( e instanceof Spy )
    {
      avatar = new SpyAvatar( (Spy)e );
    }
    else if( e instanceof Wall )
    {
      avatar = new WallAvatar( (Wall)e );
    }
    else if( e instanceof Document )
    {
      avatar = new DocumentAvatar( (Document)e );
    }
    else if( e instanceof Wall )
    {
      avatar = new WallAvatar( (Wall)e );
    }

    m_entities.put( e, avatar );

    return avatar;
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
