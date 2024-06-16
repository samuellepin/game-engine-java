package src.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Entity;
import src.Model.Spy;

public class SpyAvatar extends Avatar
{
  public SpyAvatar( Entity e )
  {
    super( e );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( AvatarFactory.m_idleSpyImg[ 0 ], x, y, width, height, null );
    super.paintHitbox( g, x, y, width, height );
  }

}
