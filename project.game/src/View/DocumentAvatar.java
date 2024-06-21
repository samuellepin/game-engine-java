package src.View;

import java.awt.Graphics;
import src.Model.Entity;

public class DocumentAvatar extends Avatar
{
  public DocumentAvatar( Entity e )
  {
    super( e );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( AvatarFactory.m_docImg, x, y, width, height, null );
//    this.paintHitbox( g, x, y, width, height );
  }

}
