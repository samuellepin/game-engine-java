package src.Model.Collision;

import src.Model.Angle;
import src.Model.Vector;

public class Arc implements Cloneable
{
  private Vector m_center;
  private double m_radius;
  private Angle  m_azimuth;
  private Angle  m_apertureAngle;

  public Arc clone() throws CloneNotSupportedException
  {
    Arc cloned = (Arc)super.clone();
    cloned.m_center = m_center.clone();
    cloned.m_azimuth = m_azimuth.clone();
    cloned.m_apertureAngle = m_apertureAngle.clone();
    return cloned;
  }

  public void setAzimuth( Angle azimuth )
  {
    m_azimuth = azimuth;
  }

  public Arc( Vector pos, double radius, Angle azimuth, Angle apertureAngle )
  {
    m_center = pos;
    m_radius = radius;
    m_azimuth = azimuth;
    m_apertureAngle = apertureAngle;
  }

  public Arc( double x, double y, double radius, Angle azimuth, Angle apertureAngle )
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

  public Angle getAzimuth()
  {
    return m_azimuth;
  }

  public Angle getApertureAngle()
  {
    return m_apertureAngle;
  }

  public void translate( double dx, double dy )
  {
    m_center.translate( dx, dy );
  }

  public void rotate( double theta )
  {
    m_azimuth.add( theta );
  }

  public void open( double phi )
  {
    m_apertureAngle.add( phi );
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
    return m_azimuth.getValue() - m_apertureAngle.getValue();
  }

  public double getEndAngle()
  {
    return m_azimuth.getValue() + m_apertureAngle.getValue();
  }

  public double getArcAngle()
  {
    return 2 * m_apertureAngle.getValue();
  }

  public void setCenter( Vector center )
  {
    m_center = center;
  }
}
