package src.AI.Action;

import src.AI.CATEGORY;
import src.Model.Entity;

/*Incrémente la variable globale nommée var de n.*/
public class Add implements ActionFsm
{

  private CATEGORY m_cat;
  private int      m_n;

  public Add()
  {
    // TODO define the default values
    m_cat = CATEGORY.Void;
  }

  public Add( CATEGORY var, Integer n )
  {
    m_cat = var;
    m_n = n.intValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doAdd( m_cat, m_n );
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Add )
    {
      Add add = (Add)action;
      if( add.m_cat.equals( m_cat ) && add.m_n == m_n ) return true;
    }
    return false;
  }

}