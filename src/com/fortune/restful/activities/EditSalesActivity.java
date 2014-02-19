package com.fortune.restful.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.Lottery;
import com.fortune.restful.SaleArrayList;
import com.fortune.restful.SaleEditAdapter;
import com.fortune.restful.SaleItem;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditSalesActivity
  extends Activity
{
  private final int DIALOG_CANNOT_DELETE_DIRTY = 2;
  private final int DIALOG_CANNOT_EDIT_BILL = 5;
  private final int DIALOG_CAN_SAVE_ONLY_DIRTY = 3;
  private final int DIALOG_CONFIRM_DELETE = 4;
  private final int DIALOG_DISPLAY_STRING_WITH_OK = 0;
  private final int DIALOG_OPTION_NOT_AVAILABLE = 1;
  private final View.OnClickListener deleteBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!EditSalesActivity.this.saleItemsAdapter.isDirty()) {
        EditSalesActivity.this.showDialog(4);
      } else {
        EditSalesActivity.this.showDialog(2);
      }
    }
  };
  private EditSalesActivity editSalesActivity;
  private final BroadcastReceiver editSalesReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Object localObject1 = paramAnonymousIntent.getExtras();
      Object localObject2 = ((Bundle)localObject1).getString("com.fortune.restful.response_message");
      int i = ((Bundle)localObject1).getInt("com.fortune.restful.resp_type");
      boolean bool = ((Bundle)localObject1).getBoolean("com.fortune.restful.success");
      localObject1 = (TextView)EditSalesActivity.this.findViewById(2131165227);
      if (4 == i)
      {
        EditSalesActivity.this.dismissProgressDialog();
        EditSalesActivity.this.saleItems.clear();
        EditSalesActivity.this.saleItemsAdapter.clear();
        EditSalesActivity.this.saleItemsAdapter.notifyDataSetChanged();
        EditSalesActivity.this.clearSaleInfo();
        ((TextView)localObject1).setText("");
        if (!bool) {
          EditSalesActivity.this.showDialog(5);
        }
      }
      do
      {
        for (;;)
        {
          return;
          if (3 != i) {
            break;
          }
          EditSalesActivity.this.dismissProgressDialog();
          EditSalesActivity.this.saleItems.clear();
          EditSalesActivity.this.saleItemsAdapter.clear();
          EditSalesActivity.this.saleItemsAdapter.notifyDataSetChanged();
          EditSalesActivity.this.clearSaleInfo();
          ((TextView)localObject1).setText("");
          if (!bool) {
            EditSalesActivity.this.showDialog(5);
          }
        }
      } while (11 != i);
      EditSalesActivity.this.dismissProgressDialog();
      for (;;)
      {
        String str;
        Lottery localLottery;
        try
        {
          localObject2 = new JSONObject((String)localObject2);
          JSONArray localJSONArray = ((JSONObject)localObject2).getJSONArray("sale-info");
          EditSalesActivity.this.saveSaleInfo(((JSONObject)localObject2).getInt("customer"), ((JSONObject)localObject2).getInt("login-type"), ((JSONObject)localObject2).getString("user-name"), ((JSONObject)localObject2).getString("sales-date"));
          localObject3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(((JSONObject)localObject2).getString("sales-date").substring(0, 21));
          SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
          localObject3 = localSimpleDateFormat.format((Date)localObject3).substring(0, 10);
          ((TextView)localObject1).setText("User: " + ((JSONObject)localObject2).getString("user-name") + ", Date: " + (String)localObject3);
          if (localJSONArray != null)
          {
            int k = localJSONArray.length();
            j = 0;
            if (j >= k)
            {
              EditSalesActivity.this.saleItemsAdapter.notifyDataSetChanged();
              EditSalesActivity.this.salesListView.requestFocus();
            }
          }
        }
        catch (JSONException localJSONException)
        {
          int j;
          localJSONException.printStackTrace();
          break;
          Object localObject3 = localJSONException.getJSONObject(j);
          int m = ((JSONObject)localObject3).getInt("ticket-length");
          if (m != 1) {
            break label551;
          }
          str = "0";
          localLottery = new Lottery(((JSONObject)localObject3).getString("ticket-name"), ((JSONObject)localObject3).getInt("master-id"), "", str, m);
          localLottery.setRate(Double.valueOf(Double.parseDouble(((JSONObject)localObject3).getString("sales-amount")) / ((JSONObject)localObject3).getInt("count")));
          localObject3 = new SaleItem(localLottery, ((JSONObject)localObject3).getInt("serial-no"), ((JSONObject)localObject3).getInt("count"));
          EditSalesActivity.this.saleItems.add((SaleItem)localObject3);
          j++;
          continue;
          Toast.makeText(EditSalesActivity.this.editSalesActivity, "Couldn't retrieve sale info for this bill number", 1).show();
        }
        catch (ParseException localParseException)
        {
          localParseException.printStackTrace();
        }
        break;
        label551:
        if (localLottery == 2) {
          str = "00";
        } else {
          str = "000";
        }
      }
    }
  };
  private final View.OnClickListener getSalesDetailsBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        EditSalesActivity.this.saleItems.clear();
        EditSalesActivity.this.saleItemsAdapter.clear();
        Editable localEditable = ((EditText)EditSalesActivity.this.findViewById(2131165223)).getText();
        if ((localEditable != null) && (localEditable.length() != 0)) {
          EditSalesActivity.this.salesID = Integer.parseInt(localEditable.toString());
        }
        if (EditSalesActivity.this.salesID <= 0) {
          Toast.makeText(EditSalesActivity.this.editSalesActivity, "Enter a valid bill number", 1).show();
        } else {
          EditSalesActivity.this.getSalesDetails();
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  };
  private int preferredDb;
  private int saleInfoCustID;
  private String saleInfoCustName;
  private int saleInfoCustUserType;
  private String saleInfoDate;
  private SaleArrayList saleItems = new SaleArrayList();
  private SaleEditAdapter saleItemsAdapter = null;
  private int salesID;
  private ListView salesListView;
  private final View.OnClickListener saveEditBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (EditSalesActivity.this.saleItemsAdapter.isDirty()) {
        try
        {
          if (EditSalesActivity.this.saleItems.isAnyEnabled()) {
            EditSalesActivity.this.saveEdit();
          } else {
            EditSalesActivity.this.deleteSale();
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      } else {
        EditSalesActivity.this.showDialog(3);
      }
    }
  };
  private ProgressDialog serverCommProgressDialog = null;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      EditSalesActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      EditSalesActivity.this.webService = null;
    }
  };
  private int userID;
  private int userType;
  private WebService webService;
  
  private void bindService()
  {
    Object localObject = new Intent(this, WebService.class);
    ((Intent)localObject).putExtra("fromApplication", true);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), (Intent)localObject);
    bindService(new Intent(this, WebService.class), this.serviceConnection, 1);
    localObject = new IntentFilter("com.fortune.restful.intent.filter");
    registerReceiver(this.editSalesReceiver, (IntentFilter)localObject);
  }
  
  private void clearSaleInfo()
  {
    this.saleInfoCustID = 0;
    this.saleInfoCustUserType = 0;
    this.saleInfoDate = "";
    this.saleInfoCustName = "";
  }
  
  private void deleteSale()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 3);
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("customer-id", getSaleInfoCustId());
    localIntent.putExtra("customer-type", getSaleInfoCustLoginType());
    localIntent.putExtra("sale-id", this.salesID);
    localIntent.putExtra("sale-jsonml", this.saleItems.toJSONML());
    localIntent.putExtra("sale-date", getSaleInfoSalesDate());
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
    showProgressDialog("Deleting transaction");
  }
  
  private void dismissProgressDialog()
  {
    if (this.serverCommProgressDialog != null)
    {
      this.serverCommProgressDialog.dismiss();
      this.serverCommProgressDialog = null;
    }
  }
  
  private int getSaleInfoCustId()
  {
    return this.saleInfoCustID;
  }
  
  private int getSaleInfoCustLoginType()
  {
    return this.saleInfoCustUserType;
  }
  
  private String getSaleInfoCustName()
  {
    return this.saleInfoCustName;
  }
  
  private String getSaleInfoSalesDate()
  {
    return this.saleInfoDate;
  }
  
  private void saveEdit()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 4);
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("customer-id", getSaleInfoCustId());
    localIntent.putExtra("customer-type", getSaleInfoCustLoginType());
    localIntent.putExtra("sale-id", this.salesID);
    localIntent.putExtra("sale-jsonml", this.saleItems.toJSONML());
    localIntent.putExtra("sale-date", getSaleInfoSalesDate());
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
    showProgressDialog("Saving changes");
  }
  
  private void saveSaleInfo(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    this.saleInfoCustID = paramInt1;
    this.saleInfoCustUserType = paramInt2;
    this.saleInfoCustName = paramString1;
    this.saleInfoDate = paramString2;
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.serverCommProgressDialog == null) {
      this.serverCommProgressDialog = ProgressDialog.show(this, "Sales", paramString, true, true);
    }
  }
  
  public void getSalesDetails()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 11);
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("sale-id", this.salesID);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
    showProgressDialog("Retrieving sales details");
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    bindService();
    this.editSalesActivity = this;
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    ((Button)findViewById(2131165224)).setOnClickListener(this.getSalesDetailsBtnListener);
    ((Button)findViewById(2131165225)).setOnClickListener(this.deleteBtnListener);
    ((Button)findViewById(2131165226)).setOnClickListener(this.saveEditBtnListener);
    this.salesListView = ((ListView)findViewById(2131165228));
    this.saleItemsAdapter = new SaleEditAdapter(this, 2130903048, this.saleItems, this.editSalesActivity);
    this.salesListView.setAdapter(this.saleItemsAdapter);
    this.salesListView.setItemsCanFocus(true);
    this.salesListView.setClickable(true);
    this.salesListView.setFocusable(true);
    this.salesListView.setFocusableInTouchMode(true);
  }
  
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    Object localObject = new AlertDialog.Builder(this.editSalesActivity);
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
      break;
    case 2: 
      ((AlertDialog.Builder)localObject).setMessage("Edited data cannot be deleted! Get details again.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      break;
    case 3: 
      ((AlertDialog.Builder)localObject).setMessage("No change to save").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      break;
    case 4: 
      ((AlertDialog.Builder)localObject).setMessage("Delete complete transaction for the bill number selected?").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          try
          {
            EditSalesActivity.this.deleteSale();
            paramAnonymousDialogInterface.cancel();
            return;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      break;
    case 5: 
      ((AlertDialog.Builder)localObject).setMessage("You cannot edit this bill now. Contact system administrator").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
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
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.editSalesReceiver);
    unbindService(this.serviceConnection);
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.EditSalesActivity
 * JD-Core Version:    0.7.0.1
 */