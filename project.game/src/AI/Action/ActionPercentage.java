package src.AI.Action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import src.Model.Entity;
import src.Model.Config;

public class ActionPercentage implements ActionFsm
{
  private class CoupleAction
  {
    int       m_percentage;
    ActionFsm m_action;

    CoupleAction( ActionFsm action, int percentage )
    {
      m_action = action;
      m_percentage = percentage;
    }

    ActionFsm getAction()
    {
      return m_action;
    }

    int getpercentage()
    {
      return m_percentage;
    }
  }

  private ArrayList< CoupleAction > m_actions = new ArrayList< CoupleAction >();

  public void add(ActionFsm action, int pourcentage) {
    m_actions.add( new CoupleAction(action,pourcentage) );
  }
  
  @Override
  public void execute( Entity entity )
  {
    
  }

}
