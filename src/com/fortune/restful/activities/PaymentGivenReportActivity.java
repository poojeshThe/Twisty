package com.fortune.restful.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.Customer;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentGivenReportActivity
  extends Activity
{
  private ArrayAdapter<Customer> customerArrayAdapter;
  private AutoCompleteTextView customerAutoCompleteView;
  private final AdapterView.OnItemClickListener customerItemClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      PaymentGivenReportActivity.this.selectedCustomer = ((Customer)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt));
    }
  };
  private ArrayList<Customer> customerList;
  private Date fromDate = new Date();
  private final View.OnClickListener genReportBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        PaymentGivenReportActivity.this.paymentReptActivity.generatePaymentReport();
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
  private PaymentGivenReportActivity paymentReptActivity;
  private ArrayList<String> paymentReptList = new ArrayList();
  private ArrayAdapter<String> paymentReptListAdapter;
  private ListView paymentReptListView;
  private final BroadcastReceiver paymentReptReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Object localObject1 = paramAnonymousIntent.getExtras();
      Object localObject2 = ((Bundle)localObject1).getString("com.fortune.restful.response_message");
      if (14 == ((Bundle)localObject1).getInt("com.fortune.restful.resp_type")) {
        try
        {
          JSONArray localJSONArray = new JSONObject((String)localObject2).getJSONArray("payment-given-report");
          new JSONObject();
          localObject2 = "";
          int j;
          if (localJSONArray != null) {
            j = localJSONArray.length();
          }
          for (int i = 0;; i++)
          {
            if (i >= j)
            {
              PaymentGivenReportActivity.this.paymentReptListAdapter.notifyDataSetChanged();
              PaymentGivenReportActivity.this.paymentReptListView.scrollTo(0, 0);
              PaymentGivenReportActivity.this.reportViewFlipper.setDisplayedChild(1);
              break;
            }
            localObject1 = localJSONArray.getJSONObject(i);
            String str = ((JSONObject)localObject1).getString("sales-date");
            if ((!str.equals(localObject2)) && (!str.equals("")))
            {
              localObject2 = str;
              PaymentGivenReportActivity.this.paymentReptList.add("Date: " + (String)localObject2);
              PaymentGivenReportActivity.this.paymentReptList.add("-----------------------------");
            }
            if (((JSONObject)localObject1).get("customer-name").equals("Total")) {
              PaymentGivenReportActivity.this.paymentReptList.add("****************************");
            }
            PaymentGivenReportActivity.this.paymentReptList.add(((JSONObject)localObject1).get("customer-name") + " :" + " Sales " + ((JSONObject)localObject1).get("sales-amount") + ", Recv " + ((JSONObject)localObject1).get("received-amount") + ", Win " + ((JSONObject)localObject1).get("winning-amount") + ", Bal " + ((JSONObject)localObject1).get("balance"));
          }
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
  };
  private int preferredDb;
  private ViewFlipper reportViewFlipper;
  private Customer selectedCustomer = null;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      PaymentGivenReportActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      PaymentGivenReportActivity.this.webService = null;
    }
  };
  private boolean showNetpay = false;
  private Date toDate = new Date();
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
    registerReceiver(this.paymentReptReceiver, (IntentFilter)localObject);
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
  
  public void generatePaymentReport()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 14);
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
    if (this.selectedCustomer != null) {
      localIntent.putExtra("customer", this.selectedCustomer.getUserLoginID());
    } else {
      localIntent.putExtra("customer", "");
    }
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    bindService();
    this.paymentReptActivity = this;
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.showNetpay = localIntent.getBooleanExtra("show-net-pay", false);
    this.customerList = localIntent.getParcelableArrayListExtra("customer-list");
    this.reportViewFlipper = ((ViewFlipper)findViewById(2131165242));
    this.paymentReptListAdapter = new ArrayAdapter(this, 2130903053, this.paymentReptList);
    this.paymentReptListView = ((ListView)findViewById(2131165245));
    this.paymentReptListView.setAdapter(this.paymentReptListAdapter);
    ((Button)findViewById(2131165244)).setOnClickListener(this.genReportBtnListener);
    setupCustomerDropDown();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.paymentReptReceiver);
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
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.PaymentGivenReportActivity
 * JD-Core Version:    0.7.0.1
 */