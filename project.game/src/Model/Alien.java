package src.Model;

import java.awt.event.KeyEvent;
import java.util.List;

import src.Controller;
import src.AI.CategoryFsm;
import src.Model.World.Map;
import src.AI.FSM;

public class Alien extends Entity
{
  private Entity m_metamorph;
  private boolean m_updateView;
  
  public void setUpdateView(boolean flag)
  {
    m_updateView = flag;
  }
  
  public boolean isViewUpdated()
  {
    return !m_updateView;
  }

  public Alien( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options, hp );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Alien - " + super.toString();
  }
  
  @Override
  public void tick( long dt )
  {
    super.tick( dt );
    if( Controller.getInstance().isKeyDown( KeyEvent.VK_SPACE ) )
    {
      m_metamorph = new Rabbit( this.getFSM() );
      this.setUpdateView( true );
    }
  }
  
  public boolean isMetamorphosed()
  {
    return m_metamorph != null;
  }
  
  public Entity getMetamorph()
  {
    return m_metamorph;
  }
}