package src.AI;

import src.Model.Angle;
import src.Model.Vector;

public class Direction
{
  private DIRECTION m_direction;

  public enum DIRECTION
  {
    Here, North, South, East, West, NE, SE, SW, NW, Forward, BackWard, Left, Right, Underscore;
  }

  public Direction( DIRECTION d )
  {
    m_direction = d;
  }

  public DIRECTION getDirection()
  {
    return m_direction;
  }

  public double toAngle( Angle angle )
  {
    double currentAngle = angle.getValue();
    switch ( m_direction )
    {
    case Here:
      return 0;
    case North:
      return -Math.PI * 0.5;
    case South:
      return Math.PI * 0.5;
    case East:
      return 0;
    case West:
      return Math.PI;
    case NE:
      return -Math.PI * 0.25;
    case SE:
      return Math.PI * 0.25;
    case SW:
      return Math.PI * 0.75;
    case NW:
      return -Math.PI * 0.75;
    case BackWard:
      return Vector.normalizeAngle( currentAngle + Math.PI );
    case Left:
      return Vector.normalizeAngle( currentAngle + Math.PI * 0.5 );
    case Right:
      return Vector.normalizeAngle( currentAngle - Math.PI * 0.5 );
    default:
      return currentAngle;
    }
  }

  @Override
  public boolean equals( Object o )
  {
    if( o instanceof Direction )
    {
      Direction dir = (Direction)o;
      if( dir.m_direction.equals( m_direction ) ) return true;
    }
    return false;
  }
}