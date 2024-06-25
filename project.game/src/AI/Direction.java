package src.AI;

import src.Model.Angle;
import src.Model.Vector;

public class Direction
{
  public static final double DIRECTION_ANGLE = Math.PI * 0.25;

  private DIRECTION          m_direction;

  public enum DIRECTION
  {
    Here, North, South, East, West, NE, SE, SW, NW, Forward, Backward, Left, Right, Underscore;
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
    case Backward:
      return Vector.normalizeAngle( currentAngle + Math.PI );
    case Left:
      return Vector.normalizeAngle( currentAngle + Math.PI * 0.5 );
    case Right:
      return Vector.normalizeAngle( currentAngle - Math.PI * 0.5 );
    default:
      return currentAngle;
    }
  }

  /**
   * @param angle
   * @return the absolute direction quarter in which the angle is approximatively.
   */
  public static DIRECTION toDirection( double angle )
  {
    double angleMod = angle % Math.PI; // angleMod is negative if angle too
    if( angleMod >= -Math.PI * 0.125 && angleMod < 0 || angleMod >= 0 && angleMod < Math.PI * 0.125 )
      return DIRECTION.East;
    if( angleMod >= Math.PI * 0.125 && angleMod < Math.PI * 0.375 ) return DIRECTION.NE;
    if( angleMod >= Math.PI * 0.375 && angleMod < Math.PI * 0.625 ) return DIRECTION.North;
    if( angleMod >= Math.PI * 0.625 && angleMod < Math.PI * 0.875 ) return DIRECTION.NW;
    if( angleMod >= Math.PI * 0.875 && angleMod < Math.PI || angleMod < -Math.PI * 0.875 && angleMod >= -Math.PI )
      return DIRECTION.West;
    if( angleMod < -Math.PI * 0.625 && angleMod >= -Math.PI * 0.875 ) return DIRECTION.SW;
    if( angleMod < -Math.PI * 0.375 && angleMod >= -Math.PI * 0.625 ) return DIRECTION.South;
    return DIRECTION.SE;
  }
  
  public void changeDirection()
  {
    switch( m_direction )
    {
    case North:
      m_direction = DIRECTION.NE;
    case NE:
      m_direction = DIRECTION.East;
    case East:
      m_direction = DIRECTION.SE;
    case SE:
      m_direction = DIRECTION.South;
    case South:
      m_direction = DIRECTION.SW;
    case SW:
      m_direction = DIRECTION.West;
    case West:
      m_direction = DIRECTION.NW;
    case NW:
      m_direction = DIRECTION.North;
    case Forward:
      m_direction = DIRECTION.Right;
    case Right:
      m_direction = DIRECTION.Backward;
    case Backward:
      m_direction = DIRECTION.Left;
    default:
      m_direction = DIRECTION.Forward;
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