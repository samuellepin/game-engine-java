package src.Model;

import java.util.ArrayList;
import java.util.Iterator;

import src.Config;
import src.Game;
import src.Model.Collision.Collision;
import src.Model.Entities.Generator;
import src.Model.World.Map;

public class Model
{
  private static final Model         INSTANCE = new Model();

  private ArrayList< Entity >        m_entities;            /// < Players, Walls, Items, etc.
  private ArrayList< EntityTracker > m_trackers;
  private boolean                    m_isGameOver;
  private Entity                     m_player1;
  private Entity                     m_player2;
  private ArrayList< Entity >        m_keyItems;
  private ArrayList< Shot >          m_shots;
  private Entity                     m_exit;
  private boolean                    m_isVictory;
  
  public void setVictory( boolean isVictory )
  {
    m_isVictory = isVictory;
  }
  
  public boolean isVictory()
  {
    return m_isVictory;
  }

  public static Model getInstance()
  {
    return INSTANCE;
  }

  private Model()
  {
    m_isGameOver = false;
    m_isVictory = false;

    m_keyItems = new ArrayList< Entity >();
    m_entities = new ArrayList< Entity >();
    m_trackers = new ArrayList< EntityTracker >();
    m_shots = new ArrayList< Shot >();

    for ( Entity e : Map.getInstance().getWalls() )
    {
      m_entities.add( e );
    }
  }

  public void tick( long elapsed )
  {
    // ATTENTION - PEUT ETRE SOURCE DE PROBLEME
    // UTILE CAR ON MODIFIE LA LISTE
    // EN L'ITERANT
    ArrayList< Entity > list = (ArrayList< Entity >)m_entities.clone();
    for ( Entity e : list )
    {
      e.tick( elapsed );
    }

    Iterator< Shot > iterator = m_shots.iterator();
    while( iterator.hasNext() )
    {
      Shot shot = iterator.next();
      shot.update( elapsed );
      if( shot.hasTouched() )
      {
        iterator.remove();
      }
    }

    
    if( m_exit != null && ( Collision.detect( m_player1.getHitbox(), m_exit.getHitbox() )
        && m_player1.getInventory().size() == Model.getInstance().getKeyItems().size() ) || m_player1.isDead() )
    {
      if( !m_player1.isDead() )
      {
        this.setVictory( true );
      }
      setGameOver();
    }

    Iterator< Entity > it = m_keyItems.iterator();
    while( it.hasNext() )
    {
      Entity entity = it.next();
      if( Collision.detect( m_player1.getHitbox(), entity.getHitbox() ) )
      {
        m_player1.addItemToInventory( entity );
        m_entities.remove( entity );
      }
    }

  }

//if( m_itemToWin instanceof Generator )
//{
//  ( (Generator)m_itemToWin ).enable();
//}
//else
//{
//}

  public void setGameOver()
  {
    m_isGameOver = true;
    Game game = Game.getInstance();
    game.stopMusic( Config.getInstance().getParameters().getBackgroundMusic() );
//    game.loadMusic( Config.getInstance().getParameters().getGameOverBGM() );
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public void addEntities( Entity entity )
  {
    m_entities.add( entity );
  }

  public void removeEntities( Entity entity )
  {
    m_entities.remove( entity );
  }

  public Entity getPlayer1()
  {
    return m_player1;
  }

  public void setPlayer1( Entity e )
  {
    m_player1 = e;
  }

  public void setPlayer2( Entity e )
  {
    m_player2 = e;
  }

  public Entity getPlayer2()
  {
    return m_player2;
  }

  public ArrayList< EntityTracker > getTrackers()
  {
    return m_trackers;
  }

  public void addEntity( Entity e )
  {
    if( m_entities.contains( e ) ) return;
    m_entities.add( e );
  }

  public void addKeyItems( Entity e )
  {
    m_keyItems.add( e );
  }

  public ArrayList< Entity > getKeyItems()
  {
    return m_keyItems;
  }

  public void addEnenmies( Entity entity, int min, int max )
  {
    int num = min + Config.getRandom().nextInt( max - min );
    for ( int i = 0; i < num; i++ )
    {
      Entity e = null;
      try
      {
        e = entity.clone();
      }
      catch ( CloneNotSupportedException except )
      {
        except.printStackTrace();
      }
      try
      {
        e.correctBrain( e );
      }
      catch ( CloneNotSupportedException except )
      {
        except.printStackTrace();
      }
      e.setId( e.getId() + i + 1 );
      e.setPos( Map.getInstance().getRandomPos() );
      this.addEntity( e );
    }
  }

  public void addShot( Shot shot )
  {
    m_shots.add( shot );
  }

  public ArrayList< Shot > getShots()
  {
    return m_shots;
  }

  public Entity getExit()
  {
    return m_exit;
  }

  public void setExit( Entity e )
  {
    m_exit = e;
  }

  public void removeEntity( Entity e )
  {
    m_entities.remove( e );
  }
}
