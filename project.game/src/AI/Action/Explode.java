package src.AI.Action;

import src.Model.Entity;
/*L'entité est détruite */
public class Explode implements ActionFsm
{

  @Override
  public void execute( Entity entity )
  {
    entity.doExplode();

  }
  
  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Explode ) return true;
    return false;
  }

}
