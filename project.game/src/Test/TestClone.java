package src.Test;

import src.Model.Vector;
import src.Model.Collision.AABB;

public class TestClone
{
  public static void main(String[] args) throws CloneNotSupportedException
  {
    Vector v1 = new Vector(1,1);
    Vector v2 = v1;
    Vector v3 = v1.clone();
    
    AABB h1 = new AABB( 1, 2, 3, 4 );
    AABB h2 = h1;
    AABB h3 = h1.clone();
  }
}
