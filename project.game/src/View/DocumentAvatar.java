package src.View;

import java.awt.Graphics;
import src.Model.Entity;

public class DocumentAvatar extends Avatar
{
  private Animation m_idle;

  public DocumentAvatar( Entity e )
  {
    super( e );
    m_idle = new Animation( e, m_factory.getDocumentSprite(), 0 );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.drawImage( m_idle.getImage(), x, y, width, height, null );
    m_idle.update();
  }

}
