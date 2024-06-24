package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Generator;
import src.Model.Vector;

public class GeneratorAvatar extends Avatar
{
  private Animation m_idle;
  private Animation m_enabled;

  public GeneratorAvatar( Entity e )
  {
    super( e );
    m_idle = new Animation( e, m_factory.getGeneratorIdle(), ANIMATION_TIME / 2 );
    m_enabled = new Animation( e, m_factory.getGeneratorEnabled(), ANIMATION_TIME / 2 );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    Generator gen = (Generator)m_entity;
    if( gen.isEnabled() )
    {
      g.drawImage( m_enabled.getImage(), x, y, width, height, null );
      m_enabled.update();
    }
    else
    {
      g.drawImage( m_idle.getImage(), x, y, width, height, null );
      m_idle.update();
    }
  }

}
