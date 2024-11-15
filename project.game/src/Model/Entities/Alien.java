package src.Model.Entities;

import java.util.List;

import src.Model.Entity;
import src.Model.Model;
import src.Model.Collision.Collision;

public class Alien extends Entity
{
  private Entity  m_metamorph;
  private boolean m_updateView;

  public void setUpdateView( boolean flag )
  {
    m_updateView = flag;
  }

  public boolean isViewUpdated()
  {
    return !m_updateView;
  }

  public Alien()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Alien - " + super.toString();
  }

  public void updateMetamorph( Entity e )
  {
    try
    {
      m_metamorph = e.clone();
    }
    catch ( CloneNotSupportedException e1 )
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    m_metamorph.setOriginEntity( this );
    this.setUpdateView( true );
  }

  public boolean isNonOriginForm()
  {
    return m_metamorph != null;
  }

  public Entity getMetamorph()
  {
    return m_metamorph;
  }

  @Override
  public void getHit( int damage )
  {
    this.subHP( damage );
  }

  @Override
  public void doWizz( List< Object > parameters ) // metamorphose
  {
    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( this != e && Collision.detect( this.getHitbox(), e.getHitbox() ) )
      {
        updateMetamorph( e );
      }
    }
    m_brain.step();
  }
}
