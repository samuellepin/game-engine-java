package src.Model;

public class Vector
{
  private double m_x, m_y;

  public static final Vector e_x = new Vector( 1, 0 );
  public static final Vector e_y = new Vector( 0, 1 );
  
  /* Triède direct utilisé pour le jeu
   * O------------> e_x
   * |
   * |
   * |
   * |
   * |
   * v
   * e_y
   */

  public Vector( double x, double y )
  {
    m_x = x;
    m_y = y;
  }
  
  public void setX(double x)
  {
    m_x = x;
  }
  
  public void setY(double y)
  {
    m_y = y;
  }

  public double getX() ///< Position dans le model 
  {
    return m_x;
  }
  
  public double getVX() ///< Position dans la vue 
  {
    return m_x + Model.getInstance().getViewPos().getX();
  }

  public double getY()
  {
    return m_y;
  }

  public double getVY()
  {
    return m_y + Model.getInstance().getViewPos().getY();
  }

  public void setPos( double x, double y )
  {
    m_x = x;
    m_y = y;
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

  public static double norm( Vector v1 )
  {
    double x = v1.getX();
    double y = v1.getY();
    return Math.sqrt( x * x + y * y );
  }

  public static Vector normalize( Vector v1 )
  {
    assert v1 != null;
    return Vector.scale( v1, 1.0f / Vector.norm( v1 ) );
  }
  
  public double norm()
  {
    return Math.sqrt( m_x * m_x + m_y * m_y );
  }
  
  public Vector add( Vector v )
  {
    return new Vector( this.getX() + v.getX(), this.getY() + v.getY() );
  }
  
  public Vector add( double x, double y )
  {
    return new Vector( this.getX() + x, this.getY() + y );
  }
  
  public Vector sub( double x, double y )
  {
    return new Vector( this.getX() - x, this.getY() - y );
  }
  
  @Override
  public String toString()
  {
    return "(mx=" + this.getX() 
         + ", my=" + this.getY() 
         + ", vx=" + this.getVX()
         + ", vy=" + this.getVY()
         + ")";
  }

  /// < Pas de cross product, on est en 2D

}
