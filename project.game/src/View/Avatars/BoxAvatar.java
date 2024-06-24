package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class BoxAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private static BufferedImage[] m_movingImg;

  private Animation              m_idle;
  private Animation              m_moving;

  public BoxAvatar( Entity e )
  {
    super( e );
    if( m_idleImg == null )
    {
      try
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/Box/Box_Idle.png", 1, 1 );
        m_movingImg = AvatarFactory.loadSprite( "resources/sprites/Box/Box_Moving.png", 1, 2 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }
    }
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    if( m_entity.isMoving() )
    {
      g.drawImage( m_moving.getImage(), x, y, width, height, null );
      m_moving.update();
    }
    else
    {
      g.drawImage( m_idle.getImage(), x, y, width, height, null );
      m_idle.update();
    }
  }

}
