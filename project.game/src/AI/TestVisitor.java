package src.AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
import src.AI.Action.*;
import src.AI.Condition.*;

public class TestVisitor implements IVisitor
{

  
  private ArrayList<StateFsm> m_current_states;
  
  
  @Override
  public Object visit( Category cat )
  {
    System.out.println( "Category " + cat.toString() );
    switch ( cat.toString() )
    {
    case "A":
      return CATEGORY.Adversary;
    case "C":
      return CATEGORY.Clue;
    case "D":
      return CATEGORY.Danger;
    case "G":
      return CATEGORY.Gate;
    case "I":
      return CATEGORY.Icon;
    case "J":
      return CATEGORY.Jumpable;
    case "K":
      return CATEGORY.Killable;
    case "M":
      return CATEGORY.Moveable;
    case "O":
      return CATEGORY.Obstacle;
    case "P":
      return CATEGORY.Pickable;
    case "T":
      return CATEGORY.Team;
    case "U":
      return CATEGORY.Util;
    case "V":
      return CATEGORY.Void;
    case "@":
      return CATEGORY.PlayerT;
    case "#":
      return CATEGORY.PlayerA;
    case "Power":
      return CATEGORY.Power;
    case "Stuff":
      return CATEGORY.Stuff;
    case "_":
      return CATEGORY.Underscore;
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
      return DIRECTION.North;
    case "S":
      return DIRECTION.South;
    case "E":
      return DIRECTION.East;
    case "W":
      return DIRECTION.West;
    case "NE":
      return DIRECTION.NE;
    case "SE":
      return DIRECTION.SE;
    case "SW":
      return DIRECTION.SW;
    case "NW":
      return DIRECTION.NW;
    case "F":
      return DIRECTION.Forward;
    case "B":
      return DIRECTION.BackWard;
    case "L":
      return DIRECTION.Left;
    case "R":
      return DIRECTION.Right;
    case "H":
      return DIRECTION.Here;
    case "_":
      return DIRECTION.Underscore;
    default:
      return null;
    }
  }

  @Override
  public Object visit( Key key )
  {
    System.out.println( "Key " + key.toString() );
    return new KeyFsm( key.toString() );
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Object visit( Value v )
  {
    System.out.println( "Value " + v.toString() );
    return new Integer(v.value);
  }

  @Override
  public Object visit( Underscore u )
  {
    System.out.println( "Underscore (= pour les destination aléatoire " );
    System.out.println( "Not Yet Implement" );
    throw new IllegalStateException("should not enter the underscore visit");
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
    return funcall;
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
    switch(unop.operator) {
    case "!":
      return new NegationFsm((ConditionFsm)expression);
    default:
      throw new IllegalStateException();
    }
  }

  @Override
  public Object visit( State state )
  {
    System.out.println( "State " + state.toString() );
    
    Iterator<StateFsm> iter = m_current_states.iterator();
    while(iter.hasNext()) {
      StateFsm st = iter.next();
      if(st.getName().equals( state.name )) {
        return st;
      }
    }
    return new StateFsm( state.name );
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
    StateFsm st =(StateFsm) source_state;
    LinkedList<Object> transition_list = (LinkedList<Object>) behaviour;
    Iterator< Object > iter = transition_list.iterator();
    while(iter.hasNext()) {
      TransitionFsm trans = (TransitionFsm) iter.next();
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
    // TODO : build condition
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
    // TO DO : build action
    System.out.println( "Actions build " );
    switch(action.operator) {
    case "/":
      // Find percentage for action with no percentage
      Iterator<FunCall> iter = action.actions.iterator();
      int nb_percent = 100;
      int nb_act_nopercent = 0;
      while(iter.hasNext()) {
        FunCall fc = iter.next();
        if(fc.percent <= 0) {
          nb_act_nopercent ++;
        }else {
          nb_percent = nb_percent - fc.percent;
        }
      }
      int percent_per_actNI =0;
      if(nb_act_nopercent != 0) {
        percent_per_actNI = nb_percent /nb_act_nopercent;
      }
      if(nb_percent < 0) {
        throw new IllegalStateException("too much perccent ( more than 100%)");
      }
      // Creating the Actions with percentage
      ActionPercentage actpc = new ActionPercentage();
      iter = action.actions.iterator();
      Iterator< Object > iteract = funcalls.iterator();
      while(iteract.hasNext()) {
        ActionFsm act = (ActionFsm) iteract.next();
        FunCall fc = iter.next();
        if(fc.percent <= 0) {
          actpc.add( act, percent_per_actNI );
        }else {
          actpc.add( act, fc.percent );
        }
      }
      return actpc;
    case ";":
      ActionMultiple actm = new ActionMultiple();      
      iteract = funcalls.iterator();
      while(iteract.hasNext()) {
        ActionFsm act = (ActionFsm) iteract.next();
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
    // TO DO : build transition , insert transition in the fsm
    System.out.println( "Transition build " );
    return new TransitionFsm( (StateFsm)target_state, (ConditionFsm)condition, (ActionFsm)action );
  }

  @Override
  public void enter( Automaton automaton )
  {
    System.out.println( "\n" );
    System.out.println( "-------------" + automaton.toString() + "-------------" );
    System.out.println( "Automaton enter " + automaton.toString() );
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
    /*
    FSM fsm = new FSM(automaton.name);
    fsm.setInitState( (StateFsm) initial_state);
    Iterator<Object> iter = modes.iterator();
    while(iter.hasNext()) {
      StateFsm st = (StateFsm)iter.next();
      fsm.addState( st );
    }
    return fsm;
    */
    return new FSM(automaton.name, m_current_states);
  }

  @Override
  public void enter( AST ast )
  {
    System.out.println( "AST enter " );
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
