package src.AI.Action;

import src.Model.Entity;

/*Incrémente la variable globale nommée var de n.*/
public class Add implements ActionFsm
{

  private String m_var;
  private int    m_n;

  public Add()
  {
    // TODO define the default values
    m_var = new String();
    m_n = 0;
  }

  public Add( String var, Integer n )
  {
    m_var = new String( var );
    m_n = n.intValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doAdd( m_var, m_n );
  }

}
