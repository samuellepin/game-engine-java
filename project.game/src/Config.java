package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.AI.FsmFactory;
import src.Model.Angle;

import src.Model.Model;
import src.Model.World.Map;

public class Config
{
  private static final String FILENAME = "resources/Config-Alien.json";
  private static final Config INSTANCE = Serializer.deserialize( FILENAME, Config.class );
  private static final Random RANDOM   = new Random( Config.getInstance().getParameters().getSeed() );

  public static Angle stringToAngle( String value )
  {
    return new Angle( Double.parseDouble( value.replace( "°", "" ) ), true );
  }

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
    src.Model.Entity entity = null;

    switch ( e.getType() )
    {
    case "Alien":
      entity = new src.Model.Entities.Alien();
      break;
    case "Box":
      entity = new src.Model.Entities.Box();
      break;
    case "Camera":
      entity = new src.Model.Entities.Camera();
      break;
    case "Document":
      entity = new src.Model.Entities.Document();
      break;
    case "Dove":
      entity = new src.Model.Entities.Dove();
      break;
    case "Generator":
      entity = new src.Model.Entities.Generator();
      break;
    case "Guard":
      entity = new src.Model.Entities.Guard();
      break;
    case "Mouse":
      entity = new src.Model.Entities.Mouse();
      break;
    case "Rabbit":
      entity = new src.Model.Entities.Rabbit();
      break;
    case "Raven":
      entity = new src.Model.Entities.Raven();
      break;
    case "Robot":
      entity = new src.Model.Entities.Robot();
      break;
    case "Spy":
      entity = new src.Model.Entities.Spy();
      break;
    case "Squirrel":
      entity = new src.Model.Entities.Squirrel();
      break;
    case "Stairs":
      entity = new src.Model.Entities.Stairs();
      break;
    case "UFO":
      entity = new src.Model.Entities.UFO();
      break;
    case "Wall":
      entity = new src.Model.Entities.Wall();
      break;
    default:
      throw new IllegalArgumentException( "Unknown entity type: " + e.getType() );
    }

    entity.setFSM( e.getFSM() );
    entity.setId( e.getId() );
    entity.setDim( e.getWidth(), e.getHeight() );
    entity.setVelocity( e.getVelocity() );
    entity.setMaxHP( e.getMaxHP() );
    entity.setHasCollision( e.hasCollision() );
    entity.setCategory( e.getTypeCat() );
    entity.setPos( Map.getInstance().getRandomPos() );
    // entity.setOptions( e.getOptions() );

    return entity;
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
    model.setExit( this.getParameters().getExit() );
    this.updateKeyItems();

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
    public String  visionFieldApertureAngle;
    public int     player1;
    public int     player2;
    public int     exit;
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

    public Angle getVisionFieldApertureAngle()
    {
      return Config.stringToAngle( visionFieldApertureAngle );
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

    public src.Model.Entity getExit()
    {
      Model model = Model.getInstance();
      for ( src.Model.Entity e : model.getEntities() )
      {
        if( e.getId() == exit )
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
    public boolean enableReducedVisionField;

    public boolean paintHitbox;
    public boolean paintVisionField;

    public boolean isReducedVisionFieldEnabled()
    {
      return enableReducedVisionField;
    }

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
    public String   category;
    public String[] options;
    public int      hp;

    public int getMaxHP()
    {
      return hp;
    }

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

    public FSM getFSM()
    {
      return FsmFactory.getInstance().getFSM( fsm );
    }
    public CategoryFsm.CATEGORY getTypeCat() {
      if (category == null) return CategoryFsm.CATEGORY.Void;
      switch (category) {
          case "Adversary":
              return CategoryFsm.CATEGORY.Adversary;
          case "Clue":
              return CategoryFsm.CATEGORY.Clue;
          case "Danger":
              return CategoryFsm.CATEGORY.Danger;
          case "Gate":
              return CategoryFsm.CATEGORY.Gate;
          case "Icon":
              return CategoryFsm.CATEGORY.Icon;
          case "Jumpable":
              return CategoryFsm.CATEGORY.Jumpable;
          case "Killable":
              return CategoryFsm.CATEGORY.Killable;
          case "Moveable":
              return CategoryFsm.CATEGORY.Moveable;
          case "Obstacle":
              return CategoryFsm.CATEGORY.Obstacle;
          case "Pickable":
              return CategoryFsm.CATEGORY.Pickable;
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
          case "Power":
              return CategoryFsm.CATEGORY.Power;
          case "Stuff":
              return CategoryFsm.CATEGORY.Stuff;
          case "Underscore":
              return CategoryFsm.CATEGORY.Underscore;
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

  public Parameters parameters;
  public World      world;
  public View       view;
  public Entity[]   entities;
  public Enemy[]    enenmies;
  public int[]      keyItems;

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

  public void updateKeyItems()
  {
    Model model = Model.getInstance();
    for ( src.Model.Entity e : model.getEntities() )
    {
      for( int id : keyItems )
      {
        if( id == e.getId() )
        {
          model.addKeyItems( e );
        }
      }
    }
  }
}