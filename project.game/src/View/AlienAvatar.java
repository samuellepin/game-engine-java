package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.Vector;

public class AlienAvatar extends Avatar
{
  private Animation m_movingAnim;

  public AlienAvatar( Entity e )
  {
    super( e );
    m_movingAnim = new Animation( e, m_factory.getMovingAlienSprite(), ANIMATION_TIME / 2 );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    double angle = Vector.normalizeAngle( m_entity.getOrientation() );
    if( angle > Math.PI / 2 || angle < -Math.PI / 2 )
    {
      width *= -1;
      x -= width;
    }
    g.drawImage( m_movingAnim.getImage(), x, y, width, height, null );
    m_movingAnim.update();
  }

}
