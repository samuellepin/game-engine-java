package src.View;

import java.awt.image.BufferedImage;

import src.Model.Entity;

public class Animation
{
  private Entity          m_entity;
  private BufferedImage[] m_img;
  private long            m_time;
  private long            m_maxTime;
  private int             m_index;
  private int             m_maxIndex;

  public Animation( Entity entity, BufferedImage[] img, long animationTime )
  {
    m_entity = entity;
    m_img = img;
    m_time = 0;
    m_maxTime = animationTime;
    m_index = 0;
    m_maxIndex = img != null ? img.length : 0;
  }

  public void update()
  {
    m_time += m_entity.getElapsedTime();
    if( m_time > m_maxTime )
    {
      m_index = ( m_index + 1 ) % m_maxIndex;
      m_time -= m_maxTime;
    }
  }
  
  public BufferedImage getImage()
  {
    return m_img[ m_index ];
  }
}
