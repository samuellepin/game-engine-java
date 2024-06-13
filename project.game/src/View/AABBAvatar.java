package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Collision.AABB;
import src.Model.Collision.Hitbox;

public class AABBAvatar 
{
  public static void paint( Graphics g, AABB hitbox )
  {
    if( hitbox == null ) return;
    g.setColor( Color.red );
    g.drawRect( 
        (int)hitbox.getMin().getVX(), 
        (int)hitbox.getMin().getVY(), 
        (int)hitbox.getWidth(),
        (int)hitbox.getHeight() );
//    System.out.println( (int)hitbox.getMin().getVX() 
//        + ", " + (int)hitbox.getMin().getVY()
//        + ", " +  (int)hitbox.getMax().getVX() 
//        + ", " +  (int)hitbox.getMax().getVY() );
  }
}
