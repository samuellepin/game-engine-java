package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Spy;

public class SpyAvatar extends Avatar
{
  private BufferedImage[] m_idleImg;
  private AABBAvatar      m_hitbox;

  public SpyAvatar( Spy spy )
  {
    super.m_entity = spy;
    try
    {
      m_idleImg = AvatarFactory.loadSprite( "resources/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    m_hitbox = new AABBAvatar();
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( m_idleImg[ 0 ], x, y, width, height, null );
    m_hitbox.paint( g, x, y, width, height );
  }

}
