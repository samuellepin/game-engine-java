package src;

public class Config
{
  private static final Config INSTANCE = Serializer.deserialize( "resources/Config.json", Config.class );

  public static Config getInstance()
  {
    return INSTANCE;
  }

  class Parameters
  {
    public int     seed;
    public String  visionFieldRadius;
    public String  player1;
    public String  player2;
    public String  zoom;
    public int     screenWidth;
    public int     screenHeight;
    public String  title;
    public boolean enableBSP;

    public int getSeed()
    {
      return seed;
    }

    public double getVisionFieldRadius()
    {
      return Double.parseDouble( visionFieldRadius );
    }

    public Entity getPlayer1()
    {
      return null;
    }

    public Entity getPlayer2()
    {
      return null;
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

    public boolean isBSPEnabled()
    {
      return enableBSP;
    }
  }

  class World
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

  class Tile
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

  class Biome
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

  class View
  {
    public boolean paintHitbox;
    public boolean paintVisionField;

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
    public String  id;
    public String  type;
    public String  width;
    public String  height;
    public boolean hasCollision;
    public String  velocity;
    public String  fsm;

    public int getId()
    {
      return Integer.parseInt( id );
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
    public String id;
    public int    min;
    public int    max;

    public int getId()
    {
      return Integer.parseInt( id );
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
    public String id;
    public int    min;
    public int    max;

    public int getId()
    {
      return Integer.parseInt( id );
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
  public Enemy[]          enemies;
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
  
  public Enemy[] getEnemies()
  {
    return enemies;
  }
  
  public ItemsToPickToWin getItemsToPickToWin()
  {
    return itemsToPickToWin;
  }
}