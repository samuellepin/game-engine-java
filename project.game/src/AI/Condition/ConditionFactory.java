package src.AI.Condition;

import java.util.ArrayList;
import java.util.List;

import src.AI.CategoryFsm;
import src.AI.Direction;
import src.AI.KEY;

public class ConditionFactory
{
  private ArrayList< ConditionFsm > m_conditions = new ArrayList< ConditionFsm >();

  private ConditionFsm add( ConditionFsm condition )
  {
    if( m_conditions.contains( condition ) ) return m_conditions.get( m_conditions.indexOf( condition ) );
    else
    {
      m_conditions.add( condition );
      return condition;
    }
  }

  public ConditionFsm setTrue()
  {
    return add( new True() );
  }

  public ConditionFsm setMyDir( List< Object > parameters )
  {
    MyDir condition = new MyDir();
    if( parameters.size() >= 1 && parameters.get( 0 ) instanceof Direction )
    {
      condition = new MyDir( (Direction)parameters.get( 0 ) );
    }
    return add( condition );
  }

  public ConditionFsm setCell( List< Object > parameters )
  {
    Cell condition = new Cell();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof Direction && o2 instanceof CategoryFsm )
      {
        condition = new Cell( (Direction)o1, (CategoryFsm)o2 );
      }
    }
    return add( condition );
  }

  public ConditionFsm setClosest( List< Object > parameters )
  {
    Closest condition = new Closest();

    if( parameters.size() >= 2 )
    {
      Object o1 = parameters.get( 0 );
      Object o2 = parameters.get( 1 );
      if( o1 instanceof CategoryFsm && o2 instanceof Direction )
      {
        condition = new Closest( (CategoryFsm)o1, (Direction)o2 );
      }
    }
    return add( condition );
  }

  public ConditionFsm setGot( List< Object > parameters )
  {
    Got condition = new Got();

    if( parameters.size() >= 1 )
    {
      Object o1 = parameters.get( 0 );
      if( parameters.size() >= 2 )
      {
        Object o2 = parameters.get( 1 );
        if( o1 instanceof CategoryFsm && o2 instanceof Integer )
        {
          return add( new Got( (CategoryFsm)o1, (Integer)o2 ) );
        }
      }
      if( o1 instanceof CategoryFsm )
      {
        condition = new Got( (CategoryFsm)o1 );
      }
    }
    return add( condition );
  }

  public ConditionFsm setKey( List< Object > parameters )
  {
    if( parameters.size() >= 1 )
    {
      Object param = parameters.get( 0 );
      if( param instanceof KEY )
      {
        return add( new Key( (KEY)param ) );
      }
    }
    return add( new Key() );
  }
}
