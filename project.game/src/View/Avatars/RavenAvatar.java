package src.View.Avatars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.View.Animation4;
import src.View.Avatar;

public class RavenAvatar extends Avatar
{
  private static BufferedImage[][] s_img;
  private Animation4               m_anim4;

  public RavenAvatar( Entity e )
  {
    super( e );
    s_img = super.loadAnimation4( s_img, "resources/sprites/Raven/Raven_", 4 );
    m_anim4 = new Animation4( e, ANIMATION_TIME, s_img );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    m_anim4.paint( g, x, y, width, height );
  }

}
