package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class SpyAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private static BufferedImage[] m_runningImg;
  private Animation              m_idle;
  private Animation              m_running;

  public SpyAvatar( Entity e )
  {
    super( e );
    try
    {
      if( m_idleImg == null )
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_Gun_1_strip4.png", 1, 4 );
        m_runningImg = AvatarFactory.loadSprite( "resources/sprites/Spy/SMS_Adv_Idle_strip4.png", 1, 4 );
      }
    }
    catch ( IOException e1 )
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    m_idle = new Animation( e, m_idleImg, ANIMATION_TIME );
    m_running = new Animation( e, m_runningImg, ANIMATION_TIME );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    BufferedImage running = m_running.getImage();
    BufferedImage idle    = m_idle.getImage();

    // Correction pour le sprint - évite que l'image ne soit écrasée
    if( m_entity.isMoving() )
    {
      double w = (double)width;
      w *= (double)(double)running.getWidth() / (double)idle.getWidth();
      width = (int)w;
      double h = (double)height;
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
