package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Vector;

public class Animation4
{
  private Animation m_left;
  private Animation m_right;
  private Animation m_up;
  private Animation m_down;
  private Entity    m_entity;

  public Animation4( Entity e, long animationTime, BufferedImage[][] img )
  {
    m_entity = e;
    m_up = new Animation( e, img[ 0 ], animationTime );
    m_right = new Animation( e, img[ 1 ], animationTime );
    m_down = new Animation( e, img[ 2 ], animationTime );
    m_left = new Animation( e, img[ 3 ], animationTime );
  }

  private boolean between( double val, double min, double max )
  {
    return min <= val && val <= max;
  }

  public void paint( Graphics g, int x, int y, int width, int height )
  {
    double        angle = m_entity.getOrientation().normalize();
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
