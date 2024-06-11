package src.View;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import src.Model.Camera;
import src.Model.Vector;

public class Viewport
{
  JFrame                      m_frame;
  Camera                      m_cam;
  private ArrayList< Avatar > m_avatars;
  /* Width and height of the viewport in pixels */
  int                         m_width, m_height;

  public Viewport( JFrame frame, Camera cam, ArrayList< Avatar > avatars )
  {
    m_frame = frame;
    m_cam = cam;
    m_avatars = avatars;

    m_width = frame.getWidth();
    m_height = frame.getHeight();
  }

  public int metersToPixels( double d )
  {
    double d_px = d * m_width / m_cam.getWidth();

    return (int)d_px;
  }

  /* Converts the world position pos to a pixel coordinate on the viewport */
  public Vector worldPosToViewportPos( Vector pos )
  {
    Vector cameraPos   = new Vector( pos.getX() - m_cam.getPos().getX(), pos.getY() - m_cam.getPos().getY() );
    Vector viewportPos = new Vector( metersToPixels( cameraPos.getX() ), metersToPixels( cameraPos.getY() ) );
    return new Vector( (int)viewportPos.getX(), (int)viewportPos.getY() );
  }

  public void paint( Graphics g )
  {

  }
}
