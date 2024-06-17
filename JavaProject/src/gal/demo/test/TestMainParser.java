package gal.demo.test;

import gal.ast.AST;
import gal.parser.Parser;

public class TestMainParser
{
    public static Object  loadAutomata(String filename) {
      try {
        AST ast = (AST) Parser.from_file(filename);
        TestVisitorParser visitor = new TestVisitorParser();
        //Ast2Gal visitor = new Ast2Gal();
        Object FSM_list =  ast.accept( visitor );
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
