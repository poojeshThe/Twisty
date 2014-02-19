package com.fortune.restful;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;

public class CustomerView
  extends AutoCompleteTextView
{
  private boolean enough = false;
  private AdapterView.OnItemClickListener onCustomerSelect = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {}
  };
  private View.OnClickListener onCustomerViewClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ((CustomerView)paramAnonymousView).showDropDown();
    }
  };
  
  public CustomerView(Context paramContext)
  {
    super(paramContext);
    setOnClickListener(this.onCustomerViewClick);
    setOnItemClickListener(this.onCustomerSelect);
  }
  
  public CustomerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOnClickListener(this.onCustomerViewClick);
    setOnItemClickListener(this.onCustomerSelect);
  }
  
  public CustomerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setOnClickListener(this.onCustomerViewClick);
    setOnItemClickListener(this.onCustomerSelect);
  }
  
  public boolean enoughToFilter()
  {
    return this.enough;
  }
  
  public void setEnoughToFilter(boolean paramBoolean)
  {
    this.enough = paramBoolean;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.CustomerView
 * JD-Core Version:    0.7.0.1
 */