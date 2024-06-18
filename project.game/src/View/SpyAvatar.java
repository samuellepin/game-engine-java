package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Vector;

public class SpyAvatar extends Avatar
{
  private int    m_movingImgIdx;
  private int    m_movingAnimTime;
  protected int  m_idleImgIdx;
  protected long m_idleAnimTime;

  public SpyAvatar( Entity e )
  {
    super( e );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    super.paintVisionField( g, x, y );
    
    // Correction pour le sprint - évite que l'image ne soit écrasée
    if( m_entity.isMoving() )
    {
      double w = (double)width;
      w *= (double) (double)AvatarFactory.m_runningSpyImg[ 0 ].getWidth() / AvatarFactory.m_idleSpyImg[ 0 ].getWidth();
      width = (int)w;
//      double h =(double)height;
//      h *= (double)AvatarFactory.m_idleSpyImg[ 0 ].getHeight() / (double)AvatarFactory.m_runningSpyImg[ 0 ].getHeight();
//      height = (int)h;
    }

    // renverse le sprite pour tourner le personnage vers la gauche
    double angle = Vector.normalizeAngle( m_entity.getOrientation() );
    if( angle > Math.PI / 2 || angle < -Math.PI / 2 )
    {
      width *= -1;
      x -= width;
    }

    BufferedImage img;
    if( m_entity.isMoving() )
    {
      img = AvatarFactory.m_runningSpyImg[ m_movingImgIdx ];
    }
    else
    {
      img = AvatarFactory.m_idleSpyImg[ m_idleImgIdx ];
    }

    g.drawImage( img, x, y, width, height, null );

    super.paintHitbox( g, x, y, width, height );

    if( m_entity.isMoving() )
    {
      m_movingAnimTime += m_entity.getElapsedTime();
      if( m_movingAnimTime > ANIMATION_TIME )
      {
        m_movingImgIdx = ( m_movingImgIdx + 1 ) % AvatarFactory.m_idleSpyImg.length;
        m_movingAnimTime -= ANIMATION_TIME;
      }
      return;
    }

    m_idleAnimTime += m_entity.getElapsedTime();
    if( m_idleAnimTime > ANIMATION_TIME )
    {
      m_idleImgIdx = ( m_idleImgIdx + 1 ) % AvatarFactory.m_idleSpyImg.length;
      m_idleAnimTime -= ANIMATION_TIME;
    }

  }

}
