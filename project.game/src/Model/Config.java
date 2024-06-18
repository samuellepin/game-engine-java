package src.Model;

import java.util.Random;

public class Config
{
  public static final long SEED = 123;
  public static final Random RANDOM=new Random(SEED);
  public static final double VISION_FIELD_RADIUS = 100;
  public static final double  RATIO = 1; // 1m -> 1px
  
  public static Random getRandom()
  {
    return new Random( SEED );
  }
}
