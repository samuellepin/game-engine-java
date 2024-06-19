package src.FSM2;

public class State
{
  private String m_name;

  public State( String name )
  {
    m_name = name;
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
    return this.getName().equals( state.getName() );
  }
}
