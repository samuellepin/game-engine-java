package src;

import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

public class CollisionTest
{
  // Ce test utilise des assertions
  // Pour qu'elles soient prises en compte, allez dans
  // Window -> Preferences -> Java -> Installed JRE -> Edit
  // Ajoutez "-ea" à Default VM arguments et sauvegardez
  public static void main( String[] args )
  {
    CollisionTest test = new CollisionTest();
    test.run();
    System.out.println( "TEST PASSED!" );
  }

  public void run()
  {
    this.testAABBAABB();
    this.testCircleCircle();
    this.testCircleAABB();
  }

  public void testAABBAABB()
  {
    AABB h1 = new AABB( 2, 0, 5, 2 );
    AABB h2 = new AABB( -1, -1, 20, 20 );
    assert Collision.detect( h1, h2 );

    AABB h3 = new AABB( -1, -1, 2, 2 );
    AABB h4 = new AABB( 3, -1, 4, 1 );
    assert !Collision.detect( h3, h4 );
  }

  public void testCircleCircle()
  {
    Circle c1 = new Circle( 0, 0, 1 );
    Circle c2 = new Circle( 0.75, 0, 1 );
    assert Collision.detect( c1, c2 );

    Circle c3 = new Circle( -1, 0, 1 );
    Circle c4 = new Circle( 3, 1, 2 );
    assert !Collision.detect( c3, c4 );
  }

  public void testCircleAABB()
  {
    Circle c1 = new Circle( -1, -1, 3 );
    AABB   h1 = new AABB( 1, 1, 2, 2 );
    assert Collision.detect( h1, c1 );

    Circle c2 = new Circle( 1, 1, 2 );
    AABB   h2 = new AABB( 4, 4, 7, 8 );
    assert !Collision.detect( h2, c2 );
  }
}
