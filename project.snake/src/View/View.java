package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Entity;
import src.Game;
import src.Grid;
import src.Model;

public class View
{
  private Model               m_model;
  private ArrayList< Avatar > m_avatars;
  private static final Color  BACKGROUND_COLOR  = new Color( 0x1E, 0x1E, 0x2E );
  private static final Color  CELL_BORDER_COLOR = new Color( 0x60, 0x60, 0x80 );

  public View( Model model )
  {
    m_model = model;
    m_avatars = new ArrayList<>();

    ArrayList< Entity > entities = model.GetEntityList();
    for ( Entity e : entities )
    {
      m_avatars.add( AvatarFactory.make( e ) );
    }
  }

  public void paint( Graphics g )
  {
    int cellWidth  = Game.WIDTH / Grid.COLS_NUM;
    int cellHeight = Game.HEIGHT / Grid.ROWS_NUM;

    /// < Draw the background
    g.setColor( BACKGROUND_COLOR );
    g.fillRect( 0, 0, Game.WIDTH, Game.HEIGHT );

    /// < Draw the grid
    g.setColor( CELL_BORDER_COLOR );
    for ( int l = 0; l < Grid.ROWS_NUM; l++ )
    {
      for ( int c = 0; c < Grid.COLS_NUM; c++ )
      {
        g.drawRect( cellWidth * l, cellHeight * c, cellWidth, cellHeight );
      }
    }

    /// < Draw the avatars : snake, apple, egg, etc.
    for ( Avatar avatar : m_avatars )
    {
      if( avatar != null )
      {
        avatar.paint( g );
      }
    }
  }
}
