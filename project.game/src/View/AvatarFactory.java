package src.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import src.Config;
import src.Config.Tile;
import src.Model.Entity;
import src.Model.Entities.Alien;
import src.Model.Entities.Box;
import src.Model.Entities.Camera;
import src.Model.Entities.Document;
import src.Model.Entities.Dove;
import src.Model.Entities.Generator;
import src.Model.Entities.Guard;
import src.Model.Entities.Mouse;
import src.Model.Entities.Rabbit;
import src.Model.Entities.Raven;
import src.Model.Entities.Robot;
import src.Model.Entities.Spy;
import src.Model.Entities.Squirrel;
import src.Model.Entities.Stairs;
import src.Model.Entities.UFO;
import src.Model.Entities.Wall;
import src.View.Avatars.AlienAvatar;
import src.View.Avatars.BoxAvatar;
import src.View.Avatars.CameraAvatar;
import src.View.Avatars.DocumentAvatar;
import src.View.Avatars.DoveAvatar;
import src.View.Avatars.GeneratorAvatar;
import src.View.Avatars.GuardAvatar;
import src.View.Avatars.MouseAvatar;
import src.View.Avatars.RabbitAvatar;
import src.View.Avatars.RavenAvatar;
import src.View.Avatars.RobotAvatar;
import src.View.Avatars.SpyAvatar;
import src.View.Avatars.StairsAvatar;
import src.View.Avatars.UFOAvatar;
import src.View.Avatars.WallAvatar;

public class AvatarFactory
{
  private static final AvatarFactory INSTANCE = new AvatarFactory();

  public static AvatarFactory getInstance()
  {
    return INSTANCE;
  }

  private BufferedImage        m_floorImg;
  private BufferedImage[]      m_obstacles;

  public Map< Entity, Avatar > m_entities;

  public BufferedImage[] getObstaclesSprite()
  {
    return m_obstacles;
  }

  public BufferedImage getFloorSprite()
  {
    return m_floorImg;
  }

  private AvatarFactory()
  {
    m_entities = new HashMap<>();
    Tile tile = Config.getInstance().getWorld().getTile();
    try
    {
      m_floorImg = AvatarFactory.loadImage( tile.getSprite() );
      String[] obstacles = tile.getObstacles();
      if( obstacles.length > 0 ) m_obstacles = new BufferedImage[ obstacles.length ];
      for ( int i = 0; i < obstacles.length; i++ )
      {
        m_obstacles[ i ] = AvatarFactory.loadImage( obstacles[ i ] );
      }
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
    if( e instanceof Alien ) avatar = new AlienAvatar( e );
    else if( e instanceof Box ) avatar = new BoxAvatar( e );
    else if( e instanceof Camera ) avatar = new CameraAvatar( e );
    else if( e instanceof Document ) avatar = new DocumentAvatar( e );
    else if( e instanceof Dove ) avatar = new DoveAvatar( e );
    else if( e instanceof Generator ) avatar = new GeneratorAvatar( e );
    else if( e instanceof Guard ) avatar = new GuardAvatar( e );
    else if( e instanceof Mouse ) avatar = new MouseAvatar( e );
    else if( e instanceof Rabbit ) avatar = new RabbitAvatar( e );
    else if( e instanceof Raven ) avatar = new RavenAvatar( e );
    else if( e instanceof Robot ) avatar = new RobotAvatar( e );
    else if( e instanceof Spy ) avatar = new SpyAvatar( e );
    else if( e instanceof Squirrel ) avatar = new MouseAvatar( e );
    else if( e instanceof Stairs ) avatar = new StairsAvatar( e );
    else if( e instanceof UFO ) avatar = new UFOAvatar( e );
    else if( e instanceof Wall )
    {
      int r = 1;
      if( this.m_obstacles != null )
      {
        r = this.m_obstacles.length;
      }
      avatar = new WallAvatar( (Wall)e, Config.getRandom().nextInt( r ) );
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
