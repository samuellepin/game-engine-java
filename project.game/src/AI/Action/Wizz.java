package src.AI.Action;

import java.util.List;

import src.Model.Entity;

public class Wizz implements ActionFsm
{
  List< Object > m_parameters;

  public Wizz()
  {
    m_parameters = null;
  }

  public Wizz( List< Object > parameters )
  {
    m_parameters = parameters;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doWizz( m_parameters );
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Wizz ) return true;
    return false;
  }

}
