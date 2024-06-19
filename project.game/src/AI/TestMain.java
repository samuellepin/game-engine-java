package src.AI;

import java.util.ArrayList;

import gal.ast.AST;
import gal.ast.export.Ast2Gal;
import gal.parser.Parser;

public class TestMain
{
  public static ArrayList<FSM>  loadAutomata(String filename) {
    try {
      AST ast = (AST) Parser.from_file(filename);
      TestVisitor visitor = new TestVisitor();
      //Ast2Gal visitor = new Ast2Gal();
      @SuppressWarnings( "unchecked" )
      ArrayList<FSM> FSM_list =(ArrayList<FSM> ) ast.accept( visitor );
      return FSM_list;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      //return null;
    }
  }

    public static void main(String[] args) {
      loadAutomata("src/AI/test-FSM/"
          + "fsm01.gal");
    }
}
