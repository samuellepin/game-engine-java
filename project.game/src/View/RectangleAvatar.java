package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Model;
import src.Model.Rectangle;
import src.Model.Vector;

public class RectangleAvatar extends Avatar
{
  private Rectangle m_rect;
  private Color m_color;
  
  public RectangleAvatar( Rectangle rect )
  {
    m_rect = rect;
    m_entity = rect;
    m_color = new Color(0.1f, 0.5f, 0.1f, 1.0f);
  }

  @Override
  public void paint( Graphics g )
  {
    g.setColor( m_color );
    g.fillPolygon( m_rect.getRX(), m_rect.getRY(), m_rect.getNum() );
  }
  
}
