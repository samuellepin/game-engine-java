package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;

public class UFOAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private Animation              m_idle;

  public UFOAvatar( Entity e )
  {
    super( e );
    if( m_idleImg == null )
    {
      try
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/UFO_Idle.png", 1, 4 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }
    }

    m_idle = new Animation( e, m_idleImg, ANIMATION_TIME );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( m_idle.getImage(), x, y, width, height, null );
    m_idle.update();
  }

}
