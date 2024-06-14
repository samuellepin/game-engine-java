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
      ArrayList<FSM> FSM_list =(ArrayList<FSM> ) ast.accept( visitor );
      return FSM_list;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      //return null;
    }
  }

    public static void main(String[] args) {
      loadAutomata("/home/samuel/Documents/cours-TP-TD/projet/g6/project.game/src/AI/test-FSM/"
          + "fsm01.gal");
    }
}
