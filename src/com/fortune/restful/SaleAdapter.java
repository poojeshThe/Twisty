package com.fortune.restful;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.fortune.restful.activities.BuyActivity;

public class SaleAdapter
  extends ArrayAdapter<SaleItem>
  implements CompoundButton.OnCheckedChangeListener
{
  private Activity activity = null;
  private Context appContext = null;
  private SaleArrayList saleItems;
  
  public SaleAdapter(Context paramContext, int paramInt, SaleArrayList paramSaleArrayList, Activity paramActivity)
  {
    super(paramContext, paramInt, paramSaleArrayList);
    this.saleItems = paramSaleArrayList;
    this.appContext = paramContext;
    this.activity = paramActivity;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if ((localView == null) && (this.appContext != null)) {
      localView = ((LayoutInflater)this.appContext.getSystemService("layout_inflater")).inflate(2130903049, null);
    }
    SaleItem localSaleItem = (SaleItem)this.saleItems.get(paramInt);
    if (localSaleItem != null)
    {
      TextView localTextView2 = (TextView)localView.findViewById(2131165258);
      Object localObject2 = (TextView)localView.findViewById(2131165259);
      TextView localTextView1 = (TextView)localView.findViewById(2131165260);
      Object localObject1 = (TextView)localView.findViewById(2131165261);
      if ((localTextView2 != null) && (localObject2 != null) && (localTextView1 != null))
      {
        localTextView2.setText(localSaleItem.getLottName());
        ((TextView)localObject2).setText(localSaleItem.getSerialNo());
        localTextView1.setText(Integer.toString(localSaleItem.getCount()));
        localObject2 = new Object[1];
        localObject2[0] = localSaleItem.getAmount();
        ((TextView)localObject1).setText(String.format("%.2f", (Object[])localObject2));
      }
      localObject1 = (CheckBox)localView.findViewById(2131165262);
      ((CheckBox)localObject1).setTag(localSaleItem);
      ((CheckBox)localObject1).setOnCheckedChangeListener(this);
      if (!localSaleItem.isDisabled())
      {
        ((CheckBox)localObject1).setChecked(true);
        localView.setBackgroundColor(-15695760);
      }
      else
      {
        ((CheckBox)localObject1).setChecked(false);
        localView.setBackgroundColor(-3203040);
      }
    }
    return localView;
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    SaleItem localSaleItem = (SaleItem)paramCompoundButton.getTag();
    if (paramBoolean)
    {
      this.saleItems.enable(localSaleItem);
      ((View)paramCompoundButton.getParent()).setBackgroundColor(-15695760);
    }
    else
    {
      this.saleItems.disable(localSaleItem);
      ((View)paramCompoundButton.getParent()).setBackgroundColor(-3203040);
    }
    updateUI(paramCompoundButton);
  }
  
  public void updateUI(View paramView)
  {
    if ((this.activity != null) && (BuyActivity.class.isInstance(this.activity))) {
      ((BuyActivity)this.activity).updateSaleInfoDisplay();
    }
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.SaleAdapter
 * JD-Core Version:    0.7.0.1
 */