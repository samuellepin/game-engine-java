package src.AI.Action;

import src.Model.Entity;
/*Prend l'objet de son inventaire suivant en main
 * met l'objet en main précédent dans l'inventaire.*/
public class Get implements ActionFsm
{

  @Override
  public void execute( Entity entity )
  {
    entity.doGet();

  }

}
