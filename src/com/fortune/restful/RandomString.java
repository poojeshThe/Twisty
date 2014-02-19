package com.fortune.restful;

import java.util.Random;

public class RandomString
{
  private static final char[] symbols = new char[17];
  private final char[] buf;
  private final Random random = new Random();
  
  static
  {
    for (int i = 0; i < 10; i++) {
      symbols[i] = ((char)(i + 48));
    }
    for (i = 10; i < 17; i++) {
      symbols[i] = ((char)(-10 + (i + 97)));
    }
  }
  
  public RandomString(int paramInt)
  {
    if (paramInt >= 1)
    {
      this.buf = new char[paramInt];
      return;
    }
    throw new IllegalArgumentException("length < 1: " + paramInt);
  }
  
  public String nextString()
  {
    for (int i = 0; i < this.buf.length; i++) {
      this.buf[i] = symbols[this.random.nextInt(symbols.length)];
    }
    return new String(this.buf);
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.RandomString
 * JD-Core Version:    0.7.0.1
 */