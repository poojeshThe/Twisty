package com.fortune.restful;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.fortune.restful.activities.EditSalesActivity;

public class SaleEditAdapter
  extends ArrayAdapter<SaleItem>
  implements CompoundButton.OnCheckedChangeListener
{
  private final int DIALOG_DISPLAY_STRING_WITH_OK = 0;
  private final int DIALOG_OPTION_NOT_AVAILABLE = 1;
  private Activity activity = null;
  private Context appContext = null;
  private boolean dirty = false;
  private View.OnClickListener saleItemClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (EditSalesActivity.class.isInstance(SaleEditAdapter.this.activity))
      {
        Object localObject = LayoutInflater.from(SaleEditAdapter.this.activity).inflate(2130903047, null);
        final TextView localTextView1 = (TextView)((View)localObject).findViewById(2131165256);
        final TextView localTextView2 = (TextView)((View)localObject).findViewById(2131165257);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(SaleEditAdapter.this.activity);
        localBuilder.setTitle("Enter Serial No & Count");
        localBuilder.setView((View)localObject);
        localObject = (SaleItem)paramAnonymousView.getTag();
        localTextView1.setText(((SaleItem)localObject).getSerialNo());
        localTextView2.setText(Integer.toString(((SaleItem)localObject).getCount()));
        localTextView1.setSelectAllOnFocus(true);
        localTextView2.setSelectAllOnFocus(true);
        new android.text.InputFilter[1][0] = new InputFilter.LengthFilter(((SaleItem)localObject).getLottLength());
        localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if ((localTextView1 != null) && (localTextView2 != null) && (this.val$item != null))
            {
              String str = localTextView1.getText().toString();
              Object localObject = localTextView2.getText().toString();
              if (str.length() != this.val$item.getLottLength())
              {
                if (SaleEditAdapter.this.activity != null)
                {
                  paramAnonymous2DialogInterface.cancel();
                  localObject = new Bundle();
                  ((Bundle)localObject).putString("dialog-message", "Enter " + this.val$item.getLottLength() + " digits for serial number");
                  SaleEditAdapter.this.activity.showDialog(0, (Bundle)localObject);
                }
              }
              else
              {
                this.val$item.setSerialNo(Integer.parseInt(str));
                this.val$item.setCount(Integer.parseInt((String)localObject));
                SaleEditAdapter.this.notifyDataSetChanged();
                SaleEditAdapter.this.dirty = true;
              }
            }
          }
        });
        localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        localBuilder.create();
        localBuilder.show();
      }
    }
  };
  private SaleArrayList saleItems;
  
  public SaleEditAdapter(Context paramContext, int paramInt, SaleArrayList paramSaleArrayList, Activity paramActivity)
  {
    super(paramContext, paramInt, paramSaleArrayList);
    this.saleItems = paramSaleArrayList;
    this.appContext = paramContext;
    this.activity = paramActivity;
    this.dirty = false;
  }
  
  public void clear()
  {
    this.dirty = false;
    super.clear();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if ((localView == null) && (this.appContext != null)) {
      localView = ((LayoutInflater)this.appContext.getSystemService("layout_inflater")).inflate(2130903048, null);
    }
    SaleItem localSaleItem = (SaleItem)this.saleItems.get(paramInt);
    if (localSaleItem != null)
    {
      TextView localTextView1 = (TextView)localView.findViewById(2131165258);
      Object localObject2 = (TextView)localView.findViewById(2131165259);
      TextView localTextView2 = (TextView)localView.findViewById(2131165260);
      Object localObject1 = (TextView)localView.findViewById(2131165261);
      if ((localTextView1 != null) && (localObject2 != null) && (localTextView2 != null) && (localObject1 != null))
      {
        localTextView1.setText(localSaleItem.getLottName());
        ((TextView)localObject2).setText(localSaleItem.getSerialNo());
        localTextView2.setText(Integer.toString(localSaleItem.getCount()));
        localObject2 = new Object[1];
        localObject2[0] = localSaleItem.getAmount();
        ((TextView)localObject1).setText(String.format("%.2f", (Object[])localObject2));
      }
      localView.setBackgroundColor(-15695760);
      localView.setFocusable(true);
      localView.setFocusableInTouchMode(true);
      localView.setClickable(true);
      localView.setOnClickListener(this.saleItemClickListener);
      localView.setTag(localSaleItem);
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
  
  public boolean isDirty()
  {
    return this.dirty;
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
      setDirty(true);
    }
  }
  
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    Object localObject = new AlertDialog.Builder(this.activity);
    switch (paramInt)
    {
    default: 
      localObject = null;
      break;
    case 0: 
      ((AlertDialog.Builder)localObject).setMessage(paramBundle.getString("dialog-message")).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      break;
    case 1: 
      ((AlertDialog.Builder)localObject).setMessage("This option is not available in this version.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
    }
    if (localObject == null) {
      localObject = null;
    } else {
      localObject = ((AlertDialog.Builder)localObject).create();
    }
    return localObject;
  }
  
  public void setDirty(boolean paramBoolean)
  {
    this.dirty = paramBoolean;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.SaleEditAdapter
 * JD-Core Version:    0.7.0.1
 */