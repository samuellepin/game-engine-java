package src.AI;

import gal.ast.AST;
import gal.ast.IVisitor;
import gal.parser.Parser;

public class TestMain
{
  public static Object loadAutomata(String filename) {
    Object fsm_list;
    try {
      AST ast = (AST) Parser.from_file(filename);
      TestVisitor visitor = new TestVisitor();
      Object FSM_list = ast.accept( visitor );
      return FSM_list;
    } catch (Exception ex) {
      return null;
    }
  }

    public static void main(String[] args) {
      loadAutomata("/home/samuel/Documents/cours-TP-TD/projet/g6/JavaProject/src/gal/demo/test/"
          + "a03.gal");
    }
}
