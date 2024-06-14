package src.AI;

public class State
{
  private int    m_id;
  private String m_name;

  public State( int id, String name )
  {
    m_id = id;
    m_name = name;
  }

  public void setID( int id )
  {
    m_id = id;
  }

  public int getID()
  {
    return m_id;
  }

  public void setName( String name )
  {
    m_name = name;
  }

  public String getName()
  {
    return m_name;
  }

  public boolean isEqual( State state )
  {
    return ( this.getID() == state.getID() ) && ( this.getName() == state.getName() );
  }
}
