package src.AI.Action;

import src.Model.Entity;

public class Pop implements ActionFsm
{

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Pop ) return true;
    return false;
  }

}
