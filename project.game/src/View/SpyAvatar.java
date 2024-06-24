package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Vector;

public class SpyAvatar extends Avatar
{
  private Animation m_idle;
  private Animation m_running;

  public SpyAvatar( Entity e )
  {
    super( e );
    m_idle = new Animation( e, m_factory.getIdleSpySprite(), ANIMATION_TIME );
    m_running = new Animation( e, m_factory.getRunningSpySprite(), ANIMATION_TIME );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    BufferedImage running = m_running.getImage();
    BufferedImage idle = m_idle.getImage();
    
    // Correction pour le sprint - évite que l'image ne soit écrasée
    if( m_entity.isMoving() )
    {
      double w = (double)width;
      w *= (double) (double)running.getWidth() / (double)idle.getWidth();
      width = (int)w;
      double h =(double)height;
      h *= (double)running.getHeight() / (double)idle.getHeight();
      height = (int)h;
    }

    // renverse le sprite pour tourner le personnage vers la gauche
    double angle = m_entity.getOrientation().normalize();
    if( angle > Math.PI / 2 || angle < -Math.PI / 2 )
    {
      width *= -1;
      x -= width;
    }

    BufferedImage img = m_entity.isMoving() ? running : idle;
    g.drawImage( img, x, y, width, height, null );

    if( m_entity.isMoving() )
    {
      m_running.update();
    }
    else
    {
      m_idle.update();
    }

  }

}
