package src.AI;

import java.util.ArrayList;

import gal.ast.AST;
import gal.ast.export.Ast2Gal;
import gal.parser.Parser;

public class FsmCreator
{
  public static ArrayList< FSM > loadAutomata( String filename )
  {
    try
    {
      AST              ast      = (AST)Parser.from_file( filename );
      VisitorFsm       visitor  = new VisitorFsm();
      // Ast2Gal visitor = new Ast2Gal();
      @SuppressWarnings( "unchecked" )
      ArrayList< FSM > FSM_list = (ArrayList< FSM >)ast.accept( visitor );
      return FSM_list;
    }
    catch ( Exception ex )
    {
      throw new RuntimeException( ex );
      // return null;
    }
  }

  public static void main( String[] args )
  {
    loadAutomata( "src/AI/test-FSM/" + "fsm02.gal" );
  }

}
