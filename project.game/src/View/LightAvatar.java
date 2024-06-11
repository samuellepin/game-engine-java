package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Model.Entity;
import src.Model.Light;
import src.Model.Model;
import src.Model.Rectangle;
import src.Model.Vector;

public class LightAvatar implements Avatar
{
  private Light       m_light;
  private Color       m_color;
  ArrayList< Entity > m_entities;

  public LightAvatar( Light light )
  {
    m_light = light;
    m_color = new Color( 1.0f, 1.0f, 0.0f, 1f );
  }

  public LightAvatar( Light light, ArrayList< Entity > entities )
  {
    m_light = light;
    m_color = new Color( 1.0f, 1.0f, 0.0f, 0.2f );
    m_entities = entities;
  }

  public void paintShadow( Graphics g, Rectangle rect )
  {
    double radius = 10 * m_light.getRadius();

    Vector points[] = rect.getPoints();
    Vector playerPos = Model.getPlayerPos();

    int minIdx = -1;
    double minAngle = 0;
    int maxIdx = -1;
    double maxAngle = 0;
    
    for( int i = 0; i < points.length; i++ )
    {
      double theta = Math.atan2( ( points[ i ].getY() - playerPos.getY() ), ( points[ i ].getX() - playerPos.getX() ) );
      if( minIdx == -1 && maxIdx == -1 )
      {
        minIdx = i;
        minAngle = theta;
        maxIdx = i;
        maxAngle = theta;
        continue;
      }
      else if( theta < minAngle )
      {
        minIdx = i;
        minAngle = theta;
      }
      else if( theta > maxAngle )
      {
        maxIdx = i;
        maxAngle = theta;
      }
    }
    Vector[] shadow = new Vector[4];
    shadow[ 0 ] = points[ minIdx ];
    shadow[ 1 ] = new Vector( radius * Math.cos( minAngle ), radius * Math.sin( minAngle ) );
    shadow[ 1 ] = Vector.add( shadow[ 0 ], shadow[ 1 ] );
    shadow[ 3 ] = points[ maxIdx ];
    shadow[ 2 ] = new Vector( radius * Math.cos( maxAngle ), radius * Math.sin( maxAngle ) );
    shadow[ 2 ] = Vector.add( shadow[ 2 ], shadow[ 3 ] );
    
    g.setColor( Color.black );
    g.fillPolygon( new int[] { 
            (int)shadow[ 0 ].getRX(), 
            (int)shadow[ 1 ].getRX(), 
            (int)shadow[ 2 ].getRX(), 
            (int)shadow[ 3 ].getRX() }, new int[] { 
                (int)shadow[ 0 ].getRY(), 
                (int)shadow[ 1 ].getRY(), 
                (int)shadow[ 2 ].getRY(), 
                (int)shadow[ 3 ].getRY() }, shadow.length );
  }

  @Override
  public void paint( Graphics g )
  {
    double width  = 2 * m_light.getRadius();
    Vector pos    = Model.getPlayerPos();
    double posX   = pos.getRX() - m_light.getRadius();
    double posY   = pos.getRY() - m_light.getRadius();

    g.setColor( m_color );
    g.fillOval( (int)posX, (int)posY, (int)width, (int)width );

    g.setColor( Color.red );
    
    width = 20;
    posX = pos.getRX() - 10;
    posY = pos.getRY() - 10;
    g.fillOval( (int)posX, (int)posY, (int)width, (int)width );
    
    this.paintShadow( g, (Rectangle)m_entities.get( 0 ) );
  }

}