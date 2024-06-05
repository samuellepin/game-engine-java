package src.AI;

public class State
{
  private int m_id;

  public State( int id )
  {
    m_id = id;
  }

  public void setID( int id )
  {
    m_id = id;
  }

  public int getID()
  {
    return m_id;
  }

  public boolean isEqual( State state )
  {
    return this.getID() == state.getID();
  }
}
