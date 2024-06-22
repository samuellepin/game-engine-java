package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.Guard;

import src.Model.Entity;
import src.Model.Vector;

public class GuardAvatar extends Avatar
{
  private Animation m_left;
  private Animation m_right;
  private Animation m_up;
  private Animation m_down;

  public GuardAvatar( Entity e )
  {
    super( e );
    m_left = new Animation( e, m_factory.getGuardLeft(), ANIMATION_TIME );
    m_right = new Animation( e, m_factory.getGuardRight(), ANIMATION_TIME );
    m_up = new Animation( e, m_factory.getGuardUp(), ANIMATION_TIME );
    m_down = new Animation( e, m_factory.getGuardDown(), ANIMATION_TIME );
  }

  private boolean between( double val, double min, double max )
  {
    return min <= val && val <= max;
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    double        angle = Vector.normalizeAngle( m_entity.getOrientation() );
    final double  PI_4  = Math.PI / 4;
    BufferedImage img   = null;

    if( between( angle, -PI_4, PI_4 ) )
    {
      img = m_right.getImage();
      m_right.update();
    }
    else if( between( angle, PI_4, 3 * PI_4 ) )
    {
      img = m_down.getImage();
      m_down.update();
    }
    else if( between( angle, -3 * PI_4, -PI_4 ) )
    {
      img = m_up.getImage();
      m_up.update();
    }
    else
    {
      img = m_left.getImage();
      m_left.update();
    }

    g.drawImage( img, x, y, width, height, null );

  }

}
