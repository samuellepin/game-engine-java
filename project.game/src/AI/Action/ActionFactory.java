package src.AI.Action;

import java.util.ArrayList;
import java.util.List;

import src.AI.DIRECTION;

public class ActionFactory
{
  private ArrayList< ActionFsm > m_actions = new ArrayList< ActionFsm >();

  private ActionFsm add( ActionFsm action )
  {
    if( m_actions.contains( action ) ) return m_actions.get( m_actions.indexOf( action ) );
    else
    {
      m_actions.add( action );
      return action;
    }
  }

  public ActionFsm setAdd( List< Object > parameters )
  {
    Add action = new Add();
    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof String && o2 instanceof Integer )
      {
        action = new Add( (String)o1, (Integer)o2 );
      }
    }
    return add( action );
  }

  public ActionFsm setEgg( List< Object > parameters )
  {
    Egg action = new Egg();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof DIRECTION )
    {
      action = new Egg( (DIRECTION)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setExplode()
  {
    return add( new Explode() );
  }

  public ActionFsm setGet()
  {
    return add( new Get() );
  }

  public ActionFsm setHit( List< Object > parameters )
  {
    Hit action = new Hit();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof DIRECTION )
    {
      action = new Hit( (DIRECTION)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setJump( List< Object > parameters )
  {
    Jump action = new Jump();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof DIRECTION && o2 instanceof Integer )
      {
        action = new Jump( (DIRECTION)o1, (Integer)o2 );
      }
    }
    return add( action );
  }

  public ActionFsm setMove( List< Object > parameters )
  {
    Move action = new Move();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof DIRECTION && o2 instanceof Integer )
      {
        action = new Move( (DIRECTION)o1, (Integer)o2 );
      }
    }
    return add( action );
  }

  public ActionFsm setPick( List< Object > parameters )
  {
    Pick action = new Pick();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof DIRECTION )
    {
      action = new Pick( (DIRECTION)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setProtect( List< Object > parameters )
  {
    Protect action = new Protect();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof DIRECTION && o2 instanceof Integer )
      {
        action = new Protect( (DIRECTION)o1, (Integer)o2 );
      }
    }
    return add( action );
  }

  public ActionFsm setRest( List< Object > parameters )
  {
    Rest action = new Rest();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof Integer && o2 instanceof Integer )
      {
        action = new Rest( (Integer)o1, (Integer)o2 );
      }
    }
    return add( action );
  }

  public ActionFsm setStore()
  {
    return add( new Store() );
  }

  public ActionFsm setThrow( List< Object > parameters )
  {
    Throw action = new Throw();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof DIRECTION )
    {
      action = new Throw( (DIRECTION)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setTurn( List< Object > parameters )
  {
    Turn action = new Turn();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof DIRECTION )
    {
      action = new Turn( (DIRECTION)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setWait( List< Object > parameters )
  {
    Wait action = new Wait();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof Integer )
    {
      action = new Wait( (Integer)parameters.get( 0 ) );
    }
    return add( action );
  }

  public ActionFsm setPop()
  {
    return add( new Pop() );
  }

  public ActionFsm setWizz()
  {
    return add( new Wizz() );
  }
}
