package src.Model;

import src.AI.Automaton;
import src.Model.World.Tile;
import src.Model.Collision.AABB;

public class Wall extends Entity
{

  public Wall( Automaton automaton, Tile wall )
  {
    super( automaton );
    AABB hitbox = wall.getHitbox();
    super.setPos( hitbox.getMin().getX(),hitbox.getMin().getY() );
    super.setDim( hitbox.getWidth(), hitbox.getHeight() );
    super.updateHitbox();
  }

}