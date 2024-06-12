package src.Model;

import java.awt.Color;

public abstract class Biome
{
  protected int    m_width;
  protected int    m_height;
  protected int    m_x;
  protected int    m_y;
  protected Vector m_entry;
  protected Color  m_color;
  protected String m_name;

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

  public Vector getEntry()
  {
    return m_entry;
  }

  public String getName()
  {
    return m_name;
  }
}
