package src.AI.Action;

import java.util.ArrayList;
import java.util.Iterator;

import src.Model.Entity;
import src.Config;

public class ActionPercentage implements ActionFsm
{
  private class CoupleAction
  {
    double    m_percentage;
    ActionFsm m_action;

    CoupleAction( ActionFsm action, double percentage )
    {
      m_action = action;
      m_percentage = percentage;
    }

    ActionFsm getAction()
    {
      return m_action;
    }

    double getpercentage()
    {
      return m_percentage;
    }
  }

  private ArrayList< CoupleAction > m_actions = new ArrayList< CoupleAction >();

  public void add( ActionFsm action, int pourcentage )
  {
    m_actions.add( new CoupleAction( action, pourcentage ) );
  }

  @Override
  public void execute( Entity entity )
  {
    double                   floor = 0;
    double                   rand  = Config.getRandom().nextDouble();

    Iterator< CoupleAction > iter  = m_actions.iterator();

    while( iter.hasNext() && floor < 1 )
    {
      CoupleAction couple     = iter.next();
      double       percentage = couple.getpercentage();
      if( floor <= rand && rand < percentage )
      {
        couple.getAction().execute( entity );
        return;
      }
      floor += percentage;
    }
  }

}
