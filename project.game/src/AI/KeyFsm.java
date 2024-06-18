package src.AI;

import src.Model.Entity;

public class KeyFsm implements ConditionFsm
{
  String m_key;

  public KeyFsm( String st )
  {
    m_key = st;
  }

  public KeyFsm()
  {
    m_key = null;
  }
  
  public String getKey() {
    return m_key;
  }

  public boolean isEqual( String st )
  {
    return m_key.equals( st );
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    System.out.println("Not yet implement , need controler");
    return false;
  }
}
