package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Player;
import src.Model.Collision.Hitbox;

public class PlayerAvatar extends Avatar
{
  private Player          m_player;
  private BufferedImage[] m_idleImg;

  public PlayerAvatar( Player player )
  {
    m_player = player;
    m_entity = player;
    try
    {
      m_idleImg = AvatarFactory.loadSprite( "resources/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
    }
    catch ( IOException e )
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void paint( Graphics g )
  {
//    g.setColor( Color.red );
//    g.drawRect( (int)m_player.getVX(), (int)m_player.getVY(), (int)m_player.getWidth(), (int)m_player.getHeight() );
    g.drawImage( m_idleImg[ 0 ], (int)m_player.getVX(), (int)m_player.getVY(), (int)m_player.getWidth(),
        (int)m_player.getHeight(), null );
//    System.out.println( m_player.getPos() );
//    System.out.println( m_player.getHitbox().getMin() );
//    System.out.println( m_player.getHitbox().getMax() );
    AABBAvatar.paint( g, m_player.getHitbox() );
  }

}
