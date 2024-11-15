package src.Test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;
import src.Controller;

public class CanvasListener implements GameCanvasListener
{
  private CollisionTest m_game;
  private Controller m_controller;

  CanvasListener( CollisionTest game )
  {
    m_game = game;
    m_controller = Controller.getInstance();
  }


  @Override
  public void mouseClicked( MouseEvent e )
  {
  }

  @Override
  public void mousePressed( MouseEvent e )
  {
    m_controller.updateMouseButtonPressed( e );
  }

  @Override
  public void mouseReleased( MouseEvent e )
  {
    m_controller.updateMouseButtonReleased( e );
  }

  @Override
  public void mouseEntered( MouseEvent e )
  {
    m_controller.updateMousePos( e );
  }

  @Override
  public void mouseExited( MouseEvent e )
  {
    m_controller.updateMousePos( e );
  }

  @Override
  public void mouseDragged( MouseEvent e )
  {
    m_controller.updateMousePos( e );
  }

  @Override
  public void mouseMoved( MouseEvent e )
  {
    m_controller.updateMousePos( e );
  }

  @Override
  public void keyTyped( KeyEvent e )
  {
  }

  @Override
  public void keyPressed( KeyEvent e )
  {
    m_controller.updateKeyPressed( e );
  }

  @Override
  public void keyReleased( KeyEvent e )
  {
    m_controller.updateKeyReleased( e );
  }

  @Override
  public void tick( long elapsed )
  {
    m_controller.update();
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
