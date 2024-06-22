package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.AI.FsmFactory;
import src.AI.CategoryFsm.CATEGORY;
import src.Model.Entity;

import src.Model.Model;
import src.Model.Spy;

public class Config
{
  private static final String FILENAME = "resources/Config-Alien.json";
  private static final Config INSTANCE = Serializer.deserialize( FILENAME, Config.class );
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
    FSM                          fsm          = FsmFactory.getInstance().getFSM( e.getFSM() );
    int                          id           = e.getId();
    double                       width        = e.getWidth();
    double                       height       = e.getHeight();
    double                       velocity     = e.getVelocity();
    boolean                      hasCollision = e.hasCollision();
    CategoryFsm.CATEGORY         type         = e.getTypeCat();
    List< CategoryFsm.CATEGORY > options      = e.getOptions();
    switch ( e.getType() )
    {
    case "Spy":
      return new src.Model.Spy( fsm, id, width, height, velocity, hasCollision, type, options );
    case "Guard":
      return new src.Model.Guard( fsm, id, width, height, velocity, hasCollision, type, options );
    case "Wall":
      return new src.Model.Wall( fsm, type, options );
    case "Document":
      return new src.Model.Document( fsm, id, width, height, velocity, hasCollision, type, options );
    case "Alien":
      return new src.Model.Alien( fsm, id, width, height, velocity, hasCollision, type, options );
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
    for ( int i = 0; i < this.enenmies.length; i++ )
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
    public String  backgroundMusic;
    public String  gameOverBGM;
    public String  volume;

    public String getGameOverBGM()
    {
      return gameOverBGM;
    }

    public float getVolume()
    {
      return Float.parseFloat( volume );
    }

    public String getBackgroundMusic()
    {
      return backgroundMusic;
    }

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
    public int    rowsNum;
    public int    colsNum;
    public Tile   tile;
    public Biome  biome;
    public String obstructionDensity;

    public double getObstructionDensity()
    {
      return Double.parseDouble( obstructionDensity.replace( "%", "" ) ) / 100.0;
    }

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
    public String   width;
    public String   height;
    public String   sprite;
    public String[] obstacles;

    public double getWidth()
    {
      return Double.parseDouble( width );
    }

    public double getHeight()
    {
      return Double.parseDouble( height );
    }

    public String getSprite()
    {
      return sprite;
    }

    public String[] getObstacles()
    {
      return obstacles;
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
    public int      id;
    public String   type;
    public String   width;
    public String   height;
    public boolean  hasCollision;
    public String   velocity;
    public String   fsm;
    public String   typeCat;
    public String[] options;

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

    public CategoryFsm.CATEGORY getTypeCat()
    {
      if( typeCat == null ) return CategoryFsm.CATEGORY.Void;
      switch ( typeCat )
      {
      case "Adversary":
        return CategoryFsm.CATEGORY.Adversary;
      case "Clur":
        return CategoryFsm.CATEGORY.Clue;
      case "Danger":
        return CategoryFsm.CATEGORY.Danger;
      case "Gate":
        return CategoryFsm.CATEGORY.Gate;
      case "Icon":
        return CategoryFsm.CATEGORY.Icon;
      case "Obstacle":
        return CategoryFsm.CATEGORY.Obstacle;
      case "Team":
        return CategoryFsm.CATEGORY.Team;
      case "Util":
        return CategoryFsm.CATEGORY.Util;
      case "Void":
        return CategoryFsm.CATEGORY.Void;
      case "PlayerT":
        return CategoryFsm.CATEGORY.PlayerT;
      case "PlayerA":
        return CategoryFsm.CATEGORY.PlayerA;
      case "Stuff":
        return CategoryFsm.CATEGORY.Stuff;
      default:
        return CategoryFsm.CATEGORY.Void;
      }
    }

    public List< CategoryFsm.CATEGORY > getOptions()
    {
      List< CategoryFsm.CATEGORY > opt = new ArrayList< CategoryFsm.CATEGORY >();
      if( options == null ) return opt;
      for ( int i = 0; i < options.length; i++ )
      {
        switch ( options[ i ] )
        {
        case "Jumpable":
          opt.add( CategoryFsm.CATEGORY.Jumpable );
        case "Killable":
          opt.add( CategoryFsm.CATEGORY.Killable );
        case "Moveable":
          opt.add( CategoryFsm.CATEGORY.Moveable );
        case "Pickable":
          opt.add( CategoryFsm.CATEGORY.Pickable );
        }
      }
      return opt;
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

  public Enemy[] getEnenmies()
  {
    return enenmies;
  }

  public ItemsToPickToWin getItemsToPickToWin()
  {
    return itemsToPickToWin;
  }
}