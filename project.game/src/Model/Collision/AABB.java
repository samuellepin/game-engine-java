package src.Model.Collision;

import src.Model.Vector;

public class AABB implements Hitbox
{
  private Vector m_min;
  private Vector m_max;

  public AABB( Vector pmin, Vector pmax )
  {
    m_min = pmin;
    m_max = pmax;
  }

  public Vector getMin()
  {
    return m_min;
  }

  public Vector getMax()
  {
    return m_max;
  }

  public void resize( Vector pmin, Vector pmax )
  {
    m_min = pmin;
    m_max = pmax;
  }
}
