package src.Model;

import java.awt.Color;

public class BiomeA extends Biome
{
  public BiomeA(int x,int y)
  {
    m_x=x;
    m_y=y;
    m_height = 15;
    m_width = 15;
    m_entry = new Vector( 0, 5 );// en coordonnées locales
    m_color=Color.green;
  }
  
}
