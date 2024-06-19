package src.AI;

import java.util.List;

import src.AI.Action.ActionFactory;
import src.AI.Condition.ConditionFactory;

public class FuncallFactory
{
  FuncallFactory()
  {
    m_actionFact = new ActionFactory();
    m_conditionFact = new ConditionFactory();
  }

  // FIELDS

  ActionFactory m_actionFact;
  ConditionFactory m_conditionFact;

  // METHODS

  // Action
  Object get( String name, List< Object > parameters )
  {
    switch ( name )
    {
    case "Add":
      return m_actionFact.setAdd( parameters );
    case "Egg":
      return m_actionFact.setEgg( parameters );
    case "Explode":
      return m_actionFact.setExplode();
    case "Get":
      return m_actionFact.setGet();
    case "Hit":
      return m_actionFact.setHit( parameters );
    case "Jump":
      return m_actionFact.setJump( parameters );
    case "Move":
      return m_actionFact.setMove( parameters );
    case "Pick":
      return m_actionFact.setPick( parameters );
    case "Protect":
      return m_actionFact.setProtect( parameters );
    case "Rest":
      return m_actionFact.setRest( parameters );
    case "Store":
      return m_actionFact.setStore();
    case "Throw":
      return m_actionFact.setThrow( parameters );
    case "Turn":
      return m_actionFact.setTurn( parameters );
    case "Wait":
      return m_actionFact.setWait( parameters );
    case "Pop":
      return m_actionFact.setPop();
    case "Wizz":
      return m_actionFact.setWizz();
    
    // TODO Condition
//    case "True":
//      return m_conditionFact.set( new True(), parameters );
//    case "MyDir":
//      return m_conditionFact.set( new MyDir(), parameters );
//    case "Cell":
//      return m_conditionFact.set( new Cell(), parameters );
//    case "Closest":
//      return m_conditionFact.set( new Closest(), parameters );
//    case "Got":
//      return m_conditionFact.set( new Got(), parameters );
//    case "Key":
//      return m_conditionFact.set( new Key(), parameters );
    }
    return null;
  }
}
