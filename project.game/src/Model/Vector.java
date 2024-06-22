package src.Model;

import java.text.DecimalFormat;

public class Vector implements Cloneable
{
  public static final Vector e_x = new Vector( 1, 0 );
  public static final Vector e_y = new Vector( 0, 1 );

  private double             m_x, m_y;
  
  public Vector clone() throws CloneNotSupportedException
  {
    return (Vector)super.clone();
  }

  public Vector( double x, double y )
  {
    m_x = x;
    m_y = y;
  }

  public double getX()
  {
    return m_x;
  }

  public double getY()
  {
    return m_y;
  }

  public void setX( double x )
  {
    m_x = x;
  }

  public void setY( double y )
  {
    m_y = y;
  }

  public void setPos( double x, double y )
  {
    m_x = x;
    m_y = y;
  }

  @Override
  public String toString()
  {
    DecimalFormat df = new DecimalFormat("#.0");
    StringBuilder strb = new StringBuilder();
    strb.append( "(" );
    strb.append( "x=" + df.format( this.getX() ) + ", " );
    strb.append( "y=" + df.format( this.getY() ) + ", " );
    strb.append( "norm=" + df.format( this.getMagnitude() ) + ", " );
    strb.append( "angle=" + df.format( this.getAngle() ) );
    strb.append( ")" );
    return strb.toString();
  }

  public double getAngle()
  {
    return Math.atan2( m_y, m_x );
  }

  public double getMagnitude()
  {
    return Math.sqrt( m_x * m_x + m_y * m_y );
  }

  public double getSquaredMagnitude()
  {
    return m_x * m_x + m_y * m_y;
  }

  public void translate( double x, double y )
  {
    m_x += x;
    m_y += y;
  }

  public void rotate( double theta )
  {
    double x   = m_x;
    double y   = m_y;
    double cos = Math.cos( theta );
    double sin = Math.sin( theta );

    m_x = cos * x - sin * y;
    m_y = sin * x + cos * y;
  }

  public static Vector add( Vector v1, Vector v2 )
  {
    assert v1 != null && v2 != null;
    return new Vector( v1.getX() + v2.getX(), v1.getY() + v2.getY() );
  }

  public static Vector sub( Vector v1, Vector v2 )
  {
    assert v1 != null && v2 != null;
    return new Vector( v1.getX() - v2.getX(), v1.getY() - v2.getY() );
  }

  public static Vector scale( Vector v1, double scalar )
  {
    assert v1 != null;
    return new Vector( scalar * v1.getX(), scalar * v1.getY() );
  }

  public static double dot( Vector v1, Vector v2 )
  {
    assert v1 != null && v2 != null;
    return v1.getX() * v2.getX() + v1.getY() * v2.getY();
  }

  public static Vector normalize( Vector v1 )
  {
    assert v1 != null;
    return Vector.scale( v1, 1.0f / v1.getMagnitude() );
  }

  public static double normalizeAngle( double angle )
  {
    angle = angle % ( 2 * Math.PI );
    if( angle >= Math.PI )
    {
      angle -= 2 * Math.PI;
    }
    else if( angle < -Math.PI )
    {
      angle += 2 * Math.PI;
    }
    return angle;
  }
  
  public static Vector min( Vector v1, Vector v2 )
  {
    double x = v1.getX() < v2.getX() ? v1.getX() : v2.getX();
    double y = v1.getY() < v2.getY() ? v1.getY() : v2.getY();
    return new Vector( x, y );
  }
  
  public static Vector max( Vector v1, Vector v2 )
  {
    double x = v1.getX() > v2.getX() ? v1.getX() : v2.getX();
    double y = v1.getY() > v2.getY() ? v1.getY() : v2.getY();
    return new Vector( x, y );
  }
}