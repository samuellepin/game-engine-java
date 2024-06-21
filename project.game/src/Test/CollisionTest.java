package src.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import info3.game.graphics.GameCanvas;
import src.Controller;
import src.Model.Vector;
import src.Model.Collision.AABB;
import src.Model.Collision.Arc;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

public class CollisionTest
{
  private static CollisionTest INSTANCE;
  public static final int      SCREEN_WIDTH  = 600;
  public static final int      SCREEN_HEIGHT = 600;

  public static void main( String args[] ) throws Exception
  {
    try
    {
      INSTANCE = new CollisionTest();
    }
    catch ( Throwable th )
    {
      th.printStackTrace( System.err );
    }
  }

  private JFrame         m_frame;
  private CanvasListener m_listener;
  private GameCanvas     m_canvas;
  private AABB           m_aabb;
  private Arc            m_arc;
  private boolean        m_collision;
  private Vector         m_closetPoint;

  public static CollisionTest getInstance()
  {
    return INSTANCE;
  }

  private CollisionTest() throws Exception
  {
    m_aabb = new AABB( 270, 150, 300, 180 );
    m_arc = new Arc( 200, 200, 150, 0, Math.PI / 32 );

    m_listener = new CanvasListener( this );

    m_canvas = new GameCanvas( m_listener );
    m_canvas.setSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );

    m_frame = m_canvas.createFrame( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );
    m_frame.setTitle( "Collision Test" );
    m_frame.setLayout( new FlowLayout() );
    m_frame.add( m_canvas );
    m_frame.setLocationRelativeTo( null );
    m_frame.setVisible( true );
  }

  void tick( long elapsed )
  {
    m_collision = Collision.detect( m_aabb, m_arc );
    m_closetPoint = Collision.closestPointToAABB( m_aabb, m_arc.getCenter() );

    Controller ctr = Controller.getInstance();
    double     d   = 0.1 * elapsed;
    if( ctr.isKeyDown( KeyEvent.VK_UP ) )
    {
      m_arc.translate( 0, -d );
    }
    if( ctr.isKeyDown( KeyEvent.VK_DOWN ) )
    {
      m_arc.translate( 0, d );
    }
    if( ctr.isKeyDown( KeyEvent.VK_RIGHT ) )
    {
      m_arc.translate( d, 0 );
    }
    if( ctr.isKeyDown( KeyEvent.VK_LEFT ) )
    {
      m_arc.translate( -d, 0 );
    }
    if( ctr.isKeyDown( KeyEvent.VK_SPACE ) )
    {
      m_arc.rotate( 0.01 );
    }
  }

  void paint( Graphics g )
  {
    g.setColor( Color.DARK_GRAY );
    g.fillRect( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );

    g.setColor( Color.white );
    g.fillRect( (int)m_aabb.getX(), (int)m_aabb.getY(), (int)m_aabb.getWidth(), (int)m_aabb.getHeight() );

    g.setColor( new Color( 255, 0, 255, 140 ) );
    g.fillArc( (int)m_arc.getX(), (int)m_arc.getY(), (int)m_arc.getWidth(), (int)m_arc.getHeight(),
        (int)Arc.radianToDegree( m_arc.getStartAngle() ), (int)Arc.radianToDegree( m_arc.getArcAngle() ) );

    if( m_collision )
    {
      g.setColor( Color.red );
      g.drawString( "COLLISION!", 20, 20 );
    }
    else
    {
      g.setColor( Color.white );
      g.drawString( "NO COLLISION", 20, 20 );
    }

    g.setColor( Color.white );
    g.drawString( m_closetPoint.toString(), 20, 40 );
    double        min = m_arc.getStartAngle();
    double        max = m_arc.getArcAngle() + min;
    DecimalFormat df  = new DecimalFormat( "#.0" );
    g.drawString(
        "min = " + df.format( Vector.normalizeAngle( min ) ) + ", max = " + df.format( Vector.normalizeAngle( max ) ),
        20, 60 );

    g.setColor( Color.red );
    g.fillOval( (int)m_closetPoint.getX() - 2, (int)m_closetPoint.getY() - 2, 4, 4 );
  }

}