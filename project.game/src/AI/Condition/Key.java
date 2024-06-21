package src.AI.Condition;

import java.awt.event.KeyEvent;

import src.Controller;
import src.AI.KEY;
import src.Model.Entity;

public class Key implements ConditionFsm
{
  private static final KEY[] KEYS      = { KEY.A, KEY.Z, KEY.E, KEY.R, KEY.T, KEY.Y, KEY.U, KEY.I, KEY.O, KEY.P, KEY.Q,
      KEY.S, KEY.D, KEY.F, KEY.G, KEY.H, KEY.J, KEY.K, KEY.L, KEY.M, KEY.W, KEY.X, KEY.C, KEY.V, KEY.B, KEY.N,
      KEY.SPACE, KEY.ENTER, KEY.UP, KEY.DOWN, KEY.LEFT, KEY.RIGHT };

  private static final int[] KEY_CODES = { KeyEvent.VK_A, KeyEvent.VK_Z, KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_T,
      KeyEvent.VK_Y, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O, KeyEvent.VK_P, KeyEvent.VK_Q, KeyEvent.VK_S,
      KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L,
      KeyEvent.VK_M, KeyEvent.VK_W, KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_V, KeyEvent.VK_B, KeyEvent.VK_N,
      KeyEvent.VK_SPACE, KeyEvent.VK_ENTER, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };

  private KEY                m_key;

  public Key()
  {
    // TODO define default values
    m_key = KEY.UNDERSCORE;
  }

  public Key( KEY key )
  {
    m_key = key;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    if( m_key == KEY.UNDERSCORE ) return true;

    for ( int i = 0; i < KEYS.length; i++ )
    {
      if( m_key == KEYS[ i ] )
      {
        return Controller.getInstance().isKeyDown( KEY_CODES[ i ] );
      }
    }

    return false;
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Key )
    {
      Key key = (Key)action;
      if( key.m_key.equals( m_key ) ) return true;
    }
    return false;
  }
}
