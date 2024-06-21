package src.AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryFsm
{
  public enum CATEGORY
  {
    Adversary, Clue, Danger, Gate, Icon, Jumpable, Killable, Moveable, Obstacle, Pickable, Team, Util, Void, PlayerT,
    PlayerA, Power, Stuff, Underscore;
  }

  private CATEGORY         m_type;
  private List< CATEGORY > m_options;

  public CategoryFsm()
  {
    m_type = CATEGORY.Void;
    m_options = new ArrayList< CATEGORY >();
  }

  public CategoryFsm( CATEGORY cat )
  {
    m_options = new ArrayList< CATEGORY >();
    switch ( cat )
    {
    case Killable:
    case Jumpable:
    case Moveable:
    case Pickable:
      m_type = CATEGORY.Void;
      m_options.add( cat );
    default:
      m_type = cat;
    }
  }
  
  public CategoryFsm( CATEGORY type, CATEGORY option )
  {
    switch ( type )
    {
    case Killable:
    case Jumpable:
    case Moveable:
    case Pickable:
      m_type = CATEGORY.Void;
    default:
      m_type = type;
    }
    m_options = new ArrayList< CATEGORY >();
    switch ( option )
    {
    case Killable:
    case Jumpable:
    case Moveable:
    case Pickable:
      m_options.add( option );
    default:
      return;
    }
  }

  public CategoryFsm( CATEGORY type, List< CATEGORY > options )
  {
    switch ( type )
    {
    case Killable:
    case Jumpable:
    case Moveable:
    case Pickable:
      m_type = CATEGORY.Void;
    default:
      m_type = type;
    }
    m_options = options;
  }

  public void addOption( CATEGORY option )
  {
    if( m_options.contains( option ) ) return;
    switch ( option )
    {
    case Killable:
    case Jumpable:
    case Moveable:
    case Pickable:
      m_options.add( option );
    default:
      return;
    }
  }

  public void removeOption( CATEGORY option )
  {
    m_options.remove( option );
  }

  public CATEGORY getType()
  {
    return m_type;
  }

  public List< CATEGORY > getOptions()
  {
    return m_options;
  }

  public boolean isOpponent( CategoryFsm cat )
  {
    switch ( m_type )
    {
    case Adversary:
    case PlayerA:
    case Danger:
      switch ( cat.m_type )
      {
      case PlayerT:
      case Team:
        return true;
      default:
        return false;
      }
    case PlayerT:
    case Team:
      switch ( cat.m_type )
      {
      case Adversary:
      case PlayerA:
      case Danger:
        return true;
      default:
        return false;
      }
    default:
      return false;
    }
  }
  
  @Override
  public boolean equals( Object o )
  {
    if ( o instanceof CategoryFsm )
    {
      CategoryFsm cat = (CategoryFsm)o;
      if( !cat.getType().equals( getType() ) ) return false;
      Iterator< CATEGORY > iter = cat.getOptions().iterator();
      while( iter.hasNext() )
      {
        CATEGORY c = iter.next();
        if( !getOptions().contains( c ) ) return false;
      }
      return true;
    }
    return false;
  }
}
