package src;

public class Model
{
  private java.util.List< Entity > m_entities;
  
  void tick( int elapsed )
  {
    java.util.ListIterator< Entity > iter = m_entities.listIterator();
    
    while (iter.hasNext())
      iter.next().tick( elapsed );
  }
}
