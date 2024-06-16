package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Spy;
import src.Model.Collision.Circle;
import src.Model.Collision.Hitbox;

public class SpyAvatar extends Avatar
{
  private Spy             m_spy;
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
  public void paint( Graphics g )
  {
    g.drawImage( m_idleImg[ 0 ], 0, 0, this.getWidth(g), this.getHeight(g), null );
    m_hitbox.paint( g );
  }

}
