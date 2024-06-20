package src.AI.Action;

import java.util.ArrayList;
import java.util.Iterator;

import src.Model.Entity;

public class ActionMultiple implements ActionFsm
{
  ArrayList< ActionFsm > m_actions;

  public ActionMultiple()
  {
    m_actions = new ArrayList< ActionFsm >();
  }

  public ActionMultiple( ActionFsm a )
  {
    m_actions = new ArrayList< ActionFsm >();
    m_actions.add( a );
  }

  public void addAction( ActionFsm act )
  {
    m_actions.add( act );
  }

  @Override
  public void execute( Entity entity )
  {
    Iterator< ActionFsm > iter = m_actions.iterator();
    while( iter.hasNext() )
    {
      ActionFsm act = (ActionFsm)iter.next();
      act.execute( entity );
    }
  }

}
