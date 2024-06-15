package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Vector;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Hitbox;

public class CircularHitboxAvatar
{

  public static void paint( Graphics g, Circle hitbox )
  {
    if( hitbox == null ) return;
 
    Vector c = hitbox.getCenter();
    double r = hitbox.getRadius();
    int dim = (int)(2 * r);

    g.setColor( new Color( 200, 0, 255, 100 ) );
    g.fillOval( (int) ( c.getVX() - r ), (int) ( c.getVY() - r ), dim, dim );
//    System.out.println( (int)hitbox.getMin().getVX() 
//        + ", " + (int)hitbox.getMin().getVY()
//        + ", " +  (int)hitbox.getMax().getVX() 
//        + ", " +  (int)hitbox.getMax().getVY() );
  }
}
