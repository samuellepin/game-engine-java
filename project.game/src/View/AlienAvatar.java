package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Alien;
import src.Model.Entity;
import src.Model.Vector;

public class AlienAvatar extends Avatar
{
  private Animation m_movingAnim;
  private static Avatar    m_metamorph;

  public AlienAvatar( Entity e )
  {
    super( e );
    m_movingAnim = new Animation( e, m_factory.getMovingAlienSprite(), ANIMATION_TIME / 2 );

  }

  public void updateAppearance()
  {
    Alien alien = (Alien)m_entity;
    if( alien.isViewUpdated() ) return;
    if( m_metamorph == null )
    m_metamorph = AvatarFactory.getInstance().make( alien.getMetamorph() );
    alien.setUpdateView( false );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    updateAppearance();
    Alien alien = (Alien)m_entity;
    if( alien.isMetamorphosed() )
    {
      m_metamorph.paint( g, x, y, width, height );
      return;
    }
    g.drawImage( m_movingAnim.getImage(), x, y, width, height, null );
    m_movingAnim.update();
  }

}
