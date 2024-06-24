package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Alien;
import src.Model.Entity;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class AlienAvatar extends Avatar
{

  private static BufferedImage[] m_movingImg;

  private Animation              m_movingAnim;
  private Avatar                 m_metamorph;

  public AlienAvatar( Entity e )
  {
    super( e );
    if( m_movingImg == null )
    {
      try
      {
        m_movingImg = AvatarFactory.loadSprite( "resources/sprites/Alien/Alien.png", 1, 11 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }
    }
    m_movingAnim = new Animation( e, m_movingImg, ANIMATION_TIME / 2 );

  }

  public void updateAppearance()
  {
    Alien alien = (Alien)m_entity;
    if( alien.isViewUpdated() ) return;
    m_metamorph = AvatarFactory.getInstance().make( alien.getMetamorph() );
    alien.setUpdateView( false );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    updateAppearance();
    Alien alien = (Alien)m_entity;
    if( alien.isNonOriginForm() )
    {
      m_metamorph.paint( g, x, y, width, height );
      return;
    }
    g.drawImage( m_movingAnim.getImage(), x, y, width, height, null );
    m_movingAnim.update();
  }

}
