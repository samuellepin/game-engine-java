package src.AI;

import java.util.ArrayList;
import java.util.Iterator;
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

public class TestVisitor implements IVisitor
{

 
  /*
   * Indicates if the initial state has been set : true if the initial state has
   * been set, false otherwise
   */
  private boolean m_init;
  private FSM     m_fsm;
  private int m_id_state;

  @Override
  public Object visit( Category cat )
  {
    System.out.println( "Category " + cat.toString() );
    return null;
  }

  @Override
  public Object visit( Direction dir )
  {
    System.out.println( "Direction " + dir.toString() );
    return null;
  }

  @Override
  public Object visit( Key key )
  {
    System.out.println( "Key " + key.toString() );
    return null;
  }

  @Override
  public Object visit( Value v )
  {
    System.out.println( "Value " + v.toString() );
    return null;
  }

  @Override
  public Object visit( Underscore u )
  {
    System.out.println( "Underscore " + u.toString() );
    return null;
  }

  @Override
  public void enter( FunCall funcall )
  {
    //System.out.println( "FunCall enter " + funcall.toString() );
  }

  @Override
  public void visit( FunCall funcall )
  {
    //System.out.println( "FunCall visit " + funcall.toString() );
  }

  @Override
  public void exit( FunCall funcall )
  {
    //System.out.println( "FunCall exit " + funcall.toString() );
  }

  @Override
  public Object build( FunCall funcall, List< Object > parameters )
  {
    System.out.println( "FunCall build " );
    switch ( funcall.name )
    {
    // Condition:
    case "Cell":
      // Cell o = new Cell(parameters);
      break;
    case "True":
      // True o = new True();
      break;
    case "MyDir":
      // MyDir o = new MyDir(parameters);
      break;
    case "Closest":
      // Closest o = new Closest(parameters);
      break;
    case "Got":
      // Got o = new Got(parameters);
      break;
    case "Key":
      // Key o = new Key(parameters);
      break;
    // Action :
    case "Add":
      // Add o = new Add(parameters);
      break;
    case "Egg":
      // Egg o = new Egg(parameters);
      break;
    case "Explode":
      // Explode o = new Explode(parameters);
      break;
    case "Get":
      // Get o = new Get(parameters);
      break;
    case "Hit":
      // Hit o = new Hit(parameters);
      break;
    case "Jump":
      // Jump o = new Jump(parameters);
      break;
    case "Move":
      // Move o = new Move(parameters);
      break;
    case "Pick":
      // Pick o = new Pick(parameters);
      break;
    case "Protect":
      // Protect o = new Protect(parameters);
      break;
    case "Rest":
      // Rest o = new Rest(parameters);
      break;
    case "Store":
      // Store o = new Store(parameters);
      break;
    case "Throw":
      // Throw o = new Throw(parameters);
      break;
    case "Turn":
      // Turn o = new Turn(parameters);
      break;
    case "Wait":
      // Wait o = new Wait(parameters);
      break;
    case "Pop":
      // Pop o = new Pop(parameters);
      break;
    case "Wizz":
      // Wizz o = new Wizz(parameters);
      break;
    }
    // Object obj = (Object) o;
    // return obj;
    return null;
  }

  @Override
  public void enter( BinaryOp binop )
  {
    //System.out.println( "BinaryOP enter " + binop.toString() );
  }

  @Override
  public void visit( BinaryOp binop )
  {
    //System.out.println( "BinaryOp visit " + binop.toString() );
  }

  @Override
  public void exit( BinaryOp binop )
  {
    //System.out.println( "BinaryOp exit " + binop.toString() );
  }

  @Override
  public Object build( BinaryOp binop, Object left, Object right )
  {
    //System.out.println( "BinaryOp build " + binop.toString() + ":\n" + left.toString() + "\n" + right.toString() );
    return null;
  }

  @Override
  public void enter( UnaryOp unop )
  {
    //System.out.println( "UnaryOp enter " + unop.toString() );
  }

  @Override
  public void exit( UnaryOp unop )
  {
    //System.out.println( "UnaryOp exit " + unop.toString() );
  }

  @Override
  public Object build( UnaryOp unop, Object expression )
  {
    System.out.println( "UnaryOp build " + unop.toString() + ":\n" + expression.toString() );
    return null;
  }

  @Override
  public Object visit( State state )
  {
    System.out.println( "State " + state.toString() );
    
    StateFsm fsm_state = new StateFsm(m_id_state ,state.name);
    if(m_fsm.containState(fsm_state)){
      if(!m_init) {
        m_fsm.setInitState( fsm_state);
        m_id_state++;
      }else {
        m_fsm.addState(fsm_state);
        m_id_state++;
      }
    }

    return null;
  }

  @Override
  public void enter( Mode mode )
  {
    //System.out.println( "Mode enter " );
  }

  @Override
  public void visit( Mode mode )
  {
    //System.out.println( "Mode visit " );
  }

  @Override
  public void exit( Mode mode )
  {
    //System.out.println( "Mode exit " );
  }

  @Override
  public Object build( Mode mode, Object source_state, Object behaviour )
  {
    System.out.println( "Mode build " );
    return null;
  }

  @Override
  public Object visit( Behaviour behaviour, List< Object > transitions )
  {
    System.out.println( "Behaviour " );
    return null;
  }

  @Override
  public void enter( Condition condition )
  {
    //System.out.println( "Condition enter " + condition.toString() );
  }

  @Override
  public void exit( Condition condition )
  {
    //System.out.println( "Condition exit" + condition.toString() );
  }

  @Override
  public Object build( Condition condition, Object expression )
  {
    System.out.println( "Condition build " );
    return null;
  }

  @Override
  public void enter( Actions action )
  {
    //System.out.println( "Actions enter " + action.toString() );
  }

  @Override
  public void visit( Actions action )
  {
    //System.out.println( "Actions visit " + action.toString() );
  }

  @Override
  public void exit( Actions action )
  {
    //System.out.println( "Actions exit " + action.toString() );
  }

  @Override
  public Object build( Actions action, String operator, List< Object > funcalls )
  {
    System.out.println( "Actions build " );
    return null;
  }

  @Override
  public void enter( Transition transition )
  {
    //System.out.println( "Transition enter " + transition.toString() );
  }

  @Override
  public void exit( Transition transition )
  {
    //System.out.println( "Transition exit " + transition.toString() );
  }

  @Override
  public Object build( Transition transition, Object condition, Object action, Object target_state )
  {
    System.out.println( "Transition build " );
    return new TransitionFsm((StateFsm)target_state,(ConditionFsm)condition,(ActionFsm)action);
  }

  @Override
  public void enter( Automaton automaton )
  {
    System.out.println( "\n" );
    System.out.println( "-------------" + automaton.toString() + "-------------" );
    System.out.println( "Automaton enter " + automaton.toString() );

    m_init = false;
    m_fsm = new FSM(automaton.name);
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
    return null;
  }

  @Override
  public void enter( AST ast )
  {
    System.out.println( "AST enter " );
    m_fsm = null;
    m_init = false;
    m_id_state =0;
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
    Iterator iterator = automata.iterator();
    ArrayList<FSM> Fsm_list = new ArrayList<FSM>();
    while(iterator.hasNext()) {
      FSM fsm = (FSM)iterator.next();
      Fsm_list.add(fsm);
    }
    return Fsm_list;
  }

}
