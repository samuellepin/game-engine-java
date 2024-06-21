package src.AI;

import java.util.ArrayList;

public class FsmFactory
{
  private static FsmFactory INSTANCE = new FsmFactory();
  
  public static FsmFactory getInstance()
  {
    return INSTANCE;
  }
  
  private ArrayList<FSM> m_fsm;
  
  private FsmFactory()
  {
    m_fsm = FsmCreator.loadAutomata( "resources/fsm.gal" );
  }
  
  public FSM getFSM( String name )
  {
    for( FSM fsm : m_fsm )
    {
      if( fsm.getName().equals( name ) )
      {
        return fsm;
      }
    }
    return null;
  }
}
