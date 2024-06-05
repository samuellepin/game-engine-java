package src;

public class MainApp
{
  private static final int WIDTH  = 100;
  private static final int HEIGHT = 100;

  public static void main( String[] args )
  {
    System.out.println( "Snake" );

    Model model = new Model( WIDTH, HEIGHT );
    model.print();

    for ( int i = 0; i < 5; i++ )
    {
      model.tick( i );
      model.print();
    }
  }
}
