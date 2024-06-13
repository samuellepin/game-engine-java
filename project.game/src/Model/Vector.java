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

  public double getX() ///< Position dans le model 
  {
    return m_x;
  }
  
  public double getVX() ///< Position dans la vue 
  {
    return m_x + Model.getViewPos().getX();
  }

  public double getY()
  {
    return m_y;
  }

  public double getVY()
  {
    return m_y + Model.getViewPos().getY();
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

  /// < Pas de cross product, on est en 2D

}
