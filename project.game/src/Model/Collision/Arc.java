package src.Model.Collision;

import src.Model.Vector;

public class Arc
{
  private Vector m_center;
  private double m_radius;
  private double m_azimuth;
  private double m_apertureAngle;

  public Arc( double x, double y, double radius, double azimuth, double apertureAngle )
  {
    m_center = new Vector( x, y );
    m_radius = radius;
    m_azimuth = azimuth;
    m_apertureAngle = apertureAngle;
  }
  
  public double getX()
  {
    return m_center.getX() - m_radius;
  }
  
  public double getY()
  {
    return m_center.getY() - m_radius;
  }

  public Vector getCenter()
  {
    return m_center;
  }

  public double getRadius()
  {
    return m_radius;
  }

  public double getAzimuth()
  {
    return m_azimuth;
  }

  public double getApertureAngle()
  {
    return m_apertureAngle;
  }

  public void translate( double dx, double dy )
  {
    m_center.translate( dx, dy );
  }

  public void rotate( double theta )
  {
    m_azimuth += theta;
  }

  public void open( double phi )
  {
    m_apertureAngle += phi;
  }

  public Vector getTopLeftCorner()
  {
    return new Vector( m_center.getX() - m_radius, m_center.getY() - m_radius );
  }

  public double getWidth()
  {
    return 2 * m_radius;
  }

  public double getHeight()
  {
    return 2 * m_radius;
  }

  public static double radianToDegree( double theta )
  {
    return ( 180.0 / Math.PI ) * theta;
  }

  public double getStartAngle()
  {
    return m_azimuth - m_apertureAngle;
  }
  
  public double getEndAngle()
  {
    return m_azimuth + m_apertureAngle;
  }

  public double getArcAngle()
  {
    return 2 * m_apertureAngle;
  }  
}
