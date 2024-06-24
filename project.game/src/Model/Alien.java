package src.Model;

import java.awt.event.KeyEvent;
import java.util.List;

import src.Controller;
import src.AI.CategoryFsm;
import src.Model.Collision.Collision;
import src.Model.World.Map;
import src.AI.FSM;

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

  @Override
  public void tick( long dt )
  {
    super.tick( dt );
    if( Controller.getInstance().isKeyDown( KeyEvent.VK_SPACE ) )
    {
      System.out.println("PRESSED");
      for ( Entity e : Model.getInstance().getEntities() )
      {
        if( this != e && Collision.detect( this.getHitbox(), e.getHitbox() ) )
        {
          System.out.println("TRANSFORM " + e.toString());
          updateMetamorph( e );
        }
      }
//      
//      
//      
    }
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
}
