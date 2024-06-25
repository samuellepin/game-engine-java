package src.AI;

import java.util.ArrayList;

import src.AI.Action.ActionFsm;
import src.Model.Entity;

public class Brain
{
  private FSM                    m_fsm;
  private Entity                 m_entity;
  private StateFsm               m_state;
  private ArrayList< ActionFsm > m_actions;

  public Brain( Entity e )
  {
    m_fsm = null;
    m_entity = e;
    m_state = null;
    m_actions = new ArrayList< ActionFsm >();
  }
  
  public Brain( Entity e, FSM a )
  {
    m_entity = e;
    m_fsm = a;
    m_state = m_fsm.getInitialState();
    m_actions = new ArrayList< ActionFsm >();
  }
  
  public void setFSM( FSM fsm )
  {
    m_fsm = fsm;
    m_state = m_fsm.getInitialState();
  }
  
  public FSM getFSM()
  {
    return m_fsm;
  }

  /* L'entité doit appeler cette fonction quand elle a fini son action actuelle */
  public void step()
  {
    if( m_actions.isEmpty() )
    {
      TransitionFsm t = m_fsm.nextState( m_entity, m_state );
      m_state = t.getDestination();
      m_actions.add( t.getAction() );
    }
    else
    {
      ActionFsm action = m_actions.remove( 0 );
      action.execute( m_entity );
    }
  }
  
  public Entity getEntity()
  {
    return m_entity;
  }
  
  public void setEntity( Entity entity )
  {
    m_entity = entity;
  }
}
