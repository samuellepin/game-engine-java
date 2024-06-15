package src.Model;

import src.AI.Automaton;

public class Opponent extends Player
{
  public Opponent( Automaton automaton )
  {
    super( automaton );
    super.setVelocity( 0.1 );
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
    follow( Model.getPlayer() );
  }
  
}
