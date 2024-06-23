package src.AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

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
import src.AI.CategoryFsm.CATEGORY;
import src.AI.Action.*;
import src.AI.Condition.*;

public class VisitorFsm implements IVisitor
{

  private FuncallFactory        m_funcallfact;
  private ArrayList< StateFsm > m_current_states;

  @Override
  public Object visit( Category cat )
  {
    System.out.println( "Category " + cat.toString() );
    switch ( cat.toString() )
    {
    case "A":
      return new CategoryFsm( CATEGORY.Adversary );
    case "C":
      return new CategoryFsm( CATEGORY.Clue );
    case "D":
      return new CategoryFsm( CATEGORY.Danger );
    case "G":
      return new CategoryFsm( CATEGORY.Gate );
    case "I":
      return new CategoryFsm( CATEGORY.Icon );
    case "J":
      return new CategoryFsm( CATEGORY.Jumpable );
    case "K":
      return new CategoryFsm( CATEGORY.Killable );
    case "M":
      return new CategoryFsm( CATEGORY.Moveable );
    case "O":
      return new CategoryFsm( CATEGORY.Obstacle );
    case "P":
      return new CategoryFsm( CATEGORY.Pickable );
    case "T":
      return new CategoryFsm( CATEGORY.Team );
    case "U":
      return new CategoryFsm( CATEGORY.Util );
    case "V":
      return new CategoryFsm( CATEGORY.Void );
    case "@":
      return new CategoryFsm( CATEGORY.PlayerT );
    case "#":
      return new CategoryFsm( CATEGORY.PlayerA );
    case "Power":
      return new CategoryFsm( CATEGORY.Power );
    case "Stuff":
      return new CategoryFsm( CATEGORY.Stuff );
    case "_":
      return new CategoryFsm( CATEGORY.Underscore );
    default:
      return null;
    }
  }

  @Override
  public Object visit( Direction dir )
  {
    System.out.println( "Direction " + dir.toString() );
    switch ( dir.toString() )
    {
    case "N":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.North );
    case "S":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.South );
    case "E":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.East );
    case "W":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.West );
    case "NE":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.NE );
    case "SE":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.SE );
    case "SW":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.SW );
    case "NW":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.NW );
    case "F":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Forward );
    case "B":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Backward );
    case "L":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Left );
    case "R":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Right );
    case "H":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Here );
    case "_":
      return new src.AI.Direction( src.AI.Direction.DIRECTION.Underscore );
    default:
      return null;
    }
  }

  @Override
  public Object visit( Key key )
  {
    System.out.println( "Key " + key.toString() );
    switch ( key.terminal.content )
    {
    case "a":
      return KEY.A;
    case "z":
      return KEY.Z;
    case "e":
      return KEY.E;
    case "r":
      return KEY.R;
    case "t":
      return KEY.T;
    case "y":
      return KEY.Y;
    case "u":
      return KEY.U;
    case "i":
      return KEY.I;
    case "o":
      return KEY.O;
    case "p":
      return KEY.P;
    case "q":
      return KEY.Q;
    case "s":
      return KEY.S;
    case "d":
      return KEY.D;
    case "f":
      return KEY.F;
    case "g":
      return KEY.G;
    case "h":
      return KEY.H;
    case "j":
      return KEY.J;
    case "k":
      return KEY.K;
    case "l":
      return KEY.L;
    case "m":
      return KEY.M;
    case "w":
      return KEY.W;
    case "x":
      return KEY.X;
    case "c":
      return KEY.C;
    case "v":
      return KEY.V;
    case "b":
      return KEY.B;
    case "n":
      return KEY.N;

    case "_":
      return KEY.UNDERSCORE;
    case "ENTER":
      return KEY.ENTER;
    case "SPACE":
      return KEY.SPACE;
    case "FU":
      return KEY.UP;
    case "FD":
      return KEY.DOWN;
    case "FL":
      return KEY.LEFT;
    case "FR":
      return KEY.RIGHT;

    case "RBP":
      return KEY.RBP;
    case "RBR":
      return KEY.RBR;
    case "LBP":
      return KEY.LBP;
    case "LBR":
      return KEY.LBR;
    case "MBP":
      return KEY.MBP;
    case "MBR":
      return KEY.MBR;
    case "WLU":
      return KEY.WLU;
    case "WLD":
      return KEY.WLD;

    default:
      return null;
    }
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Object visit( Value v )
  {
    System.out.println( "Value " + v.toString() );
    return new Integer( v.value );
  }

  @Override
  public Object visit( Underscore u )
  {
    System.out.println( "Underscore (= pour les destination aléatoire " );
    System.out.println( "Not Yet Implement" );
    throw new IllegalStateException( "should not enter the underscore visit" );
  }

  @Override
  public void enter( FunCall funcall )
  {
    System.out.println( "FunCall enter " + funcall.toString() );
  }

  @Override
  public void visit( FunCall funcall )
  {
    System.out.println( "FunCall visit " + funcall.toString() );
  }

  @Override
  public void exit( FunCall funcall )
  {
    System.out.println( "FunCall exit " + funcall.toString() );
  }

  @Override
  public Object build( FunCall funcall, List< Object > parameters )
  {
    System.out.println( "FunCall build " );
    return m_funcallfact.get( funcall.name, parameters );
  }

  @Override
  public void enter( BinaryOp binop )
  {
    System.out.println( "BinaryOP enter " + binop.toString() );
  }

  @Override
  public void visit( BinaryOp binop )
  {
    System.out.println( "BinaryOp visit " + binop.toString() );
  }

  @Override
  public void exit( BinaryOp binop )
  {
    System.out.println( "BinaryOp exit " + binop.toString() );
  }

  @Override
  public Object build( BinaryOp binop, Object left, Object right )
  {
    // System.out.println( "BinaryOp build " + binop.toString() + ":\n" +
    // left.toString() + "\n" + right.toString() );
//    System.out.println( "Build BinaryOp not yet implement" );

    switch ( binop.operator )
    {
    case "&":
      return new ConjunctionFsm( (ConditionFsm)left, (ConditionFsm)right );
    case "/":
      return new DisjunctionFsm( (ConditionFsm)left, (ConditionFsm)right );
    default:
      throw new IllegalStateException();
    }
  }

  @Override
  public void enter( UnaryOp unop )
  {
    System.out.println( "UnaryOp enter " + unop.toString() );
  }

  @Override
  public void exit( UnaryOp unop )
  {
    System.out.println( "UnaryOp exit " + unop.toString() );
  }

  @Override
  public Object build( UnaryOp unop, Object expression )
  {
    // System.out.println( "UnaryOp build " + unop.toString() + ":\n" +
    // expression.toString() );
    System.out.println( "Build UnaryOp" );
    switch ( unop.operator )
    {
    case "!":
      return new NegationFsm( (ConditionFsm)expression );
    default:
      throw new IllegalStateException();
    }
  }

  @Override
  public Object visit( State state )
  {
    System.out.println( "State " + state.toString() );

    Iterator< StateFsm > iter = m_current_states.iterator();
    while( iter.hasNext() )
    {
      StateFsm st = iter.next();
      if( st.getName().equals( state.name ) )
      {
        return st;
      }
    }
    StateFsm s = new StateFsm( state.name );
    m_current_states.add( s );
    return s;
  }

  @Override
  public void enter( Mode mode )
  {
    System.out.println( "Mode enter " );
  }

  @Override
  public void visit( Mode mode )
  {
    System.out.println( "Mode visit " );
  }

  @Override
  public void exit( Mode mode )
  {
    System.out.println( "Mode exit " );
  }

  @Override
  public Object build( Mode mode, Object source_state, Object behaviour )
  {
    System.out.println( "Mode build " );
    StateFsm             st              = (StateFsm)source_state;
    LinkedList< Object > transition_list = (LinkedList< Object >)behaviour;
    Iterator< Object >   iter            = transition_list.iterator();
    while( iter.hasNext() )
    {
      TransitionFsm trans = (TransitionFsm)iter.next();
      st.addTransition( trans );
    }
    return st;
  }

  @Override
  public Object visit( Behaviour behaviour, List< Object > transitions )
  {
    System.out.println( "Behaviour " );
    return transitions;
  }

  @Override
  public void enter( Condition condition )
  {
    System.out.println( "Condition enter " + condition.toString() );
  }

  @Override
  public void exit( Condition condition )
  {
    System.out.println( "Condition exit" + condition.toString() );
  }

  @Override
  public Object build( Condition condition, Object expression )
  {
    System.out.println( "Condition build " );
    return expression;
  }

  @Override
  public void enter( Actions action )
  {
    System.out.println( "Actions enter " + action.toString() );
  }

  @Override
  public void visit( Actions action )
  {
    System.out.println( "Actions visit " + action.toString() );
  }

  @Override
  public void exit( Actions action )
  {
    System.out.println( "Actions exit " + action.toString() );
  }

  @Override
  public Object build( Actions action, String operator, List< Object > funcalls )
  {
    System.out.println( "Actions build " );
    switch ( action.operator )
    {
    case "/":
      // Find percentage for action with no percentage
      Iterator< FunCall > iter = action.actions.iterator();
      int nb_percent = 100;
      int nb_act_nopercent = 0;
      while( iter.hasNext() )
      {
        FunCall fc = iter.next();
        if( fc.percent <= 0 )
        {
          nb_act_nopercent++ ;
        }
        else
        {
          nb_percent = nb_percent - fc.percent;
        }
      }
      int percent_per_actNI = 0;
      if( nb_act_nopercent != 0 )
      {
        percent_per_actNI = nb_percent / nb_act_nopercent;
      }
      if( nb_percent < 0 )
      {
        throw new IllegalStateException( "too much perccent ( more than 100%)" );
      }
      // Creating the Actions with percentage
      ActionPercentage actpc = new ActionPercentage();
      iter = action.actions.iterator();
      Iterator< Object > iteract = funcalls.iterator();
      while( iteract.hasNext() )
      {
        ActionFsm act = (ActionFsm)iteract.next();
        FunCall   fc  = iter.next();
        if( fc.percent <= 0 )
        {
          actpc.add( act, percent_per_actNI );
        }
        else
        {
          actpc.add( act, fc.percent );
        }
      }
      return actpc;
    case ";":
      ActionMultiple actm = new ActionMultiple();
      iteract = funcalls.iterator();
      while( iteract.hasNext() )
      {
        ActionFsm act = (ActionFsm)iteract.next();
        actm.addAction( act );
      }
      return actm;
    }
    return null;
  }

  @Override
  public void enter( Transition transition )
  {
    System.out.println( "Transition enter " + transition.toString() );
  }

  @Override
  public void exit( Transition transition )
  {
    System.out.println( "Transition exit " + transition.toString() );
  }

  @Override
  public Object build( Transition transition, Object condition, Object action, Object target_state )
  {
    System.out.println( "Transition build " );
    return new TransitionFsm( (StateFsm)target_state, (ConditionFsm)condition, (ActionFsm)action );
  }

  @Override
  public void enter( Automaton automaton )
  {
    System.out.println( "\n" );
    System.out.println( "-------------" + automaton.toString() + "-------------" );
    System.out.println( "Automaton enter " + automaton.toString() );
    m_current_states = new ArrayList< StateFsm >();
  }

  @Override
  public void exit( Automaton automaton )
  {
    System.out.println( "Automaton exit " + automaton.toString() );
  }

  @Override
  public Object build( Automaton automaton, Object initial_state, List< Object > modes )
  {
    System.out.println( "Automaton build " );
    return new FSM( automaton.name, m_current_states, (StateFsm)initial_state );
  }

  @Override
  public void enter( AST ast )
  {
    System.out.println( "AST enter " );
    m_funcallfact = new FuncallFactory();
  }

  @Override
  public void exit( AST ast )
  {
    System.out.println( "AST exit " );
  }

  @Override
  public Object build( AST ast, List< Object > automata )
  {
    System.out.println( "AST build " );
    Iterator< Object > iterator = automata.iterator();
    ArrayList< FSM >   Fsm_list = new ArrayList< FSM >();
    while( iterator.hasNext() )
    {
      FSM fsm = (FSM)iterator.next();
      Fsm_list.add( fsm );
    }
    return Fsm_list;
  }

}
