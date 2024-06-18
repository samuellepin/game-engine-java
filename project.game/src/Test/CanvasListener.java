package src.Test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener
{
  CollisionTest m_game;

  CanvasListener( CollisionTest game )
  {
    m_game = game;
  }

  @Override
  public void mouseClicked( MouseEvent e )
  {
  }

  @Override
  public void mousePressed( MouseEvent e )
  {
  }

  @Override
  public void mouseReleased( MouseEvent e )
  {
  }

  @Override
  public void mouseEntered( MouseEvent e )
  {
  }

  @Override
  public void mouseExited( MouseEvent e )
  {
  }

  @Override
  public void mouseDragged( MouseEvent e )
  {
  }

  @Override
  public void mouseMoved( MouseEvent e )
  {
  }

  @Override
  public void keyTyped( KeyEvent e )
  {
  }

  @Override
  public void keyPressed( KeyEvent e )
  {
    int code = e.getKeyCode();
    switch ( code )
    {
    case KeyEvent.VK_LEFT:
      m_game.translateCircle( -2, 0 );
      break;
    case KeyEvent.VK_UP:
      m_game.translateCircle( 0, -2 );
      break;
    case KeyEvent.VK_RIGHT:
      m_game.translateCircle( 2, 0 );
      break;
    case KeyEvent.VK_DOWN:
      m_game.translateCircle( 0, 2 );
      break;

    case KeyEvent.VK_Q:
      m_game.translateAABB( -2, 0 );
      break;
    case KeyEvent.VK_Z:
      m_game.translateAABB( 0, -2 );
      break;
    case KeyEvent.VK_D:
      m_game.translateAABB( 2, 0 );
      break;
    case KeyEvent.VK_S:
      m_game.translateAABB( 0, 2 );
      break;
    }
  }

  @Override
  public void keyReleased( KeyEvent e )
  {
  }

  @Override
  public void tick( long elapsed )
  {
    m_game.tick( elapsed );
  }

  @Override
  public void paint( Graphics g )
  {
    m_game.paint( g );
  }

  @Override
  public void windowOpened()
  {
  }

  @Override
  public void exit()
  {
  }

  @Override
  public void endOfPlay( String name )
  {
  }

  @Override
  public void expired()
  {
  }

}
