package src;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Controller
{
  private static final Controller INSTANCE    = new Controller();
  private static final int        KEYS_NUM    = 0x100;
  private static final int        BUTTONS_NUM = 3;

  /// < Keyboard
  private boolean[]               m_keys;
  private int[]                   m_keysCnt;

  /// < Mouse
  private boolean[]               m_mouseBtn;
  private int[]                   m_mouseCnt;
  private Point                   m_mousePos;
  private Point                   m_mouseCurrentPos;

  public static Controller getInstance()
  {
    return INSTANCE;
  }

  private Controller()
  {
    m_keys = new boolean[ KEYS_NUM ];
    m_keysCnt = new int[ KEYS_NUM ];
    m_mouseBtn = new boolean[ BUTTONS_NUM ];
    m_mouseCnt = new int[ BUTTONS_NUM ];
    m_mousePos = new Point( 0, 0 );
    m_mouseCurrentPos = new Point( 0, 0 );
  }

  public void update()
  {
    for ( int i = 0; i < m_keys.length; i++ )
    {
      m_keysCnt[ i ] = m_keys[ i ] ? ( m_keysCnt[ i ] + 1 ) : 0;
    }

    for ( int i = 0; i < BUTTONS_NUM; i++ )
    {
      m_mouseCnt[ i ] = m_mouseBtn[ i ] ? ( m_mouseCnt[ i ] + 1 ) : 0;
    }

    m_mousePos = new Point( m_mouseCurrentPos );
  }

  public boolean isKeyDown( int keyCode )
  {
    return m_keysCnt[ keyCode ] > 0;
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

  public Point getMousePos()
  {
    return m_mousePos;
  }

  public boolean isMouseButtonDown( int btn )
  {
    return m_mouseCnt[ btn - 1 ] > 0;
  }

  public void updateMouseButtonPressed( MouseEvent e )
  {
    int btn = e.getButton() - 1;
    if( btn < 0 || btn >= m_mouseBtn.length ) return;
    m_mouseBtn[ btn ] = true;
  }

  public void updateMouseButtonReleased( MouseEvent e )
  {
    int btn = e.getButton() - 1;
    if( btn < 0 || btn >= m_mouseBtn.length ) return;
    m_mouseBtn[ btn ] = false;
    System.out.println( btn );
  }

  public void updateMousePos( MouseEvent e )
  {
    m_mouseCurrentPos = e.getPoint();
  }
}
