package src.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import src.Config;
import src.Config.Tile;
import src.Model.Alien;
import src.Model.Document;
import src.Model.Entity;
import src.Model.Generator;
import src.Model.Guard;
import src.Model.Rabbit;
import src.Model.Spy;
import src.Model.Wall;

public class AvatarFactory
{
  private static final AvatarFactory INSTANCE = new AvatarFactory();

  public static AvatarFactory getInstance()
  {
    return INSTANCE;
  }

  private BufferedImage[]      m_idleSpyImg;
  private BufferedImage[]      m_runningSpyImg;
  private BufferedImage[]      m_movingAlienImg;
  private BufferedImage        m_floorImg;
  private BufferedImage[]      m_docImg;
  private BufferedImage[]      m_obstacles;
  private BufferedImage[]      m_guardLeftImg;
  private BufferedImage[]      m_guardRightImg;
  private BufferedImage[]      m_guardUpImg;
  private BufferedImage[]      m_guardDownImg;
  private BufferedImage[]      m_generatorIdleImg;
  private BufferedImage[]      m_generatorEnabledImg;
  private BufferedImage[]      m_rabbitRightImg;

  public Map< Entity, Avatar > m_entities;
  
  public BufferedImage[] getRabbitRight()
  {
    return m_rabbitRightImg;
  }
  
  public BufferedImage[] getGeneratorIdle()
  {
    return m_generatorIdleImg;
  }
  
  public BufferedImage[] getGeneratorEnabled()
  {
    return m_generatorEnabledImg;
  }

  public BufferedImage[] getGuardLeft()
  {
    return m_guardLeftImg;
  }

  public BufferedImage[] getGuardRight()
  {
    return m_guardRightImg;
  }

  public BufferedImage[] getGuardUp()
  {
    return m_guardUpImg;
  }

  public BufferedImage[] getGuardDown()
  {
    return m_guardDownImg;
  }

  public BufferedImage[] getIdleSpySprite()
  {
    return m_idleSpyImg;
  }

  public BufferedImage[] getRunningSpySprite()
  {
    return m_runningSpyImg;
  }

  public BufferedImage[] getObstaclesSprite()
  {
    return m_obstacles;
  }

  public BufferedImage getFloorSprite()
  {
    return m_floorImg;
  }

  public BufferedImage[] getDocumentSprite()
  {
    return m_docImg;
  }

  private AvatarFactory()
  {
    m_entities = new HashMap<>();
    Tile tile = Config.getInstance().getWorld().getTile();
    try
    {
      m_floorImg = AvatarFactory.loadImage( tile.getSprite() );
      m_idleSpyImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
      m_runningSpyImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_strip4.png", 1, 4 );
      m_docImg = AvatarFactory.loadSprite( "resources/sprites/ConciseDocumentationOfLustre.png", 1, 1 );
      String[] obstacles = tile.getObstacles();
      if( obstacles.length > 0 ) m_obstacles = new BufferedImage[ obstacles.length ];
      for ( int i = 0; i < obstacles.length; i++ )
      {
        m_obstacles[ i ] = AvatarFactory.loadImage( obstacles[ i ] );
      }
      m_movingAlienImg = AvatarFactory.loadSprite( "resources/sprites/Alien/Alien.png", 1, 11 );
      m_guardLeftImg = AvatarFactory.loadSprite( "resources/sprites/Guard/Guard_Left.png", 1, 4 );
      m_guardRightImg = AvatarFactory.loadSprite( "resources/sprites/Guard/Guard_Right.png", 1, 4 );
      m_guardUpImg = AvatarFactory.loadSprite( "resources/sprites/Guard/Guard_Up.png", 1, 4 );
      m_guardDownImg = AvatarFactory.loadSprite( "resources/sprites/Guard/Guard_Down.png", 1, 4 );
      
      this.m_generatorEnabledImg = AvatarFactory.loadSprite( "resources/sprites/Generator/Generator_Enabled.png", 1, 3 );
      this.m_generatorIdleImg = AvatarFactory.loadSprite( "resources/sprites/Generator/Generator_Idle.png", 1, 3 );    

      this.m_rabbitRightImg = AvatarFactory.loadSprite( "resources/sprites/Generator/Rabbit_Right.png", 1, 4 );
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
    if( e instanceof Alien )
    {
      avatar = new AlienAvatar( (Alien)e );
    }
    else if( e instanceof Guard )
    {
      avatar = new GuardAvatar( (Guard)e );
    }
    else if( e instanceof Generator )
    {
      avatar = new GeneratorAvatar( (Generator)e );
    }
    else if( e instanceof Spy )
    {
      avatar = new SpyAvatar( (Spy)e );
    }
    else if ( e instanceof Rabbit )
    {
      avatar = new RabbitAvatar( (Rabbit)e );
    }
    else if( e instanceof Wall )
    {
      int r = 1;
      if( this.m_obstacles != null )
      {
        r = this.m_obstacles.length;
      }
      avatar = new WallAvatar( (Wall)e, Config.getRandom().nextInt( r ) );
    }
    else if( e instanceof Document )
    {
      avatar = new DocumentAvatar( (Document)e );
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

  public BufferedImage[] getMovingAlienSprite()
  {
    return m_movingAlienImg;
  }
}
