package src;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer
{
  public static < T > T deserialize( String filename, Class< T > clazz )
  {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try ( FileReader reader = new FileReader( filename ) )
    {
      return gson.fromJson( reader, clazz );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    return null;
  }
}