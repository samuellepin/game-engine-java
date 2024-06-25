package src.Model.Collision;

import src.Model.Vector;

public class AABB implements Hitbox, Cloneable
{
  private Vector m_min;
  private Vector m_max;
  private Vector m_barycenter;

  public AABB clone() throws CloneNotSupportedException
  {
    AABB cloned = (AABB)super.clone();
    cloned.m_min = cloned.m_min.clone();
    cloned.m_max = cloned.m_max.clone();
    cloned.m_barycenter = cloned.m_barycenter.clone();
    return cloned;
  }

  public AABB( Vector pmin, Vector pmax )
  {
    m_min = pmin;
    m_max = pmax;
    m_barycenter = new Vector( 0, 0 );
    this.updateBarycenter();
  }

  public AABB( double minX, double minY, double maxX, double maxY )
  {
    m_min = new Vector( minX, minY );
    m_max = new Vector( maxX, maxY );
    m_barycenter = new Vector( 0, 0 );
    this.updateBarycenter();
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

  public double getWidth()
  {
    return m_max.getX() - m_min.getX();
  }

  public double getHeight()
  {
    return m_max.getY() - m_min.getY();
  }

  public void setDim( double width, double height )
  {
    m_max.setPos( m_min.getX() + width, m_min.getY() + height );
    this.updateBarycenter();
  }

  public Vector getPos()
  {
    return m_min;
  }

  public double getX()
  {
    return m_min.getX();
  }

  public double getY()
  {
    return m_min.getY();
  }

  public void setPos( Vector pos )
  {
    double width  = this.getWidth();
    double height = this.getHeight();
    m_min.setPos( pos.getX(), pos.getY() );
    m_max.setPos( pos.getX() + width, pos.getY() + height );
    this.updateBarycenter();
  }

  public void translate( double x, double y )
  {
    m_min.translate( x, y );
    m_max.translate( x, y );
    this.updateBarycenter();
  }

  public void setPos( double x, double y )
  {
    double width  = this.getWidth();
    double height = this.getHeight();
    m_min.setPos( x, y );
    m_max.setPos( x + width, y + height );
  }

  public void updateBarycenter()
  {
    m_barycenter.setPos( ( m_min.getX() + m_max.getX() ) / 2, ( m_min.getY() + m_max.getY() ) / 2 );
  }

  public Vector getBarycenter()
  {
    return m_barycenter;
  }

  private static double min( double a, double b )
  {
    return a < b ? a : b;
  }

  private static double max( double a, double b )
  {
    return a > b ? a : b;
  }

  public static AABB merge( AABB hitbox1, AABB hitbox2 )
  {
    double minX = AABB.min( hitbox1.getMin().getX(), hitbox2.getMin().getX() );
    double minY = AABB.min( hitbox1.getMin().getY(), hitbox2.getMin().getY() );
    double maxX = AABB.max( hitbox1.getMax().getX(), hitbox2.getMax().getX() );
    double maxY = AABB.max( hitbox1.getMax().getY(), hitbox2.getMax().getY() );

    return new AABB( minX, minY, maxX, maxY );
  }

  @Override
  public String toString()
  {
    return "(min=" + this.getMin().toString() + ", " + "max=" + this.getMax().toString() + ")";
  }
}
