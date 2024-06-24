package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class DocumentAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private Animation              m_idle;

  public DocumentAvatar( Entity e )
  {
    super( e );

    if( m_idleImg == null )
    {
      try
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/ConciseDocumentationOfLustre.png", 1, 1 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }
    }

    m_idle = new Animation( e, m_idleImg, 0 );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( m_idle.getImage(), x, y, width, height, null );
    m_idle.update();
  }

}
