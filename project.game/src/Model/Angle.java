package src.Model;

public class Angle implements Cloneable
{
  public static final Angle ANGLE_ZERO = new Angle( 0 );
  
  private double m_value; // in radian

  public Angle clone() throws CloneNotSupportedException
  {
    return (Angle)super.clone();
  }

  public Angle( double value, boolean isDegree )
  {
    if( isDegree )
    {
      m_value = value * Math.PI / 180.0;
    }
    else
    {
      m_value = value;
    }
  }

  public Angle( double value )
  {
    m_value = value;
  }

  public double getValue()
  {
    return m_value;
  }

  public double toDegree()
  {
    return 180.0 * m_value / Math.PI;
  }

  public double normalize() // set value between -pi and pi
  {
    m_value = m_value % ( 2 * Math.PI );
    if( m_value >= Math.PI )
    {
      m_value -= 2 * Math.PI;
    }
    else if( m_value < -Math.PI )
    {
      m_value += 2 * Math.PI;
    }
    return m_value;
  }

  public void add( double value )
  {
    m_value += value;
  }

  public void sub( double value )
  {
    m_value -= value;
  }

  public void setValue( double value )
  {
    m_value = value;
  }

  @Override
  public boolean equals( Object o )
  {
    if( o instanceof Angle )
    {
      Angle a = (Angle)o;
      return a.getValue() == this.getValue();
    }
    return false;
  }
}
