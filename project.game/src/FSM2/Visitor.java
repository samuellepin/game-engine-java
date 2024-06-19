package src.FSM2;

import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.FunCall;
import gal.ast.IVisitor;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.State;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;
import gal.parser.Parser;
import src.FSM2.Condition.Negation;

public class Visitor implements IVisitor
{
  public Visitor()
  {
    clear();
  }

  // EXPORT

  public String export()
  {
    return string_builder.toString();
  }

  // FIELDS

  private FSM   m_fsm;
  StringBuilder string_builder;

  int           indentation;

  void indent()
  {
    indentation += 2;
  }

  void nident()
  {
    indentation -= 2;
  }

  void indentation()
  {
    for ( int i = 0; i < indentation; i++ )
    {
      string_builder.append( " " );
    }
  }

  void newline()
  {
    string_builder.append( "\n" );
    indentation();
  }

  void clear()
  {
    string_builder = new StringBuilder();
    indentation = 0;
  }

  void print( String string )
  {
    string_builder.append( string );
  }

  // REQUIRED BY INTERFACE Visitor

  @Override
  public Object visit( Category cat )
  {
    switch ( cat.toString() )
    {
    case "Adversary":
    case "A":
      return CATEGORY.ADVERSARY;
    case "Clue":
    case "C":
      return CATEGORY.CLUE;
    case "Gate":
    case "G":
      return CATEGORY.GATE;
    case "Jumpable":
    case "J":
      return CATEGORY.JUMPABLE;
    case "Obstacle":
    case "O":
      return CATEGORY.OBSTACLE;
    case "Pickable":
    case "P":
      return CATEGORY.PICKABLE;
    case "Team":
    case "T":
      return CATEGORY.TEAM;
    case "Void":
    case "V":
      return CATEGORY.VOID;
    case "PlayerT":
    case "@":
      return CATEGORY.PLAYER_T;
    case "PlayerA":
    case "#":
      return CATEGORY.PLAYER_A;
    case "Power":
      return CATEGORY.POWER;
    case "Stuff":
      return CATEGORY.STUFF;
    }
    return null;
  }

  @Override
  public Object visit( Direction dir )
  {
    switch ( dir.toString() )
    {
    case "Here":
    case "H":
      return DIRECTION.HERE;
    case "North":
    case "N":
      return DIRECTION.NORTH;
    case "South":
    case "S":
      return DIRECTION.SOUTH;
    case "East":
    case "E":
      return DIRECTION.EAST;
    case "West":
    case "W":
      return DIRECTION.WEST;
    case "NE":
      return DIRECTION.NORTH_EAST;
    case "SE":
      return DIRECTION.SOUTH_EAST;
    case "SW":
      return DIRECTION.SOUTH_WEST;
    case "NW":
      return DIRECTION.NORTH_WEST;
    case "Forward":
    case "F":
      return DIRECTION.FORWARD;
    case "BackWard":
    case "B":
      return DIRECTION.BACKWARD;
    case "Left":
    case "L":
      return DIRECTION.LEFT;
    case "Right":
    case "R":
      return DIRECTION.RIGHT;
    }
    return null;
  }

  @Override
  public Object visit( Key key )
  {
    return key.toString();
  }

  @Override
  public Object visit( Value v )
  {
    return Integer.parseInt( v.toString() );
  }

  @Override
  public Object visit( Underscore u )
  {
    return u.toString();
  }

  // FUNCALL

  @Override
  public void enter( FunCall funcall )
  {
    if( funcall.percent != FunCall.NO_PERCENT ) print( String.format( "%d%% ", funcall.percent ) );
    print( funcall.name );
    if( !funcall.parameters.isEmpty() ) print( "(" );
  }

  public void visit( FunCall funcall )
  {
    print( "," );
  }

  public void exit( FunCall funcall )
  {
    if( !funcall.parameters.isEmpty() ) print( ")" );
  }

  @Override
  public Object build( FunCall funcall, List< Object > parameters )
  {
    return null;
  }

  // BINOP

  public void enter( BinaryOp binop )
  {
    print( "( " );
  }

  public void visit( BinaryOp binop )
  {
    print( String.format( " %s ", binop.operator ) );
  }

  public void exit( BinaryOp binop )
  {
    print( " )" );
  }

  @Override
  public Object build( BinaryOp binop, Object left, Object right )
  {
    return null;
  }

  // UNOP

  public void enter( UnaryOp unop )
  {
  }

  public void exit( UnaryOp unop )
  {
  }

  @Override
  public Object build( UnaryOp operator, Object expression )
  {
    switch ( operator.operator )
    {
    case "!":
      return new Negation( (src.FSM2.Condition.Condition)expression );
    }
    return null;
  }

  // STATE

  @Override
  public Object visit( State state )
  {
    return new src.FSM2.State( state.toString() );
  }

  // MODE

  @Override
  public void enter( Mode mode )
  {
    indent();
    newline();
    print( String.format( "* (%s): ", mode.state.name ) );
  }

  public void visit( Mode mode )
  {
  }

  public void exit( Mode mode )
  {
    nident();
    newline();
  }

  @Override
  public Object build( Mode mode, Object source_state, Object behaviour )
  {
    return null;
  }

  // BEHAVIOUR

  @Override
  public Object visit( Behaviour behaviour, List< Object > transitions )
  {
    return null;
  }

  // CONDITION

  @Override
  public void enter( Condition condition )
  {
  }

  public void exit( Condition condition )
  {
  }

  @Override
  public Object build( Condition condition, Object expression )
  {
    print( " ? " );
    return null;
  }

  // ACTION

  @Override
  public void enter( Actions action )
  {
  }

  public void visit( Actions action )
  {
    print( String.format( " %s ", action.operator ) );
  }

  public void exit( Actions action )
  {
  }

  @Override
  public Object build( Actions action, String operator, List< Object > funcalls )
  {
    return null;
  }

  // TRANSITION

  @Override
  public void enter( Transition transition )
  {
    newline();
    print( "| " );
  }

  @Override
  public void exit( Transition transition )
  {
    print( String.format( "  :(%s)", transition.target.name ) );
  }

  @Override
  public Object build( Transition transition, Object condition, Object action, Object target_state )
  {
    return null;
  }

  // AUTOMATON

  @Override
  public void enter( Automaton automaton )
  {
    newline();
    print( automaton.name );
    print( "(" );
    print( automaton.initial_state.name );
    print( ")" );
    print( "{" );
  }

  public void exit( Automaton automaton )
  {
    print( "}" );
    newline();
  }

  @Override
  public Object build( Automaton automaton, Object initial_state, List< Object > modes )
  {
    return null;
  }

  // AST

  @Override
  public void enter( AST ast )
  {
    clear();
  }

  public void exit( AST ast )
  {
  }

  @Override
  public Object build( AST bot, List< Object > automata )
  {
    return null;
  }

  public static void main( String[] args ) throws Exception
  {
    AST     ast     = Parser.from_file( "resources/ninja.gal" );
    Visitor visitor = new Visitor();
    ast.accept( visitor );
    System.out.println( visitor.export() );
  }
}
