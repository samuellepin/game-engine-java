package src.AI.Action;

import src.Model.Entity;

public class Wizz implements ActionFsm
{

  @Override
  public void execute( Entity entity )
  {
    entity.doWizz();
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Wizz ) return true;
    return false;
  }

}
