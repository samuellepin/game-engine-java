package src.View;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.graphics.GameCanvas;
import src.Model.Model;

public class View
{
  private static final View INSTANCE = new View();

  private Model             m_model;
  private Viewport[]        m_viewports;
  private int               m_width;
  private int               m_height;
  private GameCanvas        m_canvas;

  private View()
  {
    m_model = Model.getInstance();
    m_viewports = new Viewport[ 1 ];
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.black );
    g.fillRect( 0, 0, m_width, m_height );
//    g.fillRect( 0, 0, m_canvas.getWidth(), m_canvas.getHeight() );
    m_viewports[ 0 ].paint( g );

  }

  public void setCanvas( GameCanvas canvas )
  {
    this.m_canvas = canvas;
    m_width = m_canvas.getWidth();
    m_height = m_canvas.getHeight();
    m_viewports[ 0 ] = new Viewport( m_model.getPlayer(), 0, 0, m_width, m_height );
  }

  public static View getInstance()
  {
    return INSTANCE;
  }
}
