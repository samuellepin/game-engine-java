package src.Model.Biome;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import src.Model.Vector;

public class BiomeA extends Biome
{
  public BiomeA( int x, int y )
  {
    m_x = x;
    m_y = y;
    m_height = 5;
    m_width = 5;
    m_entryIndex = 0;
    m_color = Color.green;
    m_name = "BiomeA";
    m_entryList = new ArrayList<>();
    m_entryList.add( new Vector( 0, m_height - 1 ) );
    m_entryList.add( new Vector( m_width - 1, 0 ) );
    m_entryList.add( new Vector( 0, 0 ) );
    m_entryList.add( new Vector( m_width - 1, m_height - 1 ) );
    Collections.shuffle( m_entryList );
  }
}
