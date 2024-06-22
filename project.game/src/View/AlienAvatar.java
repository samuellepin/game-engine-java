package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Vector;

public class AlienAvatar extends Avatar
{
  private int m_movingImgIdx;
  private int m_movingAnimTime;

  public AlienAvatar( Entity e )
  {
    super( e );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    BufferedImage[] moving = m_factory.getMovingAlienSprite();

    double          angle  = Vector.normalizeAngle( m_entity.getOrientation() );
    if( angle > Math.PI / 2 || angle < -Math.PI / 2 )
    {
      width *= -1;
      x -= width;
    }

    g.drawImage( moving[ m_movingImgIdx ], x, y, width, height, null );

    m_movingAnimTime += m_entity.getElapsedTime();
    if( m_movingAnimTime > ANIMATION_TIME/2 )
    {
      m_movingImgIdx = ( m_movingImgIdx + 1 ) % moving.length;
      m_movingAnimTime -= ANIMATION_TIME/2;
    }
  }

}
