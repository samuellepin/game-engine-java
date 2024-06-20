package src;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;
import src.Model.Entity;
import src.Model.Model;

public class CanvasListener implements GameCanvasListener
{
  private Game       m_game;
  private Model      m_model;
  private Controller m_controller;

  CanvasListener( Game game )
  {
    m_game = game;
    m_model = Model.getInstance();
    m_controller = Controller.getInstance();
  }

  @Override
  public void mouseClicked( MouseEvent e )
  {
//    System.out.println( "Mouse clicked: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mousePressed( MouseEvent e )
  {
//    System.out.println( "Mouse pressed: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mouseReleased( MouseEvent e )
  {
//    System.out.println( "Mouse released: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mouseEntered( MouseEvent e )
  {
//    System.out.println( "Mouse entered: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mouseExited( MouseEvent e )
  {
//    System.out.println( "Mouse exited: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mouseDragged( MouseEvent e )
  {
//    System.out.println( "Mouse dragged: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void mouseMoved( MouseEvent e )
  {
//    System.out.println( "Mouse moved: (" + e.getX() + "," + e.getY() + ")" );
//    System.out.println( "   modifiers=" + e.getModifiersEx() );
//    System.out.println( "   buttons=" + e.getButton() );
  }

  @Override
  public void keyTyped( KeyEvent e )
  {
//    System.out.println( "Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode() );
  }

  @Override
  public void keyPressed( KeyEvent e )
  {
    System.out.println( "Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode() );
    
    m_controller.updateKeyPressed( e );

    double orientations[] = { -Math.PI, -Math.PI / 2, 0, Math.PI / 2 };
    int    code           = e.getKeyCode();
    Entity player1        = Model.getInstance().getPlayer1();
    switch ( code )
    {
    case 37: // LEFT
    case 38: // UP
    case 39: // RIGHT
    case 40: // DOWN
      player1.turn( orientations[ code - 37 ] );
      player1.doMove( player1.getOrientation() );
      player1.setIsMoving( true );
      break;
    }

  }

  @Override
  public void keyReleased( KeyEvent e )
  {
    m_controller.updateKeyReleased( e );

    int    code    = e.getKeyCode();
    Entity player1 = Model.getInstance().getPlayer1();
    switch ( code )
    {
    case 37: // LEFT
    case 38: // UP
    case 39: // RIGHT
    case 40: // DOWN
      player1.setIsMoving( false );
      break;
    }

    // System.out.println( "Key released: " + e.getKeyChar() + " code=" +
    // e.getKeyCode() );
  }

  @Override
  public void tick( long elapsed )
  {
    m_controller.poll();
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
