package src.Model;

import src.AI.FSM;

public class Opponent extends Player
{
  public static final int WAIT_TIME_BEFORE_MOVING = 100;
  long m_wait;
  
  public Opponent( FSM automaton )
  {
    super( automaton );
    super.setVelocity( 5 );
    m_wait = 0;
  }
  
  /*
   * O
   * ----------------------------------->
   * |
   * |    x E   x Q    theta = Angle(PEQ)
   * |     
   * |
   * |          x P
   * |
   * v
   */
  
  void follow( Entity entity )
  {
    Vector OP = entity.getPos();
    Vector OE = this.getPos();
    Vector EP = Vector.sub( OP, OE ); 
    // theta = Arctan( QP/EQ )
    super.setOrientation( Math.atan2( EP.getY(), EP.getX() ) );
    
    move();
  }
  
  @Override
  public void tick( long elapsed )
  {
    m_elapsedTime = elapsed;
    m_wait += elapsed;
    
    if( m_wait > WAIT_TIME_BEFORE_MOVING )
    {
      follow( Model.getPlayer() );
      m_wait -= WAIT_TIME_BEFORE_MOVING;
    }
    
  }
  
}
