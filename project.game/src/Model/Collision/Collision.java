package src.Model.Collision;

import src.Model.Vector;

public class Collision
{
  public static boolean detect( Hitbox h1, Hitbox h2 )
  {
    if( h1 instanceof AABB && h2 instanceof AABB )
    {
      return detect( (AABB)h1, (AABB)h2 );
    }
    if( h1 instanceof Circle && h2 instanceof Circle )
    {
      return detect( (Circle)h1, (Circle)h2 );
    }
    return false;
  }

  private static boolean detect( AABB a, AABB b )
  {
    return a.getMax().getX() >= b.getMin().getX() && a.getMin().getX() <= b.getMax().getX()
        && a.getMax().getY() >= b.getMin().getY() && a.getMin().getY() <= b.getMax().getY();
  }

  private static boolean detect( Circle c1, Circle c2 )
  {
    Vector OA = c1.getCenter();
    Vector OB = c2.getCenter();
    Vector AB = Vector.sub( OB, OA );

    return Vector.norm( AB ) <= c1.getRadius() + c2.getRadius();
  }

  public static Vector getBarycenter( Vector[] points )
  {
    Vector sum = new Vector( 0, 0 );
    for ( Vector point : points )
    {
      sum = Vector.add( sum, point );
    }
    return Vector.scale( sum, 1.0 / (double)points.length );
  }

  public static AABB getAABB( Vector[] points )
  {
    assert points != null && points.length >= 3;

    double minX = points[ 0 ].getX();
    double minY = points[ 0 ].getY();
    double maxX = minX;
    double maxY = minY;

    for ( Vector point : points )
    {
      if( point.getX() <= minX )
      {
        minX = point.getX();
      }
      if( point.getX() >= maxX )
      {
        maxX = point.getX();
      }
      if( point.getY() <= minY )
      {
        minY = point.getY();
      }
      if( point.getY() >= maxY )
      {
        maxY = point.getY();
      }
    }

    Vector pmin = new Vector( minX, minY );
    Vector pmax = new Vector( maxX, maxY );

    return new AABB( pmin, pmax );
  }

  public Circle getCircle( Vector[] points )
  {
    Vector center = Collision.getBarycenter( points );
    double radius = 0;
    
    for( Vector point : points )
    {
      double distance = Vector.norm( Vector.sub( point, center ) );
      if( distance > radius )
      {
        distance = radius;
      }
    }
    
    return new Circle( center, radius );
  }
}
