package com.fortune.restful.activities;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.Customer;
import com.fortune.restful.WinningReptAdapter;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WinningReportsActivity
  extends Activity
{
  private final int DIALOG_NO_REPORT_AVAILABLE = 1;
  private final int DIALOG_SELECT_ALL_INPUTS = 0;
  private ArrayAdapter<Customer> customerArrayAdapter;
  private AutoCompleteTextView customerAutoCompleteView;
  private final AdapterView.OnItemClickListener customerItemClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      WinningReportsActivity.this.selectedCustomer = ((Customer)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt));
    }
  };
  private ArrayList<Customer> customerList;
  private String displayOption = "A";
  private Date fromDate = new Date();
  private final View.OnClickListener genReportBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        String str2 = "";
        String str1 = "";
        if (WinningReportsActivity.this.selectedCustomer != null) {
          str2 = WinningReportsActivity.this.selectedCustomer.getCustName();
        }
        if (((EditText)WinningReportsActivity.this.findViewById(2131165271)).getText() != null) {
          str1 = ((EditText)WinningReportsActivity.this.findViewById(2131165271)).getText().toString();
        }
        WinningReportsActivity.this.winningReptActivity.generateWinningReport(str2, str1);
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
  };
  private int preferredDb;
  private ViewFlipper reportViewFlipper;
  private ProgressDialog salesProgressDialog = null;
  private final View.OnClickListener selectByBillNoBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        WinningReportsActivity.this.winningReptActivity.generateWinningCustomerOrBillList(false);
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
  };
  private final View.OnClickListener selectByCustomerBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        WinningReportsActivity.this.winningReptActivity.generateWinningCustomerOrBillList(true);
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
  };
  private Customer selectedCustomer = null;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      WinningReportsActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      WinningReportsActivity.this.webService = null;
    }
  };
  private boolean showAgentWinning = false;
  private boolean showStockistWinning = false;
  private boolean showSubstockistWinning = false;
  private Date toDate = new Date();
  private int userID;
  private int userType;
  private WebService webService;
  private WinningReportsActivity winningReptActivity;
  private ArrayList<String> winningReptList = new ArrayList();
  private WinningReptAdapter winningReptListAdapter = null;
  private ListView winningReptListView;
  private final BroadcastReceiver winningReptReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Bundle localBundle = paramAnonymousIntent.getExtras();
      Object localObject1 = localBundle.getString("com.fortune.restful.response_message");
      Object localObject3;
      Object localObject2;
      label98:
      JSONArray localJSONArray;
      label159:
      label180:
      label199:
      Double localDouble3;
      Double localDouble2;
      Object localObject4;
      Double localDouble1;
      if (13 == localBundle.getInt("com.fortune.restful.resp_type"))
      {
        WinningReportsActivity.this.dismissProgressDialog();
        int j;
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          int i;
          if (((JSONObject)localObject1).getBoolean("consol-by-bill"))
          {
            localObject3 = ((JSONObject)localObject1).getJSONArray("bill-nos");
            localObject2 = new ArrayList();
            if (localObject3 != null)
            {
              j = ((JSONArray)localObject3).length();
              i = 0;
              break label1042;
            }
            WinningReportsActivity.this.selectStringPopup((ArrayList)localObject2, false);
            break label1051;
            ((ArrayList)localObject2).add(((JSONArray)localObject3).getString(i));
            i++;
            break label1042;
          }
          if (!i.getBoolean("consol-by-customers")) {
            break label199;
          }
          localObject2 = i.getJSONArray("customer-names");
          localObject3 = new ArrayList();
          if (localObject2 != null)
          {
            i = ((JSONArray)localObject2).length();
            j = 0;
            break label1052;
          }
          WinningReportsActivity.this.selectStringPopup((ArrayList)localObject3, true);
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
        ((ArrayList)localObject3).add(((JSONArray)localObject2).getString(j));
        j++;
        break label1052;
        localJSONArray = localJSONException.getJSONArray("winning-report");
        new JSONObject();
        localObject2 = Double.valueOf(0.0D);
        localDouble3 = Double.valueOf(0.0D);
        localObject3 = Double.valueOf(0.0D);
        localDouble2 = Double.valueOf(0.0D);
        localObject4 = "";
        if (localJSONArray != null) {
          localDouble1 = localJSONArray.length();
        }
      }
      for (Double localDouble4 = 0;; localDouble4++)
      {
        if (localDouble4 >= localDouble1)
        {
          WinningReportsActivity.this.winningReptListAdapter.notifyDataSetChanged();
          WinningReportsActivity.this.winningReptListView.scrollTo(0, 0);
          if ((localJSONArray == null) || (localJSONArray.length() <= 0))
          {
            WinningReportsActivity.this.showDialog(1);
            break label1051;
          }
        }
        else
        {
          Object localObject6 = localJSONArray.getJSONObject(localDouble4);
          Object localObject5 = Double.valueOf(((JSONObject)localObject6).getDouble("prize-amount"));
          Double localDouble7 = Double.valueOf(((JSONObject)localObject6).getDouble("stockist-amount"));
          Double localDouble5 = Double.valueOf(((JSONObject)localObject6).getDouble("substockist-amount"));
          Double localDouble6 = Double.valueOf(((JSONObject)localObject6).getDouble("agent-amount"));
          if (((JSONObject)localObject6).get("ticket-name").equals(""))
          {
            localObject5 = "*** Grp" + ((JSONObject)localObject6).get("group-code") + " total for " + ((JSONObject)localObject6).get("draw-date") + " : " + localObject5;
            if (WinningReportsActivity.this.showStockistWinning) {
              localObject5 = localObject5 + " - " + localDouble7;
            }
            if (WinningReportsActivity.this.showSubstockistWinning) {
              localObject5 = localObject5 + " - " + localDouble5;
            }
            if (WinningReportsActivity.this.showAgentWinning) {
              localObject5 = localObject5 + " - " + localDouble6;
            }
            WinningReportsActivity.this.winningReptList.add(localObject5);
            continue;
          }
          String str = ((JSONObject)localObject6).get("bill").toString();
          if (!str.equals(localObject4))
          {
            WinningReportsActivity.this.winningReptList.add("Bill " + str + " : " + ((JSONObject)localObject6).get("ticket-name") + " : " + ((JSONObject)localObject6).get("customer") + " : " + ((JSONObject)localObject6).get("draw-date") + " : " + ((JSONObject)localObject6).get("sales-date"));
            localObject4 = str;
          }
          localObject6 = "  - Pos: " + ((JSONObject)localObject6).get("prize-position") + " - " + " No.: " + ((JSONObject)localObject6).get("ticket-no") + " Cnt: " + ((JSONObject)localObject6).get("counts") + " Prz: " + localObject5;
          if (WinningReportsActivity.this.showStockistWinning) {
            localObject6 = localObject6 + " Stk: " + localDouble7;
          }
          if (WinningReportsActivity.this.showSubstockistWinning) {
            localObject6 = localObject6 + " SStk: " + localDouble5;
          }
          if (WinningReportsActivity.this.showAgentWinning) {
            localObject6 = localObject6 + " Agnt: " + localDouble6;
          }
          WinningReportsActivity.this.winningReptList.add(localObject6);
          localDouble2 = Double.valueOf(localDouble2.doubleValue() + localDouble6.doubleValue());
          localObject3 = Double.valueOf(((Double)localObject3).doubleValue() + localDouble5.doubleValue());
          localDouble3 = Double.valueOf(localDouble3.doubleValue() + localDouble7.doubleValue());
          localObject2 = Double.valueOf(((Double)localObject2).doubleValue() + ((Double)localObject5).doubleValue());
          continue;
        }
        if (!WinningReportsActivity.this.showStockistWinning) {
          localDouble3 = Double.valueOf(0.0D);
        }
        if (!WinningReportsActivity.this.showSubstockistWinning) {
          localObject3 = Double.valueOf(0.0D);
        }
        if (!WinningReportsActivity.this.showAgentWinning) {
          localDouble2 = Double.valueOf(0.0D);
        }
        WinningReportsActivity.this.updateSummaryDisplay(Double.valueOf(((Double)localObject2).doubleValue() + localDouble3.doubleValue() + localDouble2.doubleValue()), (Double)localObject2, localDouble3, (Double)localObject3, localDouble2);
        WinningReportsActivity.this.reportViewFlipper.setDisplayedChild(1);
        break label1051;
        label1042:
        if (localDouble1 < localDouble2) {
          break label98;
        }
        break;
        label1051:
        return;
        label1052:
        if (localDouble2 < localDouble1) {
          break label180;
        }
        break label159;
      }
    }
  };
  
  private void bindService()
  {
    Object localObject = new Intent(this, WebService.class);
    ((Intent)localObject).putExtra("fromApplication", true);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), (Intent)localObject);
    bindService(new Intent(this, WebService.class), this.serviceConnection, 1);
    localObject = new IntentFilter("com.fortune.restful.intent.filter");
    registerReceiver(this.winningReptReceiver, (IntentFilter)localObject);
  }
  
  private void dismissProgressDialog()
  {
    if (this.salesProgressDialog != null)
    {
      this.salesProgressDialog.dismiss();
      this.salesProgressDialog = null;
    }
  }
  
  private void selectStringPopup(ArrayList<String> paramArrayList, final boolean paramBoolean)
  {
    final CharSequence[] arrayOfCharSequence = (CharSequence[])paramArrayList.toArray(new CharSequence[paramArrayList.size()]);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Select item");
    localBuilder.setItems(arrayOfCharSequence, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        String str1;
        if (paramBoolean) {
          str1 = (String)arrayOfCharSequence[paramAnonymousInt];
        }
        for (;;)
        {
          try
          {
            WinningReportsActivity.this.generateWinningReport(str1, "");
            return;
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.printStackTrace();
            continue;
          }
          String str2 = (String)arrayOfCharSequence[paramAnonymousInt];
          try
          {
            WinningReportsActivity.this.generateWinningReport("", str2);
          }
          catch (JSONException localJSONException2)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
    });
    localBuilder.create().show();
  }
  
  private void setupCustomerDropDown()
  {
    this.customerAutoCompleteView = ((AutoCompleteTextView)findViewById(2131165189));
    this.customerArrayAdapter = new ArrayAdapter(this, 17367050, this.customerList);
    this.customerArrayAdapter.setNotifyOnChange(true);
    this.customerAutoCompleteView.setThreshold(0);
    this.customerAutoCompleteView.setAdapter(this.customerArrayAdapter);
    this.customerAutoCompleteView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((AutoCompleteTextView)paramAnonymousView).showDropDown();
      }
    });
    this.customerAutoCompleteView.setOnItemClickListener(this.customerItemClickListener);
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.salesProgressDialog == null) {
      this.salesProgressDialog = ProgressDialog.show(this, "Reports", paramString, true, true);
    }
  }
  
  public void generateWinningCustomerOrBillList(boolean paramBoolean)
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 13);
    DatePicker localDatePicker = (DatePicker)findViewById(2131165239);
    this.toDate.setYear(-1900 + localDatePicker.getYear());
    this.toDate.setDate(localDatePicker.getDayOfMonth());
    this.toDate.setMonth(localDatePicker.getMonth());
    localDatePicker = (DatePicker)findViewById(2131165237);
    this.fromDate.setYear(-1900 + localDatePicker.getYear());
    this.fromDate.setDate(localDatePicker.getDayOfMonth());
    this.fromDate.setMonth(localDatePicker.getMonth());
    localIntent.putExtra("from-date", this.fromDate.getTime());
    localIntent.putExtra("to-date", this.toDate.getTime());
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("display", this.displayOption);
    if (!paramBoolean)
    {
      localIntent.putExtra("consol-by-bill", true);
      localIntent.putExtra("consol-by-customers", false);
      showProgressDialog("Fetching winning bill numbers");
    }
    else
    {
      localIntent.putExtra("consol-by-customers", true);
      localIntent.putExtra("consol-by-bill", false);
      showProgressDialog("Fetching winning customers");
    }
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
  }
  
  public void generateWinningReport(String paramString1, String paramString2)
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 13);
    DatePicker localDatePicker = (DatePicker)findViewById(2131165239);
    this.toDate.setYear(-1900 + localDatePicker.getYear());
    this.toDate.setDate(localDatePicker.getDayOfMonth());
    this.toDate.setMonth(localDatePicker.getMonth());
    localDatePicker = (DatePicker)findViewById(2131165237);
    this.fromDate.setYear(-1900 + localDatePicker.getYear());
    this.fromDate.setDate(localDatePicker.getDayOfMonth());
    this.fromDate.setMonth(localDatePicker.getMonth());
    localIntent.putExtra("from-date", this.fromDate.getTime());
    localIntent.putExtra("to-date", this.toDate.getTime());
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("display", this.displayOption);
    if ((!paramString1.equals("")) || (!paramString2.equals("")))
    {
      localIntent.putExtra("sales-id", paramString2);
      localIntent.putExtra("customer", paramString1);
      WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
      showProgressDialog("Fetching winning report");
    }
    else
    {
      showDialog(0);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903054);
    bindService();
    this.winningReptActivity = this;
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.showAgentWinning = localIntent.getBooleanExtra("show-agent-winning", false);
    this.showStockistWinning = localIntent.getBooleanExtra("show-stockist-winning", false);
    this.showSubstockistWinning = localIntent.getBooleanExtra("show-substockist-winning", false);
    this.customerList = localIntent.getParcelableArrayListExtra("customer-list");
    this.reportViewFlipper = ((ViewFlipper)findViewById(2131165269));
    this.winningReptListAdapter = new WinningReptAdapter(this, 2130903053, this.winningReptList);
    this.winningReptListView = ((ListView)findViewById(2131165275));
    this.winningReptListView.setAdapter(this.winningReptListAdapter);
    ((Button)findViewById(2131165272)).setOnClickListener(this.genReportBtnListener);
    ((Button)findViewById(2131165273)).setOnClickListener(this.selectByCustomerBtnListener);
    ((Button)findViewById(2131165274)).setOnClickListener(this.selectByBillNoBtnListener);
    setupCustomerDropDown();
  }
  
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    Object localObject = new AlertDialog.Builder(this);
    switch (paramInt)
    {
    default: 
      localObject = null;
      break;
    case 0: 
      ((AlertDialog.Builder)localObject).setMessage("You must select Customer or Bill No!\nOr else select Winning Customers or\nwinning Bill Numbers first").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      break;
    case 1: 
      ((AlertDialog.Builder)localObject).setMessage("No report available for the selected customer/bill/date combination").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
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
    unregisterReceiver(this.winningReptReceiver);
    unbindService(this.serviceConnection);
  }
  
  public void setFromDate(Date paramDate)
  {
    this.fromDate = paramDate;
  }
  
  public void setToDate(Date paramDate)
  {
    this.toDate = paramDate;
  }
  
  public void updateSummaryDisplay(Double paramDouble1, Double paramDouble2, Double paramDouble3, Double paramDouble4, Double paramDouble5)
  {
    TextView localTextView3 = (TextView)findViewById(2131165278);
    TextView localTextView2 = (TextView)findViewById(2131165280);
    TextView localTextView1 = (TextView)findViewById(2131165283);
    TextView localTextView4 = (TextView)findViewById(2131165286);
    TextView localTextView5 = (TextView)findViewById(2131165289);
    localTextView3.setText(paramDouble1.toString());
    localTextView2.setText(paramDouble2.toString());
    if (!this.showStockistWinning) {
      findViewById(2131165281).setVisibility(8);
    } else {
      localTextView1.setText(paramDouble3.toString());
    }
    if (!this.showSubstockistWinning) {
      findViewById(2131165284).setVisibility(8);
    } else {
      localTextView4.setText(paramDouble4.toString());
    }
    if (!this.showAgentWinning) {
      findViewById(2131165287).setVisibility(8);
    } else {
      localTextView5.setText(paramDouble5.toString());
    }
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.WinningReportsActivity
 * JD-Core Version:    0.7.0.1
 */