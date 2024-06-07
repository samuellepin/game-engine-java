package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Grid.Cell;
import src.Game;
import src.Grid;
import src.Apple;

public class AppleAvatar implements Avatar
{
  private Apple             m_apple;
  public static final Color COLOR = new Color( 0xFF, 0x50, 0x00 );

  public AppleAvatar( Apple apple )
  {
    m_apple = apple;
  }

  @Override
  public void paint( Graphics g )
  {
    Cell cell   = m_apple.getCell();
    int  width  = Game.WIDTH / Grid.COLS_NUM;
    int  height = Game.HEIGHT / Grid.ROWS_NUM;
    int  posX   = width * cell.getX();
    int  posY   = height * cell.getY();

    g.setColor( COLOR );
    g.fillOval( posX, posY, width, height );
  }
}
