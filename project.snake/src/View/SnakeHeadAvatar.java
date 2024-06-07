package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Grid.Cell;
import src.Game;
import src.Grid;
import src.SnakeHead;

public class SnakeHeadAvatar implements Avatar
{
  private SnakeHead          m_snakeHead;
  private static final Color COLOR = new Color( 0x7F, 0xFF, 0x00 );

  public SnakeHeadAvatar( SnakeHead snakeHead )
  {
    m_snakeHead = snakeHead;
  }

  @Override
  public void paint( Graphics g )
  {
    Cell  cell   = m_snakeHead.getCell();
    int   width  = Game.WIDTH / Grid.COLS_NUM;
    int   height = Game.HEIGHT / Grid.ROWS_NUM;
    int   posX   = width * cell.getX();
    int   posY   = height * cell.getY();

    int[] X      = null;
    int[] Y      = null;

    switch ( m_snakeHead.getDirection() )
    {
    case UP:
      X = new int[] { posX, posX + width / 2, posX + width };
      Y = new int[] { posY + height, posY, posY + height };
      break;
    case DOWN:
      X = new int[] { posX, posX + width / 2, posX + width };
      Y = new int[] { posY, posY + height, posY };
      break;
    case RIGHT:
      X = new int[] { posX, posX + width, posX };
      Y = new int[] { posY, posY + height / 2, posY + height };
      break;
    case LEFT:
      X = new int[] { posX + width, posX, posX + width };
      Y = new int[] { posY, posY + height / 2, posY + height };
      break;
    }

    g.setColor( COLOR );
    g.fillPolygon( X, Y, X.length );

  }
}
