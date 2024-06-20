package src;

import java.util.Random;

import src.Model.Entity;

import src.Model.Model;
import src.Model.Spy;

public class Config
{
  private static final Config INSTANCE = Serializer.deserialize( "resources/Config.json", Config.class );

  private static final Random RANDOM   = new Random( Config.getInstance().getParameters().getSeed() );

  public static Random getRandom()
  {
    return RANDOM;
  }

  public static Config getInstance()
  {
    return INSTANCE;
  }

  private src.Model.Entity StringToEntity( Entity e )
  {
    switch ( e.getType() )
    {
    case "Spy":
      return new src.Model.Spy( null, e.getId(), e.getWidth(), e.getHeight(), e.getVelocity(), e.hasCollision() );
    case "Guard":
      return new src.Model.Guard( null, e.getId(), e.getWidth(), e.getHeight(), e.getVelocity(), e.hasCollision() );
    case "Wall":
      return new src.Model.Wall( null );
    case "Document":
      return new src.Model.Document( null, e.getId(), e.getWidth(), e.getHeight(), e.getVelocity(), e.hasCollision() );
    }
    return null;
  }

  public void initialize()
  {
    // create all the entities
    Model model = Model.getInstance();
    for ( Entity e : this.getEntities() )
    {
      src.Model.Entity newEntity = StringToEntity( e );
      newEntity.setId( e.getId() );
      model.addEntity( newEntity );
    }
    
    // put the viewports on the right entities
    model.setPlayer1( this.getParameters().getPlayer1() );
    model.setPlayer2( this.getParameters().getPlayer2() );
    model.setItemToWin( this.getParameters().getItemToWin() );
    
    // make copy of the enenmies
    for( int i = 0; i < this.enenmies.length; i++ )
    {
      Enemy enemy = this.enenmies[ i ];
      for ( src.Model.Entity e : model.getEntities() )
      {
        if( e.getId() == enemy.getId() )
        {
          model.addEnenmies( e, enemy.getMin(), enemy.getMax() );
          break;
        }
      }
    }
    
  }

  public class Parameters
  {
    public int     seed;
    public String  visionFieldRadius;
    public int     player1;
    public int     player2;
    public int     itemToWin;
    public boolean enableBSP;
    public boolean enableWalls;

    public src.Model.Entity getItemToWin()
    {
      Model model = Model.getInstance();
      for ( src.Model.Entity e : model.getEntities() )
      {
        if( e.getId() == itemToWin )
        {
          return e;
        }
      }
      return null;
    }

    public int getSeed()
    {
      return seed;
    }

    public double getVisionFieldRadius()
    {
      return Double.parseDouble( visionFieldRadius );
    }

    public src.Model.Entity getPlayer1()
    {
      Model model = Model.getInstance();
      for ( src.Model.Entity e : model.getEntities() )
      {
        if( e.getId() == player1 )
        {
          return e;
        }
      }
      return null;
    }

    public src.Model.Entity getPlayer2()
    {
      Model model = Model.getInstance();
      for ( src.Model.Entity e : model.getEntities() )
      {
        if( e.getId() == player2 )
        {
          return e;
        }
      }
      return null;
    }

    public boolean isBSPEnabled()
    {
      return enableBSP;
    }
    
    public boolean isWallsEnabled()
    {
      return enableWalls;
    }
  }

  public class World
  {
    public int   rowsNum;
    public int   colsNum;
    public Tile  tile;
    public Biome biome;

    public int getRowsNum()
    {
      return rowsNum;
    }

    public int getColsNum()
    {
      return colsNum;
    }

    public Tile getTile()
    {
      return tile;
    }

    public Biome getBiome()
    {
      return biome;
    }
  }

  public class Tile
  {
    public String width;
    public String height;

    public double getWidth()
    {
      return Double.parseDouble( width );
    }

    public double getHeight()
    {
      return Double.parseDouble( height );
    }
  }

  public class Biome
  {
    public int width;
    public int height;
    public int space;

    public int getWidth()
    {
      return width;
    }

    public int getHeight()
    {
      return height;
    }

    public int getSpace()
    {
      return space;
    }
  }

  public class View
  {
    public String  zoom;
    public int     screenWidth;
    public int     screenHeight;
    public String  title;

    public boolean paintHitbox;
    public boolean paintVisionField;

    public double getZoom()
    {
      return Double.parseDouble( zoom );
    }

    public int getScreenWidth()
    {
      return screenWidth;
    }

    public int getScreenHeight()
    {
      return screenHeight;
    }

    public String getTitle()
    {
      return title;
    }

    public boolean shouldPaintHitbox()
    {
      return paintHitbox;
    }

    public boolean shouldPaintVisionField()
    {
      return paintVisionField;
    }
  }

  class Entity
  {
    public int     id;
    public String  type;
    public String  width;
    public String  height;
    public boolean hasCollision;
    public String  velocity;
    public String  fsm;

    public int getId()
    {
      return id;
    }

    public String getType()
    {
      return type;
    }

    public double getWidth()
    {
      return Double.parseDouble( width );
    }

    public double getHeight()
    {
      return Double.parseDouble( height );
    }

    public boolean hasCollision()
    {
      return hasCollision;
    }

    public double getVelocity()
    {
      return Double.parseDouble( velocity );
    }

    public String getFSM()
    {
      return fsm;
    }
  }

  class Controllers
  {
    public PlayerControls player1;
    public PlayerControls player2;
  }

  class PlayerControls
  {
    public String right;
    public String left;
    public String up;
    public String down;
    public String hide;

    public String getRightKey()
    {
      return right;
    }

    public String getLeftKey()
    {
      return left;
    }

    public String getUpKey()
    {
      return up;
    }

    public String getDownKey()
    {
      return down;
    }

    public String getHideKey()
    {
      return hide;
    }
  }

  class Enemy
  {
    public int id;
    public int min;
    public int max;

    public int getId()
    {
      return id;
    }

    public int getMin()
    {
      return min;
    }

    public int getMax()
    {
      return max;
    }
  }

  class ItemsToPickToWin
  {
    public int id;
    public int min;
    public int max;

    public int getId()
    {
      return id;
    }

    public int getMin()
    {
      return min;
    }

    public int getMax()
    {
      return max;
    }
  }

  public Parameters       parameters;
  public World            world;
  public View             view;
  public Entity[]         entities;
  public Controllers      controllers;
  public Enemy[]          enenmies;
  public ItemsToPickToWin itemsToPickToWin;

  public Parameters getParameters()
  {
    return parameters;
  }

  public World getWorld()
  {
    return world;
  }

  public View getView()
  {
    return view;
  }

  public Entity[] getEntities()
  {
    return entities;
  }

  public Controllers getControllers()
  {
    return controllers;
  }

  public Enemy[] getEnenmies()
  {
    return enenmies;
  }

  public ItemsToPickToWin getItemsToPickToWin()
  {
    return itemsToPickToWin;
  }
}