package src.AI;

public class ValueFsm
{
  int m_val;
  
  public ValueFsm()
  {
    m_val = 0;
  }

  public ValueFsm(int val)
  {
    m_val = val;
  }

  public int getVal()
  {
    return m_val;
  }

  public void setVal(int val)
  {
    m_val = val;
  }
}
