package com.fortune.restful;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class WinningReptAdapter
  extends ArrayAdapter<String>
{
  private Context appContext = null;
  private ArrayList<String> reportItems;
  
  public WinningReptAdapter(Context paramContext, int paramInt, ArrayList<String> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.reportItems = paramArrayList;
    this.appContext = paramContext;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if ((localView == null) && (this.appContext != null)) {
      localView = ((LayoutInflater)this.appContext.getSystemService("layout_inflater")).inflate(2130903053, null);
    }
    String str = (String)this.reportItems.get(paramInt);
    if (str != null)
    {
      TextView localTextView = (TextView)localView.findViewById(16908308);
      if (localTextView != null) {
        localTextView.setText(str);
      }
      if (!str.startsWith("Bill"))
      {
        if (!str.startsWith("**"))
        {
          localView.setBackgroundColor(-7829368);
          localTextView.setTextColor(-14671856);
          localTextView.setTextSize(14.0F);
        }
        else
        {
          localView.setBackgroundColor(-14647168);
          localTextView.setTextColor(-8380384);
          localTextView.setTextSize(14.0F);
        }
      }
      else
      {
        localView.setBackgroundColor(-6250320);
        localTextView.setTextColor(-16777216);
        localTextView.setTextSize(16.0F);
      }
    }
    return localView;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.WinningReptAdapter
 * JD-Core Version:    0.7.0.1
 */