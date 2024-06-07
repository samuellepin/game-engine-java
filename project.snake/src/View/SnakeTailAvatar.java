package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Grid.Cell;
import src.Game;
import src.Grid;
import src.SnakeTail;

public class SnakeTailAvatar implements Avatar
{
  private SnakeTail          m_snakeTail;
  private static final Color COLOR = new Color( 0x7F, 0xFF, 0x00 );

  public SnakeTailAvatar( SnakeTail snakeTail )
  {
    m_snakeTail = snakeTail;
  }

  @Override
  public void paint( Graphics g )
  {
    Cell cell   = m_snakeTail.getCell();
    int  width  = Game.WIDTH / Grid.COLS_NUM;
    int  height = Game.HEIGHT / Grid.ROWS_NUM;
    int  posX   = width * cell.getX();
    int  posY   = height * cell.getY();

    g.setColor( COLOR );
    g.fillRect( posX, posY, width, height );
  }
}
