package src.Model.Entities;

import src.Model.Entity;

public class Box extends Entity
{
  private boolean m_isEmpty;
  private boolean m_isExplosed;
  
  public boolean isEmpty()
  {
    return m_isEmpty;
  }
  
  public boolean isExplosed()
  {
    return m_isExplosed;
  }
  
  public void setEmpty( boolean isEmpty )
  {
    m_isEmpty = isEmpty;
  }
  
  public Box()
  {
    super();
    m_isExplosed = false;
    m_isEmpty = true;
  }

  @Override
  public String toString()
  {
    return "Box - " + super.toString();
  }
  
  @Override
  public void doExplode()
  {
    if( this.isEmpty() ) return;
//    this.m_hp = 0;
//    this.setVisible( false );
    m_isExplosed = true;
  }
}
