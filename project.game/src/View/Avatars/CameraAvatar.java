package src.View.Avatars;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Entity;
import src.Model.Vector;
import src.View.Avatar;

public class CameraAvatar extends Avatar
{
  public CameraAvatar( Entity e )
  {
    super( e );
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    double   theta  = m_entity.getOrientation().getValue();

    int[]    X      = new int[ 4 ];
    int[]    Y      = new int[ 4 ];
    Vector[] points = new Vector[ 4 ];
    points[ 0 ] = new Vector( x, y );
    points[ 1 ] = new Vector( x + width, y );
    points[ 2 ] = new Vector( x + width, y + height );
    points[ 3 ] = new Vector( x, y + height );

    double centroidX = 0;
    double centroidY = 0;
    for ( Vector point : points )
    {
      centroidX += point.getX();
      centroidY += point.getY();
    }
    centroidX /= points.length;
    centroidY /= points.length;

    for ( Vector point : points )
    {
      point.setX( point.getX() - centroidX );
      point.setY( point.getY() - centroidY );
    }

    for ( int i = 0; i < points.length; i++ )
    {
      points[ i ].rotate( theta );
      points[ i ].setX( points[ i ].getX() + centroidX );
      points[ i ].setY( points[ i ].getY() + centroidY );
      X[ i ] = (int)points[ i ].getX();
      Y[ i ] = (int)points[ i ].getY();
    }

    g.setColor( Color.darkGray );
    g.fillPolygon( X, Y, X.length );
  }

}
