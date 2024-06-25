package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.View.Animation;
import src.View.Avatar;
import src.View.AvatarFactory;

public class RobotAvatar extends Avatar
{
  private static BufferedImage[] m_idleImg;
  private static BufferedImage[] m_walkImg;
  private static BufferedImage[] m_deadImg;

  private Animation              m_idle;
  private Animation              m_walk;
  private Animation              m_dead;

  public RobotAvatar( Entity e )
  {
    super( e );
    if( m_idleImg == null )
    {
      try
      {
        m_idleImg = AvatarFactory.loadSprite( "resources/sprites/Robot/Robot_Idle.png", 1, 26 );
        m_walkImg = AvatarFactory.loadSprite( "resources/sprites/Robot/Robot_Walk.png", 1, 4 );
        m_deadImg = AvatarFactory.loadSprite( "resources/sprites/Robot/Robot_Dead.png", 1, 3 );
      }
      catch ( IOException e1 )
      {
        e1.printStackTrace();
      }
    }

    m_idle = new Animation( e, m_idleImg, ANIMATION_TIME/3 );
    m_walk = new Animation( e, m_walkImg, ANIMATION_TIME );
    m_dead = new Animation( e, m_deadImg, ANIMATION_TIME );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    double angle = m_entity.getOrientation().normalize();
    if( angle > Math.PI / 2 || angle < -Math.PI / 2 )
    {
      width *= -1;
      x -= width;
    }

    if( m_entity.isDead() )
    {
      g.drawImage( m_dead.getImage(), x, y, width, height, null );
      m_dead.update();
    }
    else if( m_entity.isMoving() )
    {
      g.drawImage( m_walk.getImage(), x, y, width, height, null );
      m_walk.update();
    }
    else
    {
      g.drawImage( m_idle.getImage(), x, y, width, height, null );
      m_idle.update();
    }
  }

}
