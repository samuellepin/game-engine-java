package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.Guard;

import src.Model.Entity;
import src.Model.Vector;

public class GuardAvatar extends Avatar
{
  private int m_movingImgIdx;
  private int m_movingAnimTime;

  public GuardAvatar( Entity e )
  {
    super( e );
  }

  private boolean between( double val, double min, double max )
  {
    return min <= val && val <= max;
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    BufferedImage[] moving = null;

    double          angle  = Vector.normalizeAngle( m_entity.getOrientation() );
    double          pi4    = Math.PI / 4;

    if( between( angle, -pi4, pi4 ) )
    {
      moving = m_factory.getGuardRight();
    }
    else if( between( angle, pi4, 3 * pi4 ) )
    {
      moving = m_factory.getGuardDown();
    }
    else if( between( angle, -3 * pi4, -pi4 ) )
    {
      moving = m_factory.getGuardUp();
    }
    else
    {
      moving = m_factory.getGuardLeft();
    }

    g.drawImage( moving[ m_movingImgIdx ], x, y, width, height, null );

    m_movingAnimTime += m_entity.getElapsedTime();
    if( m_movingAnimTime > ANIMATION_TIME )
    {
      m_movingImgIdx = ( m_movingImgIdx + 1 ) % moving.length;
      m_movingAnimTime -= ANIMATION_TIME;
    }
  }

}
