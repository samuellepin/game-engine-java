package src.Model;

public abstract class Biome
{
  private int m_width;
  private int m_height;
  private int m_x;
  private int m_y;

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
}
