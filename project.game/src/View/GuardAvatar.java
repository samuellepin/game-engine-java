package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;

public class GuardAvatar extends Avatar
{
  private static BufferedImage[][] s_img;
  private Animation4               m_anim4;

  public GuardAvatar( Entity e )
  {
    super( e );
    s_img = super.loadAnimation4( s_img, "resources/sprites/Guard/Guard_", 4 );
    BufferedImage[][] img = s_img;
    m_anim4 = new Animation4( e, ANIMATION_TIME, s_img );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    m_anim4.paint( g, x, y, width, height );
  }

}
