package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Collision.AABB;
import src.Model.Collision.Hitbox;

public class AABBAvatar extends Avatar
{
  private static final Color COLOR = new Color( 255, 255, 0, 180 );
  
  @Override
  public void paint( Graphics g )
  {
    g.setColor( COLOR );
    g.drawRect( 0, 0, this.getWidth( g )-1, this.getHeight( g )-1 );
  }
}
