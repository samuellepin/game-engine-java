package src.AI.Action;

import src.Model.Entity;

/*Place l'objet tenu en main dans l'inventaire.*/
public class Store implements ActionFsm
{

  @Override
  public void execute( Entity entity )
  {
    entity.doStore();

  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Store ) return true;
    return false;
  }

}
