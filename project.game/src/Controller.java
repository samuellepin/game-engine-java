package src;

import java.awt.event.KeyEvent;

public class Controller
{
  private static final Controller INSTANCE = new Controller();
  private static final int KEYS_NUM = 0x100;
  
  private boolean[] m_keys;
  private int[] m_polled;
  
  private Controller()
  {
    m_keys = new boolean[ KEYS_NUM ];
    m_polled = new int[ KEYS_NUM ];
  }
  
  public boolean isKeyDown( int keyCode )
  {
    return m_polled[ keyCode ] > 0;
  }
  
  public boolean isKeyDownOnce( int keyCode )
  {
    return m_polled[ keyCode ] == 1;
  }
  
  public void poll()
  {
    for( int i = 0; i < m_keys.length; i++ )
    {
      m_polled[ i ] = m_keys[ i ] ? ( m_polled[ i ] + 1 ) : 0;
    }
  }
  
  public void updateKeyPressed( KeyEvent e )
  {
    int keyCode = e.getKeyCode();
    if( keyCode < 0 || keyCode >= m_keys.length ) return;
    m_keys[ keyCode ] = true;
  }
  
  public void updateKeyReleased( KeyEvent e )
  {
    int keyCode = e.getKeyCode();
    if( keyCode < 0 || keyCode >= m_keys.length ) return;
    m_keys[ keyCode ] = false;
  }

  public static Controller getInstance()
  {
    return INSTANCE;
  }
}
