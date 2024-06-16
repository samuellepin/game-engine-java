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

  public AABB( double minX, double minY, double maxX, double maxY )
  {
    m_min = new Vector( minX, minY );
    m_max = new Vector( maxX, maxY );
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
  }

  public void translate( double x, double y )
  {
    m_min.translate( x, y );
    m_max.translate( x, y );
  }

  public void setPos( double x, double y )
  {
    double width  = this.getWidth();
    double height = this.getHeight();
    m_min.setPos( x, y );
    m_max.setPos( x + width, y + height );
  }

  public Vector getBarycenter()
  {
    return new Vector( ( m_min.getX() + m_max.getX() ) / 2, ( m_min.getY() + m_max.getY() ) / 2 );
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
    return "(min=" + this.getMin().toString()
        + ", " + "max=" + this.getMax().toString() + ")";
  }
}
