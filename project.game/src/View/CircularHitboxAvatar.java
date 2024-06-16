package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Vector;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Hitbox;

public class CircularHitboxAvatar extends Avatar
{
  public static final Color COLOR = new Color( 200, 0, 255, 100 );
  
  public void paint( Graphics g )
  {
    g.setColor( COLOR );
    g.fillOval( 0, 0, this.getWidth( g ), this.getHeight( g ) );
  }
}
