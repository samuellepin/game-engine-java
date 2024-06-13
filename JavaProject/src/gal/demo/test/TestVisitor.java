package gal.demo.test;

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

  @Override
  public Object visit( Category cat )
  {
    System.out.println( "Category" + cat.toString());
    return null;
  }

  @Override
  public Object visit( Direction dir )
  {
    System.out.println( "Direction" + dir.toString() );
    return null;
  }

  @Override
  public Object visit( Key key )
  {
    System.out.println( "Key" + key.toString()) ;
    return null;
  }

  @Override
  public Object visit( Value v )
  {
    System.out.println( "Value" + v.toString());
    return null;
  }

  @Override
  public Object visit( Underscore u )
  {
    System.out.println( "Underscore" + u.toString());
    return null;
  }

  @Override
  public void enter( FunCall funcall )
  {
    System.out.println( "FunCall enter" + funcall.toString() );

  }

  @Override
  public void visit( FunCall funcall )
  {
    System.out.println( "FunCall visit" + funcall.toString());

  }

  @Override
  public void exit( FunCall funcall )
  {
    System.out.println( "FunCall exit" + funcall.toString());

  }

  @Override
  public Object build( FunCall funcall, List< Object > parameters )
  {
    System.out.println( "FunCall build"  + funcall.toString() + ":\n");
    for ( Object o : parameters )
    {
      System.out.println( o.toString() );
    }
    System.out.println("\n");
    return null;
  }

  @Override
  public void enter( BinaryOp binop )
  {
    System.out.println( "BinaryOP enter" + binop.toString());
  }

  @Override
  public void visit( BinaryOp binop )
  {
    System.out.println( "BinaryOp visit" + binop.toString());
  }

  @Override
  public void exit( BinaryOp binop )
  {
    System.out.println( "BinaryOp exit" + binop.toString());
  }

  @Override
  public Object build( BinaryOp binop, Object left, Object right )
  {
    System.out.println( "BinaryOp build" + binop.toString() + ":\n" + left.toString() + "\n" + right.toString() );
    return null;
  }

  @Override
  public void enter( UnaryOp unop )
  {
    System.out.println( "UnaryOp enter" + unop.toString());
  }

  @Override
  public void exit( UnaryOp unop )
  {
    System.out.println( "UnaryOp exit" + unop.toString());
  }

  @Override
  public Object build( UnaryOp unop, Object expression )
  {
    System.out.println( "UnaryOp build" + unop.toString() + ":\n" + expression.toString());
    return null;
  }

  @Override
  public Object visit( State state )
  {
    System.out.println( "State" + state.toString());
    return null;
  }

  @Override
  public void enter( Mode mode )
  {
    System.out.println( "Mode enter" + mode.toString());
  }

  @Override
  public void visit( Mode mode )
  {
    System.out.println( "Mode visit" + mode.toString());
  }

  @Override
  public void exit( Mode mode )
  {
    System.out.println( "Mode exit" + mode.toString());
  }

  @Override
  public Object build( Mode mode, Object source_state, Object behaviour )
  {
    System.out.println( "Mode build" + mode.toString() + ":\n" + source_state.toString() + "\n" + behaviour.toString());
    return null;
  }

  @Override
  public Object visit( Behaviour behaviour, List< Object > transitions )
  {
    System.out.println( "Behaviour" + behaviour.toString() + ":\n");
    for ( Object o : transitions )
    {
      System.out.println( o.toString() );
    }
    return null;
  }

  @Override
  public void enter( Condition condition )
  {
    System.out.println( "Condition enter" + condition.toString() );
  }

  @Override
  public void exit( Condition condition )
  {
    System.out.println( "Condition exit" + condition.toString());
  }

  @Override
  public Object build( Condition condition, Object expression )
  {
    System.out.println( "Condition build" + condition.toString() + ":\n" + expression.toString());
    return null;
  }

  @Override
  public void enter( Actions action )
  {
    System.out.println( "Actions enter" + action.toString());
  }

  @Override
  public void visit( Actions action )
  {
    System.out.println( "Actions visit" + action.toString());
  }

  @Override
  public void exit( Actions action )
  {
    System.out.println( "Actions exit" + action.toString());
  }

  @Override
  public Object build( Actions action, String operator, List< Object > funcalls )
  {
    System.out.println( "Actions build =" + action.toString() + "\noperator =" + operator + "\n");
    for ( Object o : funcalls )
    {
      System.out.println( o.toString() );
    }
    System.out.println("\n");
    return null;
  }

  @Override
  public void enter( Transition transition )
  {
    System.out.println( "Transition enter" + transition.toString() );
  }

  @Override
  public void exit( Transition transition )
  {
    System.out.println( "Transition exit" + transition.toString());
  }

  @Override
  public Object build( Transition transition, Object condition, Object action, Object target_state )
  {
    System.out.println( "Transition build" + transition.toString() + ":\n" + condition.toString() + "\n" + action.toString() + "\n" + target_state.toString());
    return null;
  }

  @Override
  public void enter( Automaton automaton )
  {
    System.out.println( "Automaton enter" + automaton.toString());
  }

  @Override
  public void exit( Automaton automaton )
  {
    System.out.println( "Automaton exit" + automaton.toString());
  }

  @Override
  public Object build( Automaton automaton, Object initial_state, List< Object > modes )
  {
    System.out.println( "Automaton build" + automaton.toString() + ":\n" + initial_state.toString() + "\n");
    for ( Object o : modes )
    {
      System.out.println( o.toString() );
    }
    System.out.println("\n");
    return null;
  }

  @Override
  public void enter( AST ast )
  {
    System.out.println( "AST enter" + ast.toString());
  }

  @Override
  public void exit( AST ast )
  {
    System.out.println( "AST exit" + ast.toString());
  }

  @Override
  public Object build( AST ast, List< Object > automata )
  {
    System.out.println( "AST build"  + ast.toString() + ":\n");
    for ( Object o : automata )
    {
      System.out.println( o.toString() );
    }
    return null;
  }

}
