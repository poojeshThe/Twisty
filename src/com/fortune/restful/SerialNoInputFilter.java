package com.fortune.restful;

import android.text.InputFilter;
import android.text.Spanned;

public class SerialNoInputFilter
  implements InputFilter
{
  private int length;
  
  public SerialNoInputFilter(int paramInt)
  {
    this.length = paramInt;
  }
  
  public SerialNoInputFilter(String paramString)
  {
    this.length = Integer.parseInt(paramString);
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    String str;
    if ((paramSpanned.toString() + paramCharSequence.toString()).length() == this.length) {
      str = "";
    } else {
      str = null;
    }
    return str;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.SerialNoInputFilter
 * JD-Core Version:    0.7.0.1
 */