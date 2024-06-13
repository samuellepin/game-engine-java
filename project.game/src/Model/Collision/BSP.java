package src.Model.Collision;

import java.util.ArrayList;

public class BSP
{
  private class Node
  {
    private Node m_left, m_right;
    private ArrayList<AABB> m_hitbox;
  }
  
  private Node m_root;
  
  
}
