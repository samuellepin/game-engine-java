package src.Model.World;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import src.Model.Vector;

public abstract class Biome
{
  protected int                 m_width;
  protected int                 m_height;
  protected int                 m_x;
  protected int                 m_y;
  protected ArrayList< Vector > m_entryList; // en coordonnées locales
  protected Color               m_color;
  protected String              m_name;
  protected int                 m_entryIndex;

  public int getX()
  {
    return m_x;
  }

  public int getY()
  {
    return m_y;
  }

  public int getHeight()
  {
    return m_height;
  }

  public int getWidth()
  {
    return m_width;
  }

  public String getName()
  {
    return m_name;
  }

  public Vector getEntry()
  {
    return m_entryList.get( m_entryIndex );
  }

  public void entryNext()
  {
    m_entryIndex++ ;
  }
}
