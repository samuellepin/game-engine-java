package src.Model.Collision;

import src.Model.Vector;

public class Collision
{
//  public static boolean detect( Hitbox h1, Hitbox h2 )
//  {
//    if( h1 instanceof AABB && h2 instanceof AABB )
//    {
//      return detect( (AABB)h1, (AABB)h2 );
//    }
//    if( h1 instanceof Circle && h2 instanceof Circle )
//    {
//      return detect( (Circle)h1, (Circle)h2 );
//    }
//    if( h1 instanceof Circle && h2 instanceof AABB )
//    {
//      return detect( (AABB)h2, (Circle)h1 );
//    }
//    if( h1 instanceof AABB && h2 instanceof Circle )
//    {
//      return detect( (AABB)h1, (Circle)h2 );
//    }
//    return false;
//  }

  public static boolean detect( AABB a, AABB b )
  {
    if( a == null || b == null ) return false;
    return a.getMax().getX() >= b.getMin().getX() && a.getMin().getX() <= b.getMax().getX()
        && a.getMax().getY() >= b.getMin().getY() && a.getMin().getY() <= b.getMax().getY();
  }

  public static boolean detect( Circle c1, Circle c2 )
  {
    Vector OA = c1.getCenter();
    Vector OB = c2.getCenter();
    Vector AB = Vector.sub( OB, OA );
    double R  = c1.getRadius() + c2.getRadius();

    return AB.getSquaredMagnitude() <= R * R; /// < On évite la racine carré de Vector.norm( AB ) <= R
  }

  public static Vector getBarycenter( Vector[] points )
  {
    Vector sum = new Vector( 0, 0 );
    for ( Vector point : points )
    {
      sum.translate( point.getX(), point.getY() );
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

  public static Circle getCircle( Vector[] points )
  {
    Vector center = Collision.getBarycenter( points );
    double radius = 0;

    for ( Vector point : points )
    {
      double distance = Vector.sub( point, center ).getSquaredMagnitude();
      if( distance > radius * radius )
      {
        radius = Math.sqrt( distance );
      }
    }

    return new Circle( center, radius );
  }

  private static double square( double x )
  {
    return x * x;
  }

  public static double squaredDistanceBetweenAABBandPoint( AABB hitbox, Vector point )
  {
    double distance = 0;
    double delta    = 0;

    delta = point.getX() - hitbox.getMin().getX();
    if( delta < 0 ) distance += square( delta );

    delta = point.getX() - hitbox.getMax().getX();
    if( delta > 0 ) distance += square( delta );

    delta = point.getY() - hitbox.getMin().getY();
    if( delta < 0 ) distance += square( delta );

    delta = point.getY() - hitbox.getMax().getY();
    if( delta > 0 ) distance += square( delta );

    return distance;
  }

  public static boolean detect( AABB h, Circle c )
  {
    double r = c.getRadius();
    return squaredDistanceBetweenAABBandPoint( h, c.getCenter() ) <= r * r;
  }

  public static Vector closestPointToAABB( AABB aabb, Vector point )
  {
    double x = Math.max( aabb.getMin().getX(), Math.min( aabb.getMax().getX(), point.getX() ) );
    double y = Math.max( aabb.getMin().getY(), Math.min( aabb.getMax().getY(), point.getY() ) );
    return new Vector( x, y );
  }

  public static boolean detect( AABB aabb, Arc arc )
  {
    arc.normalizeAngles();
    Vector C  = arc.getCenter();
    Vector P  = closestPointToAABB( aabb, C );
    Vector CP = Vector.sub( P, C );

    double r  = arc.getRadius();
    if( CP.getSquaredMagnitude() > r * r )
    {
      return false;
    }

    Vector pmin     = aabb.getMin();
    Vector pmax     = aabb.getMax();
    Vector points[] = { new Vector( pmin.getX(), pmin.getY() ), new Vector( pmax.getX(), pmin.getY() ),
        new Vector( pmax.getX(), pmax.getY() ), new Vector( pmin.getX(), pmax.getY() ) };

    double minAngle = 0;
    double maxAngle = 0;
    for ( int i = 0; i < 4; i++ )
    {
      Vector Q     = points[ i ];
      Vector CQ    = Vector.sub( Q, C );
      double angle = CQ.getAngle();

      if( i == 0 )
      {
        minAngle = angle;
        maxAngle = angle;
        continue;
      }
      if( angle < minAngle )
      {
        minAngle = angle;
      }
      if( angle > maxAngle )
      {
        maxAngle = angle;
      }
    }

    double startAngle = Vector.normalizeAngle( arc.getStartAngle() );
    double endAngle   = Vector.normalizeAngle( arc.getEndAngle() );

    if( startAngle <= minAngle && minAngle <= endAngle  ) 
    {
      return true;
    }

    return false;
  }

}
