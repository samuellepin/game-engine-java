package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.Model.Entities.Generator;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class GeneratorAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private static BufferedImage[] m_enabledImg;

  private Animation              m_idle;
  private Animation              m_enabled;

  public GeneratorAvatar( Entity e )
  {
    super( e );

    if( m_idleImg == null )
    {
      try
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/Generator/Generator_Idle.png", 1, 3 );
        m_enabledImg = AvatarFactory.loadSprite( "resources/sprites/Generator/Generator_Enabled.png", 1, 3 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }

    }

    m_idle = new Animation( e, m_idleImg, ANIMATION_TIME / 2 );
    m_enabled = new Animation( e, m_enabledImg, ANIMATION_TIME / 2 );
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
