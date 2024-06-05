package src;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class Model
{

  private class Ticker implements GameCanvasListener {

    Model m_model;
    
    Ticker(Model mod) {
      m_model = mod;
    }
    
    @Override
    public void mouseClicked( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mousePressed( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mouseReleased( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mouseEntered( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mouseExited( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mouseDragged( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void mouseMoved( MouseEvent e )
    {
      // Nothing to do
    }

    @Override
    public void keyTyped( KeyEvent e )
    {
      // Nothing to do
    }

    @Override
    public void keyPressed( KeyEvent e )
    {
      // Nothing to do
    }

    @Override
    public void keyReleased( KeyEvent e )
    {
      // Nothing to do
    }

    @Override
    public void endOfPlay( String arg0 )
    {
      // Nothing to do
    }

    @Override
    public void exit()
    {
      // Nothing to do
    }

    @Override
    public void expired()
    {
      // Nothing to do
    }

    @Override
    public void paint( Graphics arg0 )
    {
      // Nothing to do
    }

    @Override
    public void tick( long arg0 )
    {
      m_model.tick((int) arg0);
    }

    @Override
    public void windowOpened()
    {
      // Nothing to do
    }
  }
  
  private java.util.List< Entity > m_entities;
  
  void tick( int elapsed )
  {
    java.util.ListIterator< Entity > iter = m_entities.listIterator();
    
    
    while (iter.hasNext())
      iter.next().tick( elapsed );
  }
}
