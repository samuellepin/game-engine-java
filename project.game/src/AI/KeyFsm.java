package src.AI;

public class KeyFsm
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
}
