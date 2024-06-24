package src.Model;

import src.Model.Collision.Collision;

public class Shot
{
  private static final double LIMIT_DISTANCE = 500;

  private Vector              m_pos;
  private Angle               m_dir;
  private double              m_velocity;
  private boolean             m_hasTouched;
  private double              m_distance;
  private Entity              m_entityTouched;
  private int                 m_damage;
  
  public Vector getPos()
  {
    return m_pos;
  }

  public Shot( Vector pos, Angle dir )
  {
    try
    {
      m_pos = pos.clone();
      m_dir = dir.clone();
    }
    catch ( CloneNotSupportedException e )
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    m_velocity = 0.3f;
    m_hasTouched = false;
    m_distance = 0;
    m_entityTouched = null;
    m_damage = 1;
  }

  public void update( long dt )
  {
    if( this.hasTouched() ) return;
    move( dt );
    if( m_distance >= LIMIT_DISTANCE )
    {
      m_hasTouched = true;
      return;
    }
    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( Collision.detect( e.getHitbox(), m_pos ) )
      {
        m_hasTouched = true;
        e.getHit(m_damage);
        return;
      }
    }
  }
  
  public void move( long dt )
  {
    double d   = m_velocity * (double)dt;
    double dir = m_dir.getValue();
    m_pos.translate( d * Math.cos( dir ), d * Math.sin( dir ) );
    m_distance += d;
  }

  public boolean hasTouched()
  {
    return m_hasTouched;
  }

  public Entity getEntityTouched()
  {
    return m_entityTouched;
  }

  public double getX()
  {
    return m_pos.getX();
  }

  public double getY()
  {
    return m_pos.getY();
  }
}
