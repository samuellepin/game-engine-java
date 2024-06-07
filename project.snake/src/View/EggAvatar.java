package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Grid.Cell;
import src.Game;
import src.Grid;
import src.Egg;

public class EggAvatar implements Avatar
{
  private Egg               m_egg;
  public static final Color COLOR = new Color( 0xFF, 0x45, 0x00 );

  public EggAvatar( Egg egg )
  {
    m_egg = egg;
  }

  @Override
  public void paint( Graphics g )
  {
    Cell cell       = m_egg.getCell();
    int  width      = Game.WIDTH / Grid.COLS_NUM;
    int  height     = Game.HEIGHT / Grid.ROWS_NUM;
    int  posX       = width * cell.getX();
    int  posY       = height * cell.getY();

    int  ovalWidth  = (int) ( width * 0.8 );
    int  ovalHeight = (int) ( height * 1.2 );
    int  offsetX    = ( width - ovalWidth ) / 2;
    int  offsetY    = ( height - ovalHeight ) / 2;

    g.setColor( COLOR );
    g.fillOval( posX + offsetX, posY + offsetY, ovalWidth, ovalHeight );
  }
}
